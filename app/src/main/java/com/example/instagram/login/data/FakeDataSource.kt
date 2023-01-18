package com.example.instagram.login.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.common.model.Database

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null){
                callback.onFailure("Usuário não encontrado")
            } else if (userAuth.password != password){
                callback.onFailure("Senha incorreta")
            } else {
                Database.sessionAuth = userAuth
                callback.onSuccess(userAuth)
            }

            callback.onComplete()
        }, 2000)
    }
}