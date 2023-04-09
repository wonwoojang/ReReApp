package com.jww.rereapp.main.ui

import android.os.Bundle
import com.jww.rereapp.base.BaseActivity
import com.jww.rereapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}