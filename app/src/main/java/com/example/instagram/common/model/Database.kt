package com.example.instagram.common.model

import java.util.UUID

object Database {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA =
            UserAuth(UUID.randomUUID().toString(), "User A", "userA@gmail.com", "12345678", null)
        val userB =
            UserAuth(UUID.randomUUID().toString(), "User B", "userB@gmail.com", "87654321", null)

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        for(i in 0..30) {
            val user = UserAuth(UUID.randomUUID().toString(), "User$i", "user$i@gmail.com", "123123123", null)
            usersAuth.add(user)
        }

        sessionAuth = usersAuth.first()
    }

}