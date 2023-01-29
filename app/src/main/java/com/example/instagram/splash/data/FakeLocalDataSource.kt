package com.example.instagram.splash.data

import com.example.instagram.common.model.Database

class FakeLocalDataSource : SplashDatasource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}