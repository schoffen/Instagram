package com.example.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.common.view.CustomDialog
import com.example.instagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it) {
                registerBtJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerPhotoBtnNext.isEnabled = true
                registerPhotoBtnNext.setOnClickListener {
                    val customDialog = CustomDialog(requireContext())

                    customDialog.setTitle(R.string.define_photo_profile)
                    customDialog.addButton(R.string.photo, R.string.gallery) { dialogView ->
                        when (dialogView.id) {
                            R.string.photo -> {

                            }

                            R.string.gallery -> {

                            }
                        }
                    }

                    customDialog.show()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}