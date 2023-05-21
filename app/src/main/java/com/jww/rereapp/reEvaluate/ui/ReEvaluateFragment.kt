package com.jww.rereapp.reEvaluate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentReEvaluateBinding
import com.jww.rereapp.databinding.ItemChipStringRoundBinding

class ReEvaluateFragment : BaseFragment() {

    private var _binding: FragmentReEvaluateBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReEvaluateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
        initWatchNumberTimes()
    }

    private fun initWatchNumberTimes() {
        with(binding.includeWatchNumberTimes.chipGroup) {
            addView(makeSelectChip("1회차"))
            addView(makeSelectChip("2회차"))
            addView(makeSelectChip("3회차"))
            addView(makeSelectChip("4회차"))
            addView(makeSelectChip("5회차"))
        }
    }

    private fun bind() {
        binding.run {

            binding.includeWatchNumberTimes.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                Log.d("Jww::", "change = ${checkedIds}")
            }
        }
    }

    private fun observe() {

    }

    private fun makeSelectChip(message: String): Chip {
        return ItemChipStringRoundBinding.inflate(
            LayoutInflater.from(requireContext()),
            binding.includeWatchNumberTimes.chipGroup,
            false
        ).apply {
            this.message = message
            chip.id = View.NO_ID
        }.chip
    }
}