package com.example.instagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.common.model.Database

class FakeRegisterDatasource : RegisterEmailDatasource{
    override fun create(email: String, callback: RegisterEmailCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }

            callback.onComplete()
        }, 2000)
    }
}