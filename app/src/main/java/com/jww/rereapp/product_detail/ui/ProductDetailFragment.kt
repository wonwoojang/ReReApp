package com.jww.rereapp.product_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentProductDetailBinding
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.re_evaluate.ui.ReEvaluateContract

class ProductDetailFragment : BaseFragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!

    private val launcherReEvaluate = registerForActivityResult(ReEvaluateContract()) {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
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
            this.register.throttleClick {
//                launcherReEvaluate.launch(
//                    ReEvaluateContract.Input(
//                        ContentsType.BOOK,
//                        null
//                    )
//            )
            }
        }
    }

    private fun observe() {

    }
}