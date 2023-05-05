package com.jww.rereapp.main.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.common.models.Movie
import com.jww.rereapp.databinding.FragmentMovieBinding
import com.jww.rereapp.databinding.ItemMovieListBinding
import com.jww.rereapp.extension.repeatOnStarted
import com.jww.rereapp.main.movie.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
            this.vm = viewModel
            this.lifecycleOwner = viewLifecycleOwner

            this.recycler.adapter = adapter

            this.iconSearch.setOnClickListener {
                viewModel.searchMovie()
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
            is MovieViewModel.Event.ResultMovieData -> {
                event.movieList?.let {
                    lifecycleScope.launch {
                        adapter.submitData(PagingData.from(it))
                    }
                }
            }
            else -> Unit
        }
    }

    class Adapter : PagingDataAdapter<Movie.Result, Adapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Movie.Result>() {
        override fun areItemsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie.Result, newItem: Movie.Result): Boolean {
            return oldItem.movieId == newItem.movieId
        }
    }) {
        class ViewHolder(private val binding: ItemMovieListBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun onBind(item: Movie.Result) {
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