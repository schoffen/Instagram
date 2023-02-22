package com.example.instagram.splash.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class FireSplashDataSource : SplashDatasource {
    override fun session(callback: SplashCallback) {
        if (FirebaseAuth.getInstance().uid != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}