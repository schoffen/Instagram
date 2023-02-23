package com.example.instagram.profile.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.example.instagram.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FireProfileDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        userUUID: String,
        callback: RequestCallback<Pair<User, Boolean?>>
    ) {
        FirebaseFirestore.getInstance().collection("/users").document(userUUID).get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)

                when (user) {
                    null -> callback.onFailure("Falha ao converter usuário")
                    else -> {
                        if (user.uuid == FirebaseAuth.getInstance().uid) {
                            callback.onSuccess(Pair(user, null))
                        } else {
                            FirebaseFirestore.getInstance().collection("/followers")
                                .document(FirebaseAuth.getInstance().uid!!)
                                .collection("followers")
                                .whereEqualTo("uuid", userUUID)
                                .get()
                                .addOnSuccessListener {
                                    callback.onSuccess(Pair(user, !it.isEmpty))
                                }
                                .addOnFailureListener {
                                    callback.onFailure(it.message ?: "Falha ao buscar seguidores")
                                }
                                .addOnCompleteListener { callback.onComplete() }
                        }
                    }
                }
            }
            .addOnFailureListener {
                callback.onFailure(it.message ?: "Falha ao buscar usuário")
            }
    }

    override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        FirebaseFirestore.getInstance()
            .collection("/posts")
            .document(userUUID)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { res ->
                val documents = res.documents
                val posts = mutableListOf<Post>()
                for (document in documents) {
                    val post = document.toObject(Post::class.java)
                    post?.let { posts.add(it) }
                }
                callback.onSuccess(posts)
            }
            .addOnFailureListener { callback.onFailure(it.message ?: "Falha posts") }
            .addOnCompleteListener { callback.onComplete() }
    }

    override fun followUser(
        userUUID: String,
        isFollow: Boolean,
        callback: RequestCallback<Boolean>
    ) {

    }
}