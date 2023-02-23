package com.example.instagram.home.data

import com.example.instagram.common.base.RequestCallback
import com.example.instagram.common.model.Post

interface HomeDataSource {
    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession() : String { throw UnsupportedOperationException() }
    fun putFeed(response: List<Post>?) { throw UnsupportedOperationException() }
}