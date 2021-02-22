package ru.arhlib.app.news

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ru.arhlib.app.databinding.LoadStateItemBinding

class PostLoadStateViewHolder(
        binding: LoadStateItemBinding,
        postAdapter: PostAdapter
) : RecyclerView.ViewHolder(binding.root) {
    private val progressBar = binding.progressBar
    private val errorText = binding.errorText
    private val retryButton = binding.button
            .also { it.setOnClickListener { postAdapter.retry() } }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        errorText.isVisible = loadState is LoadState.Error
        retryButton.isVisible = loadState is LoadState.Error
    }
}
