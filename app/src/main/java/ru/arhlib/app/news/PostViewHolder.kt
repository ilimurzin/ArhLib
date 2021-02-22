package ru.arhlib.app.news

import android.content.Intent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.arhlib.app.R
import ru.arhlib.app.databinding.PostItemBinding

class PostViewHolder(
        private val binding: PostItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post?) {
        if (post == null) return

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
        binding.root.setOnClickListener {
            val intent = Intent(it.context, PostActivity::class.java)
            intent.putExtra("link", post.link)
            intent.putExtra("title", post.getTitle())
            intent.putExtra("content", post.getContent())
            intent.putExtra("imageUrl", post.getImageUrl())
            intent.putExtra("sourceImageUrl", post.getSourceImageUrl())
            it.context.startActivity(intent)
        }
    }
}
