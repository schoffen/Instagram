package com.example.instagram.add.data

import com.example.instagram.common.model.Database
import com.example.instagram.common.model.UserAuth
import com.google.firebase.auth.FirebaseAuth

class AddLocalDataSource : AddDataSource {

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid ?: throw RuntimeException("usuario nao logado")
    }
}