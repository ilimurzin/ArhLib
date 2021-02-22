package ru.arhlib.app.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.arhlib.app.databinding.PostFragmentBinding

class PostFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = PostFragmentBinding.inflate(inflater, container, false)
        val adapter = PostAdapter()

        binding.postList.adapter = adapter.withLoadStateFooter(PostLoadStateAdapter(adapter))

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest(adapter::submitData)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading

                binding.retryBlock.isVisible = loadStates.refresh is LoadState.Error

                if (loadStates.refresh !is LoadState.Loading) {
                    binding.swipeRefresh.isRefreshing = false
                }

                if (loadStates.refresh is LoadState.Error) {
                    binding.postList.isVisible = false
                }
                if (loadStates.refresh is LoadState.NotLoading) {
                    binding.postList.isVisible = true
                }
            }
        }

        binding.retryButton.setOnClickListener { adapter.retry() }

        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }

        return binding.root
    }
}
