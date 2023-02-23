package com.example.instagram.profile.data

import com.example.instagram.common.base.Cache
import com.example.instagram.common.model.User

object ProfileMemoryCache : Cache<Pair<User, Boolean?>> {
    private var user: Pair<User, Boolean?>? = null

    override fun isCached(): Boolean {
        return user != null
    }

    override fun get(key: String): Pair<User, Boolean?>? {
        return if (user?.first?.uuid == key) {
            user
        } else {
            null
        }
    }

    override fun put(data: Pair<User, Boolean?>?) {
        this.user = data
    }
}