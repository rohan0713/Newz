package com.kotlin.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.newsapp.data.model.News
import com.kotlin.newsapp.data.remote.Retrofit
import com.kotlin.newsapp.databinding.FragmentBreakingNewsBinding
import com.kotlin.newsapp.main.MainActivity
import com.kotlin.newsapp.ui.adapters.NewsAdapter
import com.kotlin.newsapp.ui.viewmodels.NewsViewModel
import com.kotlin.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreakingNewsFragment : Fragment() {

    lateinit var binding: FragmentBreakingNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBreakingNewsBinding.inflate(layoutInflater)

//        lifecycleScope.launch(Dispatchers.IO) {
//            val response = Retrofit.api.getNewsByCountry()
//            withContext(Dispatchers.Main){
//                if(response.isSuccessful){
//                    val news : News = response.body()!!
//                    val adapter = NewsAdapter(news.articles)
//                    binding.rvBreakingNews.layoutManager = LinearLayoutManager(activity)
//                    binding.rvBreakingNews.adapter = adapter
//                }
//            }
//        }

        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { news ->
                        newsAdapter = NewsAdapter(news.articles)
                        binding.rvBreakingNews.adapter = newsAdapter
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message ->
                        Toast.makeText(activity, message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter(listOf())
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.GONE
    }
}