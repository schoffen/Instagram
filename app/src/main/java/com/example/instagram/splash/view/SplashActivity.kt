package com.example.instagram.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.databinding.ActivitySplashBinding
import com.example.instagram.splash.Splash
import com.example.instagram.splash.presentation.SplashPresenter
import com.example.instagram.common.base.DependencyInjector
import com.example.instagram.common.extension.animationEnd
import com.example.instagram.login.view.LoginActivity
import com.example.instagram.main.view.MainActivity

class SplashActivity : AppCompatActivity(), Splash.View {

    private lateinit var binding: ActivitySplashBinding

    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = SplashPresenter(this, DependencyInjector.splashRepository())

        binding.splashImg.animate().apply {
            setListener(animationEnd {
                presenter.authenticated()
            })
            duration = 1000
            alpha(1.0f)
            start()
        }
    }

    private fun animatedIntentScreen(activity: Activity) {
        binding.splashImg.animate().apply {
            setListener(animationEnd {
                val intent = Intent(
                    baseContext,
                    activity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0.0f)
            start()
        }
    }

    override fun goToMainScreen() {
        animatedIntentScreen(MainActivity())
    }

    override fun goToLoginScreen() {
        animatedIntentScreen(LoginActivity())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}