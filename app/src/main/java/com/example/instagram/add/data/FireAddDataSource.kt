package com.example.instagram.add.data

import android.net.Uri
import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.example.instagram.common.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireAddDataSource : AddDataSource {
    override fun createPost(
        userUUID: String,
        uri: Uri,
        caption: String,
        callback: RequestCallback<Boolean>
    ) {
        val uriLastPath = uri.lastPathSegment ?: throw IllegalArgumentException("Imagem inválida")

        val imgRef = FirebaseStorage.getInstance().reference
            .child("images/")
            .child(userUUID)
            .child(uriLastPath)

        imgRef.putFile(uri)
            .addOnSuccessListener { res ->
                imgRef.downloadUrl
                    .addOnSuccessListener { resDownload ->

                        FirebaseFirestore.getInstance()
                            .collection("/users")
                            .document(userUUID)
                            .get()
                            .addOnSuccessListener { resMe ->
                                val me = resMe.toObject(User::class.java)

                                val postRef = FirebaseFirestore.getInstance()
                                    .collection("/posts")
                                    .document(userUUID)
                                    .collection("posts")
                                    .document()

                                val post = Post(
                                    uuid = postRef.id,
                                    photoUrl = resDownload.toString(),
                                    caption = caption,
                                    timestamp = System.currentTimeMillis(),
                                    publisher = me
                                )

                                postRef.set(post)
                                    .addOnSuccessListener { resPost ->
                                        FirebaseFirestore.getInstance()
                                            .collection("/feeds")
                                            .document(userUUID)
                                            .collection("posts")
                                            .document(postRef.id)
                                            .set(post)
                                            .addOnSuccessListener { resMyFeed ->
                                                FirebaseFirestore.getInstance()
                                                    .collection("/followers")
                                                    .document(userUUID)
                                                    .collection("followers")
                                                    .get()
                                                    .addOnSuccessListener { resFollowers ->
                                                        val documents = resFollowers.documents

                                                        for (document in documents) {
                                                            val followerUUID = document.toObject(String::class.java) ?: throw RuntimeException("Falha ao converter seguidor")
                                                            FirebaseFirestore.getInstance()
                                                                .collection("/feeds")
                                                                .document(followerUUID)
                                                                .collection("posts")
                                                                .document(postRef.path)
                                                                .set(post)
                                                        }

                                                        callback.onSuccess(true)
                                                    }
                                                    .addOnFailureListener { callback.onFailure(it.message ?: "Falha ao buscar seguidores") }
                                                    .addOnCompleteListener { callback.onComplete() }
                                            }

                                    }
                                    .addOnFailureListener { callback.onFailure(it.message ?: "Falha ao subir post") }
                            }
                            .addOnFailureListener { callback.onFailure(it.message ?: "Falha ao buscar usuário") }
                    }
                    .addOnFailureListener { callback.onFailure(it.message ?: "Falha ao baixar foto") }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Falha ao subir a foto")
            }
    }
}