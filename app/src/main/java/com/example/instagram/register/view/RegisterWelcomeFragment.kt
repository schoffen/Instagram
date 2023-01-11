package com.example.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentRegisterWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterWelcomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}