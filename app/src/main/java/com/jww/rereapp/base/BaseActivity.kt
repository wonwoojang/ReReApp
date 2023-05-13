package com.jww.rereapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jww.rereapp.R
import com.jww.rereapp.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val binding: ActivityBaseBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_base)
        binding.run {
            lifecycleOwner = this@BaseActivity
        }
    }
}