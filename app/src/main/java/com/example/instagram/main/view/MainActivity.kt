package com.example.instagram.main.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.camera.view.CameraFragment
import com.example.instagram.common.extension.replaceFragment
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.home.view.HomeFragment
import com.example.instagram.profile.view.ProfileFragment
import com.example.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var cameraFragment: CameraFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var currentFragment: Fragment

    private lateinit var fragmentSavedState: HashMap<String, Fragment.SavedState?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )

            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        if (savedInstanceState == null) {
            fragmentSavedState = HashMap()
        } else {
            savedInstanceState.getSerializable("fragmentState") as HashMap<String, Fragment.SavedState?>
        }

//        homeFragment = HomeFragment()
//        searchFragment = SearchFragment()
//        cameraFragment = CameraFragment()
//        profileFragment = ProfileFragment()

//        currentFragment = homeFragment

//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.main_fragment, profileFragment, "3").hide(profileFragment)
//            add(R.id.main_fragment, cameraFragment, "2").hide(cameraFragment)
//            add(R.id.main_fragment, searchFragment, "1").hide(searchFragment)
//            add(R.id.main_fragment, homeFragment, "0")
//            commit()
//        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("fragmentState", fragmentSavedState)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false

        val newFrag: Fragment? = when(item.itemId) {
            R.id.menu_bottom_home -> {
                HomeFragment()
            }
            R.id.menu_bottom_profile -> {
                ProfileFragment()
            }
            else -> null
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment)
        val fragmentTag = newFrag?.javaClass?.simpleName

        if (!currentFragment?.tag.equals(fragmentTag)) {
            currentFragment?.let {  frag ->
                fragmentSavedState.put(
                    frag.tag!!,
                    supportFragmentManager.saveFragmentInstanceState(frag)
                )
            }
        }

        newFrag?.setInitialSavedState(fragmentSavedState[fragmentTag])
        newFrag?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, it, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }

//        when (item.itemId) {
//            R.id.menu_bottom_home -> {
//                if (currentFragment == homeFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(homeFragment)
//                    .commit()
//                currentFragment = homeFragment
//            }
//
//            R.id.menu_bottom_search -> {
//                if (currentFragment == searchFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(searchFragment)
//                    .commit()
//                currentFragment = searchFragment
//            }
//
//            R.id.menu_bottom_add -> {
//                if (currentFragment == cameraFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(cameraFragment)
//                    .commit()
//                currentFragment = cameraFragment
//            }
//
//            R.id.menu_bottom_profile -> {
//                if (currentFragment == profileFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment)
//                    .show(profileFragment).commit()
//                currentFragment = profileFragment
//                scrollToolbarEnabled = true
//            }
//        }

        setScrollToolbarEnabled(scrollToolbarEnabled)

        return true
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS

            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatorParams
    }
}