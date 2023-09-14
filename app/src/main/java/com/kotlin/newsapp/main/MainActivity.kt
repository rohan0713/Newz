package com.kotlin.newsapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kotlin.newsapp.databinding.ActivityMainBinding
import com.kotlin.newsapp.db.ArticleDatabase
import com.kotlin.newsapp.repositories.NewsRepository
import com.kotlin.newsapp.ui.viewmodels.NewsViewModel
import com.kotlin.newsapp.ui.viewmodels.ViewModelProviderFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProvider = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(NewsViewModel::class.java)

        binding.bottomNavigationView.setupWithNavController(binding.flFrameLayout[0].findNavController())

    }
}