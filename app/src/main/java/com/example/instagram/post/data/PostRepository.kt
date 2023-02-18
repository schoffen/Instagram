package com.example.instagram.post.data

import com.example.instagram.common.model.PhotoPair

class PostRepository(private val dataSource: PostDataSource) {
    suspend fun fetchPictures(): List<PhotoPair> {
        return dataSource.fetchPictures()
    }
}