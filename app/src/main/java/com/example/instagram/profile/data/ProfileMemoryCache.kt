package com.example.instagram.profile.data

import com.example.instagram.common.model.UserAuth

object ProfileMemoryCache : ProfileCache<UserAuth>{
    private var userAuth: UserAuth? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): UserAuth? {
        if (userAuth?.uuid == key) {
            return userAuth
        } else {
            return null
        }
    }

    override fun put(data: UserAuth) {
        this.userAuth = data
    }
}