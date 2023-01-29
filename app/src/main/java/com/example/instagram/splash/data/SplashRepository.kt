package com.example.instagram.splash.data

class SplashRepository(
    private val datasource: SplashDatasource
) {
    fun session(callback: SplashCallback) {
        datasource.session(callback)
    }
}