package com.example.keepmynotes.view.activity

import android.os.Bundle
import com.example.keepmynotes.base.BaseActivity
import com.example.keepmynotes.view.fragment.HomeFragment
import com.example.keepmynotes.R

class RootActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            createFragment(R.id.fragment_container, HomeFragment(), null, true)
        }
    }
}
