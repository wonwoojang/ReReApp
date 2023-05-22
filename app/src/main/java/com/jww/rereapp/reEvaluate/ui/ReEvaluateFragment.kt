package com.jww.rereapp.reEvaluate.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.chip.Chip
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentReEvaluateBinding
import com.jww.rereapp.databinding.ItemChipStringRoundBinding
import com.jww.rereapp.extension.throttleClick

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
        initReason()
        initSexAge()
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

    private fun initReason() {
        with(binding.includeReason.chipGroup) {
            addView(makeSelectChip("감독의 연출"))
            addView(makeSelectChip("배우 연기력"))
            addView(makeSelectChip("배경 음악"))
            addView(makeSelectChip("시나리오"))
            addView(makeSelectChip("기획의도"))
            addView(makeSelectChip("기타"))
        }
    }

    private fun initSexAge() {
        binding.includeSexAge.run {
            this.spinnerSex.adapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                arrayListOf("남", "녀")
            )

            val ageItemList = arrayListOf<String>()
            for (i in 10..80) {
                ageItemList.add("$i 세")
            }
            this.spinnerAge.adapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                ageItemList
            )
        }

    }

    private fun bind() {
        binding.run {

            includeWatchNumberTimes.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                Log.d("Jww::", "change = ${checkedIds}")
            }

            includeSexAge.run {
                spinnerSex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, position: Int, id: Long
                    ) {
                        Log.d("Jww::", "spinnerSex postion = $position")
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
                spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, position: Int, id: Long
                    ) {
                        Log.d("Jww::", "spinnerAge postion = $position")
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            }

            save.throttleClick {

            }
        }
    }

    private fun observe() {

    }

    private fun makeSelectChip(message: String): Chip {
        return ItemChipStringRoundBinding.inflate(
            LayoutInflater.from(requireContext())
        ).apply {
            this.message = message
            chip.id = View.NO_ID
        }.chip
    }
}