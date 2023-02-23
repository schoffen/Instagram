package com.example.instagram.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.common.model.Post
import com.example.instagram.databinding.ItemPostListBinding

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    var items: List<Post> = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemPostListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FeedViewHolder(private val binding: ItemPostListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {

            Glide.with(itemView.context).load(post.photoUrl).into(binding.homeImgPost)
            Glide.with(itemView.context).load(post.publisher?.photoUrl).into(binding.homeImgUser)

            binding.homeTxtCaption.text = post.caption
            binding.homeTxtUsername.text = post.publisher?.name
        }
    }
}