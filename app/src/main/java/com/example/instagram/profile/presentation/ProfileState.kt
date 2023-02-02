package com.example.instagram.profile.presentation

import com.example.instagram.common.model.Post
import com.example.instagram.common.model.UserAuth
import com.example.instagram.profile.Profile

class ProfileState(private val posts: List<Post>?, private val userAuth: UserAuth?) : Profile.State {
    override fun fetchUserProfile(): UserAuth? {
        return userAuth
    }

    override fun fetchUserPosts(): List<Post>? {
        return posts
    }
}