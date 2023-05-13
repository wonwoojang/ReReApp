package com.jww.rereapp.main.webToon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentWebToonBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.main.webToon.WebToonViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebToonFragment : BaseFragment() {

    private var _binding: FragmentWebToonBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by viewModel<WebToonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebToonBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
    }

    private fun bind() {
        binding.run {
            lifecycleOwner = viewLifecycleOwner

        }
    }

    private fun observe() {
        repeatOnStarted {
            viewModel.eventFlow().collectLatest {
                handle(it)
            }
        }
    }

    private fun handle(event: WebToonViewModel.Event) {
        when (event) {
            else -> Unit
        }
    }
}