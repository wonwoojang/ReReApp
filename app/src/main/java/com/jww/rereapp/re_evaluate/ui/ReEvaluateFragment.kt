package com.jww.rereapp.re_evaluate.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentReEvaluateBinding
import com.jww.rereapp.databinding.ItemChipStringRoundBinding
import com.jww.rereapp.enums.ContentsType
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.BookAdapterItem
import com.jww.rereapp.re_evaluate.ReEvaluateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReEvaluateFragment : BaseFragment() {

    private var _binding: FragmentReEvaluateBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<ReEvaluateViewModel>() {
        parametersOf(
            arguments?.getSerializable(ContentsType::class.simpleName),
            arguments?.getSerializable(BookAdapterItem::class.simpleName)
        )
    }

    private val watchNumberTimesCheckedListener =
        ChipGroup.OnCheckedStateChangeListener { group, _ ->
            viewModel.watchNumberSelected = if (group.checkedChipId != -1) {
                val chip = group.findViewById(group.checkedChipId) as Chip
                chip.text.toString()
            } else null
        }

    private val sexSelectListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>, view: View, position: Int, id: Long
        ) {
            viewModel.sexPosition = position
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }
    private val ageSelectListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>, view: View, position: Int, id: Long
        ) {
            viewModel.agePosition = position
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }

    private val reasonCheckedListener =
        ChipGroup.OnCheckedStateChangeListener { group, checkedChipIds ->
            viewModel.reasonTextList =
                if (!checkedChipIds.contains(-1) && checkedChipIds.size > 0) {
                    val list = mutableListOf<String>()
                    checkedChipIds.forEach {
                        val chip = group.findViewById(it) as Chip
                        list.add(chip.text.toString())
                    }
                    list
                } else null
        }

    fun putArgument(
        contentsType: ContentsType,
        bookAdapterItem: BookAdapterItem?
    ) {
        arguments = bundleOf().apply {
            putSerializable(BookAdapterItem::class.simpleName, bookAdapterItem)
            putSerializable(ContentsType::class.simpleName, contentsType)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        viewModel.watchNumberTimesListData.forEach {
            binding.includeWatchNumberTimes.chipGroup.addView(makeSelectChip(it))
        }
    }

    private fun initReason() {
        viewModel.reasonListData.forEach {
            binding.includeReason.chipGroup.addView(makeSelectChip(it))
        }
    }

    private fun initSexAge() {
        binding.includeSexAge.run {
            this.spinnerSex.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                viewModel.sexListData
            )

            this.spinnerAge.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                viewModel.ageListData
            )
        }
    }

    private fun bind() {
        binding.run {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            includeWatchNumberTimes.chipGroup.setOnCheckedStateChangeListener(
                watchNumberTimesCheckedListener
            )

            includeSexAge.run {
                spinnerSex.onItemSelectedListener = sexSelectListener
                spinnerAge.onItemSelectedListener = ageSelectListener
            }

            includeReason.chipGroup.setOnCheckedStateChangeListener(reasonCheckedListener)

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