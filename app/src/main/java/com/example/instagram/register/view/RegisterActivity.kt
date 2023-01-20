package com.example.instagram.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.ActivityRegisterBinding
import com.example.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)
    }

    override fun goToNameAndPasswordScreen(email: String) {

        val fragment = RegisterNamePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EMAIL, email)
            }
        }

        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val fragment = RegisterWelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
        }

        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()

        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragment, fragment)
                addToBackStack(null)
                commit()
            }
        }

    }
}