package ru.arhlib.app.actual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.arhlib.app.databinding.ActualFragmentBinding

class ActualFragment : Fragment() {
    private val viewModel: ActualViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = ActualFragmentBinding.inflate(inflater, container, false)
        val adapter = ActualItemAdapter()
        viewModel.actualItems.observe(viewLifecycleOwner, { result ->
            if (result is LoadResult.Success) {
                adapter.submitList(result.data)
                binding.emptyActualText.isVisible = result.data.isEmpty()
                binding.actualList.isVisible = result.data.isNotEmpty()
            }
            if (result is LoadResult.Error) {
                binding.actualList.isVisible = false
            }
            binding.progressBar.isVisible = result is LoadResult.Loading
            binding.retryBlock.isVisible = result is LoadResult.Error
            binding.swipeRefresh.isRefreshing = result is LoadResult.Refreshing
        })
        binding.actualList.adapter = adapter
        binding.retryButton.setOnClickListener {
            viewModel.load()
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        return binding.root
    }
}
