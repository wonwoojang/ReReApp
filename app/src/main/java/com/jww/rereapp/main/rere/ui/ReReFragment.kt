package com.jww.rereapp.main.rere.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentRereBinding

class ReReFragment : BaseFragment() {
    private var _binding: FragmentRereBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRereBinding.inflate(inflater, container, false)
        return binding.root
    }
}