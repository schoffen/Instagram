package com.example.instagram.post.data

import com.example.instagram.common.model.PhotoPair

interface PostDataSource {
    suspend fun fetchPictures(): List<PhotoPair>
}