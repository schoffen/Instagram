package com.example.instagram.home.data

import com.example.instagram.common.base.Cache
import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post
import com.google.firebase.auth.FirebaseAuth

class HomeLocalDataSource(
    private val feedCache: Cache<List<Post>>
) : HomeDataSource {

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = feedCache.get(userUUID)
        if (posts != null) {
            callback.onSuccess(posts)
        } else {
            callback.onFailure("posts nao existem")
        }
        callback.onComplete()
    }

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("usuario não logado")
    }

    override fun putFeed(response: List<Post>?) {
        feedCache.put(response)
    }
}