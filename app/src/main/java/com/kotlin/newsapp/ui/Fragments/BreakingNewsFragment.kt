package com.kotlin.newsapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.newsapp.R
import com.kotlin.newsapp.data.model.News
import com.kotlin.newsapp.data.remote.Retrofit
import com.kotlin.newsapp.databinding.FragmentBreakingNewsBinding
import com.kotlin.newsapp.ui.Adapter.NewsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreakingNewsFragment : Fragment() {

    lateinit var binding: FragmentBreakingNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBreakingNewsBinding.inflate(layoutInflater)

        lifecycleScope.launch(Dispatchers.IO) {
            val response = Retrofit.getClient().getNewsByCountry()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    val news : News = response.body()!!
                    val adapter = NewsAdapter(news.articles)
                    binding.rvBreakingNews.layoutManager = LinearLayoutManager(activity)
                    binding.rvBreakingNews.adapter = adapter
                }
            }
        }
        return binding.root
    }
}