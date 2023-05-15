package com.jww.rereapp.main.webToon.ui

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
import com.jww.rereapp.databinding.FragmentWebToonBinding
import com.jww.rereapp.databinding.ItemWebToonListBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.itemModel.WebToonAdapterItem
import com.jww.rereapp.main.webToon.WebToonViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebToonFragment : BaseFragment() {

    private var _binding: FragmentWebToonBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by viewModel<WebToonViewModel>()

    private val adapter by lazy { Adapter() }

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
            viewModel.searchWebToonFlow().collect {
                adapter.submitData(it)
            }
        }
    }

    private fun handle(event: WebToonViewModel.Event) {
        when (event) {
            else -> Unit
        }
    }

    class Adapter : PagingDataAdapter<WebToonAdapterItem, Adapter.ViewHolder>(object :
        DiffUtil.ItemCallback<WebToonAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: WebToonAdapterItem,
            newItem: WebToonAdapterItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WebToonAdapterItem,
            newItem: WebToonAdapterItem
        ): Boolean {
            return oldItem == newItem
        }
    }) {
        class ViewHolder(private val binding: ItemWebToonListBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun onBind(item: WebToonAdapterItem) {
                binding.item = item
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position) ?: return
            holder.onBind(item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemWebToonListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}