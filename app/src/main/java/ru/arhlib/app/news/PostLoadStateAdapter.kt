package ru.arhlib.app.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import ru.arhlib.app.databinding.LoadStateItemBinding

class PostLoadStateAdapter(
        private val postAdapter: PostAdapter
) : LoadStateAdapter<PostLoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PostLoadStateViewHolder {
        val binding = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostLoadStateViewHolder(binding, postAdapter)
    }

    override fun onBindViewHolder(holder: PostLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
