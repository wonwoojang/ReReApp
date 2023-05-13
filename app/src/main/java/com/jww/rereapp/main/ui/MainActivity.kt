package com.jww.rereapp.main.ui

import android.os.Bundle
import com.jww.rereapp.R
import com.jww.rereapp.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.container, NavigationFragment())
            .commit()
    }
}