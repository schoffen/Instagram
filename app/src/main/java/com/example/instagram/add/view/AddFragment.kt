package com.example.instagram.add.view

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.instagram.R
import com.example.instagram.add.Add
import com.example.instagram.common.base.BaseFragment
import com.example.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit var presenter: Add.Presenter

    override fun setupPresenter() {
    }

    override fun setupViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager

        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            getPermission.launch(REQUIRED_PERMISSION)
        }


    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        REQUIRED_PERMISSION
    ) == PackageManager.PERMISSION_GRANTED

    private fun startCamera() {

    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), R.string.permission_camera_denied, Toast.LENGTH_LONG)
                    .show()
            }
        }

    companion object {
        private const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }
}