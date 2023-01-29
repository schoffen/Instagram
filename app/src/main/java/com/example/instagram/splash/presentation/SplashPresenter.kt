package com.example.instagram.splash.presentation

import com.example.instagram.splash.Splash
import com.example.instagram.splash.data.SplashCallback
import com.example.instagram.splash.data.SplashRepository

class SplashPresenter(private var view: Splash.View?, private val repository: SplashRepository) : Splash.Presenter{
    override fun onDestroy() {
        view = null
    }

    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })
    }
}