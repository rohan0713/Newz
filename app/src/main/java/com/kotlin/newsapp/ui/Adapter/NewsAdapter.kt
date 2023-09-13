package com.kotlin.newsapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.newsapp.data.model.Article
import com.kotlin.newsapp.data.model.News
import com.kotlin.newsapp.databinding.ArticleItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val list: List<Article>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    lateinit var binding: ArticleItemBinding
    inner class NewsViewHolder(itemview: ArticleItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        fun bind(news: Article) {
            Picasso.get().load(news.urlToImage).into(binding.ivArticleImage)
            binding.tvDescription.text = news.description
            binding.tvPublishedAt.text = news.publishedAt
            binding.tvTitle.text = news.title
            binding.tvSource.text = news.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

}