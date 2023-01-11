package com.example.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentRegisterNamePasswordBinding

class RegisterNamePasswordFragment : Fragment() {

    private lateinit var binding: FragmentRegisterNamePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterNamePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}