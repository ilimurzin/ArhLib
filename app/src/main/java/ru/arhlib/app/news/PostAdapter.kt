package ru.arhlib.app.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.arhlib.app.databinding.PostItemBinding

class PostAdapter : PagingDataAdapter<Post, PostViewHolder>(PostDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldPost: Post, newPost: Post): Boolean {
            return oldPost.id == newPost.id
        }

        override fun areContentsTheSame(oldPost: Post, newPost: Post): Boolean {
            return oldPost == newPost
        }
    }
}
