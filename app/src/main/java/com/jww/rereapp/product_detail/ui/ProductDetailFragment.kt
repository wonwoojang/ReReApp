package com.jww.rereapp.product_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.jww.rereapp.R
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentProductDetailBinding
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.ProductAdapterItem
import com.jww.rereapp.product_detail.ProductDetailViewModel
import com.jww.rereapp.re_evaluate.ui.ReEvaluateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailFragment : BaseFragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by viewModel<ProductDetailViewModel> {
        parametersOf(arguments?.getSerializable(ProductAdapterItem::class.simpleName))

    }

    fun putArgument(
        productAdapterItem: ProductAdapterItem?
    ) {
        arguments = bundleOf().apply {
            putSerializable(ProductAdapterItem::class.simpleName, productAdapterItem)
        }
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
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            register.throttleClick {
                parentFragmentManager.beginTransaction()
                    .add(R.id.container, ReEvaluateFragment().apply {
                        putArgument(viewModel.productAdapterItem)
                    }).addToBackStack(null).commit()
            }
        }
    }

    private fun observe() {

    }
}