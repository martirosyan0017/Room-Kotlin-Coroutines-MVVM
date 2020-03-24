package com.example.keepmynotes.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.keepmynotes.view.fragment.AddNotesFragment
import com.example.keepmynotes.view.fragment.HomeFragment
import com.example.keepmynotes.R

open class BaseActivity : AppCompatActivity() {

    fun createFragment(resId: Int, fragment: Fragment, bundle: Bundle?, updateScreen: Boolean, reload:Boolean = false) {

        var mFragment = fragment
        val findFragment: Fragment? = supportFragmentManager.findFragmentById(resId)
        val existedFragment: Fragment? = supportFragmentManager.findFragmentByTag(mFragment.javaClass.name)

        if (existedFragment != null && !updateScreen)
            mFragment = existedFragment

        if (bundle != null)
            mFragment.arguments = bundle

        if (existedFragment != null && existedFragment.isVisible && !reload) {
            return
        }

        if (findFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(resId, mFragment, mFragment.javaClass.name)
                .addToBackStack(null)
                .commit()
        } else
            supportFragmentManager
                .beginTransaction()
                .replace(resId, mFragment, mFragment.javaClass.name)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        val visibleFragment = getVisibleFragment()

        if (visibleFragment is AddNotesFragment && getAllFragments().size == 1) {
            createFragment(R.id.fragment_container, HomeFragment(), null, true)
        } else if (visibleFragment is HomeFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun getAllFragments(): List<Fragment> {
        val fragmentManager = this.supportFragmentManager
        return fragmentManager.fragments
    }

    private fun getVisibleFragment(): Fragment? {
        val fragmentManager = this.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments

        for (fragment in fragments) {
            if (fragment.isVisible)
                return fragment
        }
        return null
    }
}
