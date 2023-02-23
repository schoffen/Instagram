package com.example.instagram.home.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FireHomeDataSource : HomeDataSource {
    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        val uid = FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuário não encontrado")

        FirebaseFirestore.getInstance()
            .collection("/feeds")
            .document(uid)
            .collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { res ->
                val feed = mutableListOf<Post>()
                val documents = res.documents
                for (document in documents) {
                    val post = document.toObject(Post::class.java)
                    post?.let { feed.add(it) }
                }
                callback.onSuccess(feed)
            }
            .addOnFailureListener { exceptio ->
                callback.onFailure(
                    exceptio.message ?: "Erro ao carregar feed"
                )
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}