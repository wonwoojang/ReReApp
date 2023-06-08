package com.jww.rereapp.main.book.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentBookBinding
import com.jww.rereapp.databinding.ItemBookListBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.ProductAdapterItem
import com.jww.rereapp.main.book.BookViewModel
import com.jww.rereapp.product_detail.ui.ProductDetailContract
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFragment : BaseFragment() {

    private var _binding: FragmentBookBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by viewModel<BookViewModel>()

    private val launcherProductDetail = registerForActivityResult(ProductDetailContract()) {

    }

    private val adapter by lazy {
        Adapter {
            launcherProductDetail.launch(ProductDetailContract.Input(it))

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(
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
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            recyclerView.adapter = adapter

            search.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    iconSearch.performClick()
                false
            }
            iconSearch.throttleClick {
                submitPage()
            }
        }
    }

    private fun observe() {
        repeatOnStarted {
            viewModel.eventFlow().collectLatest {
                handle(it)
            }
        }
    }

    private fun submitPage() {
        lifecycleScope.launch {
            adapter.submitData(PagingData.empty())
            viewModel.searchBookFlow().collect {
                adapter.submitData(it)
            }
        }
    }

    private fun handle(event: BookViewModel.Event) {
        when (event) {
            else -> Unit
        }
    }

    class Adapter(private val action: (ProductAdapterItem.BookAdapterItem) -> Unit) :
        PagingDataAdapter<ProductAdapterItem.BookAdapterItem, Adapter.ViewHolder>(object :
            DiffUtil.ItemCallback<ProductAdapterItem.BookAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: ProductAdapterItem.BookAdapterItem,
                newItem: ProductAdapterItem.BookAdapterItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductAdapterItem.BookAdapterItem,
                newItem: ProductAdapterItem.BookAdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }) {
        class ViewHolder(private val binding: ItemBookListBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun onBind(item: ProductAdapterItem.BookAdapterItem) {
                binding.item = item
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position) ?: return
            holder.onBind(item)
            holder.itemView.throttleClick {
                action(item)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemBookListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}