package com.jww.rereapp.main.movie.ui

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
import com.jww.rereapp.databinding.FragmentMovieBinding
import com.jww.rereapp.databinding.ItemMovieListBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.ProductAdapterItem
import com.jww.rereapp.main.movie.MovieViewModel
import com.jww.rereapp.product_detail.ui.ProductDetailContract
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<MovieViewModel>()

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
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
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

            recycler.adapter = adapter

            search.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    iconSearch.performClick()
                false
            }

            iconSearch.throttleClick {
                lifecycleScope.launch {
                    adapter.submitData(PagingData.empty())
                    viewModel.searchMovieFlow().collect {
                        adapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun observe() {
        repeatOnStarted {
            viewModel.eventFlow().collect {
                handle(it)
            }
        }
    }

    private fun handle(event: MovieViewModel.Event) {
        when (event) {
            else -> Unit
        }
    }

    class Adapter(private val action: (ProductAdapterItem.MovieAdapterItem) -> Unit) :
        PagingDataAdapter<ProductAdapterItem.MovieAdapterItem, Adapter.ViewHolder>(object :
            DiffUtil.ItemCallback<ProductAdapterItem.MovieAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: ProductAdapterItem.MovieAdapterItem,
                newItem: ProductAdapterItem.MovieAdapterItem
            ): Boolean {
                return oldItem.movieSeq == newItem.movieSeq
            }

            override fun areContentsTheSame(
                oldItem: ProductAdapterItem.MovieAdapterItem,
                newItem: ProductAdapterItem.MovieAdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }) {
        class ViewHolder(private val binding: ItemMovieListBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun onBind(item: ProductAdapterItem.MovieAdapterItem) {
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
                ItemMovieListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}