package com.example.instagram.common.model

import java.util.UUID

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(
            UserAuth(UUID.randomUUID().toString(), "User A", "userA@gmail.com", "12345678")
        )

        usersAuth.add(
            UserAuth(UUID.randomUUID().toString(), "User B",  "userB@gmail.com", "87654321")
        )

        sessionAuth = usersAuth.first()
    }

}