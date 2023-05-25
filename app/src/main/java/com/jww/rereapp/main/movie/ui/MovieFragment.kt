package com.jww.rereapp.main.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentMovieBinding
import com.jww.rereapp.databinding.ItemMovieListBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.extension.throttleClick
import com.jww.rereapp.item_model.MovieAdapterItem
import com.jww.rereapp.main.movie.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<MovieViewModel>()

    private val adapter by lazy { Adapter() }

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
                viewLifecycleOwner.repeatOnStarted {
                    adapter.submitData(PagingData.empty())
                    viewModel.searchMovieFlow().collectLatest {
                        adapter.submitData(it)
                    }
                }
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

    private fun handle(event: MovieViewModel.Event) {
        when (event) {
            else -> Unit
        }
    }

    class Adapter : PagingDataAdapter<MovieAdapterItem, Adapter.ViewHolder>(object :
        DiffUtil.ItemCallback<MovieAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: MovieAdapterItem,
            newItem: MovieAdapterItem
        ): Boolean {
            return oldItem.movieSeq == newItem.movieSeq
        }

        override fun areContentsTheSame(
            oldItem: MovieAdapterItem,
            newItem: MovieAdapterItem
        ): Boolean {
            return oldItem == newItem
        }
    }) {
        class ViewHolder(private val binding: ItemMovieListBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun onBind(item: MovieAdapterItem) {
                binding.item = item
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position) ?: return
            holder.onBind(item)
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