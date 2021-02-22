package ru.arhlib.app.actual

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.arhlib.app.R
import ru.arhlib.app.browser.CustomTabs
import ru.arhlib.app.databinding.ActualItemBinding
import ru.arhlib.app.databinding.PostItemBinding
import ru.arhlib.app.news.Post
import ru.arhlib.app.news.PostActivity

class ActualItemAdapter : ListAdapter<ActualItem, ActualItemAdapter.ActualItemViewHolder>(ActualItemDiffCallback()) {
    companion object {
        private const val TYPE_LINK = 10
        private const val TYPE_POST = 20
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ActualLink -> TYPE_LINK
            is Post -> TYPE_POST
            else -> throw RuntimeException("Неподдерживаемый адаптером тип")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualItemViewHolder {
        return when (viewType) {
            TYPE_LINK -> ActualViewHolder(ActualItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_POST -> PostViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw RuntimeException("Тип должен быть задан.")
        }
    }

    override fun onBindViewHolder(holder: ActualItemViewHolder, position: Int) {
        when (holder) {
            is ActualViewHolder -> holder.bind(getItem(position) as ActualLink)
            is PostViewHolder -> holder.bind(getItem(position) as Post)
            else -> throw RuntimeException("Неподдерживаемый адаптером holder")
        }
    }

    abstract class ActualItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private class ActualViewHolder(private val binding: ActualItemBinding) : ActualItemViewHolder(binding.root) {
        fun bind(actualLink: ActualLink) {
            binding.root.setOnClickListener { view: View -> CustomTabs.openUrl(view.context, actualLink.link) }
            binding.emoji.text = actualLink.emoji
            binding.emoji.isGone = TextUtils.isEmpty(actualLink.emoji)
            binding.title.text = actualLink.title
            binding.description.text = actualLink.description
            binding.description.isGone = TextUtils.isEmpty(actualLink.description)
        }
    }

    private class PostViewHolder(private val binding: PostItemBinding) : ActualItemViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postTitle.text = post.getTitle()
            binding.postExcerpt.text = post.getExcerpt()
            binding.postDate.text = post.getDateFormatted()
            if (post.getImageUrl().startsWith("http")) {
                Picasso.get()
                        .load(post.getImageUrl())
                        .centerCrop()
                        .fit()
                        .placeholder(R.color.colorPlaceholder)
                        .into(binding.postImage)
                binding.postImage.isVisible = true
            } else {
                binding.postImage.isVisible = false
            }
            binding.root.setOnClickListener { view: View ->
                val context = view.context
                val intent = Intent(context, PostActivity::class.java)
                intent.putExtra("link", post.link)
                intent.putExtra("title", post.getTitle())
                intent.putExtra("content", post.getContent())
                intent.putExtra("imageUrl", post.getImageUrl())
                intent.putExtra("sourceImageUrl", post.getSourceImageUrl())
                context.startActivity(intent)
            }
        }
    }

    private class ActualItemDiffCallback : DiffUtil.ItemCallback<ActualItem>() {
        override fun areItemsTheSame(oldItem: ActualItem, newItem: ActualItem): Boolean {
            if (oldItem is Post && newItem is Post) {
                return oldItem.id == oldItem.id
            }
            if (oldItem is ActualLink && newItem is ActualLink) {
                return oldItem.title == newItem.title
            }
            return false
        }

        override fun areContentsTheSame(oldItem: ActualItem, newItem: ActualItem): Boolean {
            if (oldItem is Post && newItem is Post) {
                return oldItem.getContent() == newItem.getContent()
            }
            if (oldItem is ActualLink && newItem is ActualLink) {
                return oldItem.title == newItem.title
                        && oldItem.description == newItem.description
                        && oldItem.emoji == newItem.emoji
                        && oldItem.link == newItem.link
            }
            return false
        }
    }
}
