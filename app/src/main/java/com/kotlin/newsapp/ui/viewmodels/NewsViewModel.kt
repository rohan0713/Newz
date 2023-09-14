package com.kotlin.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.kotlin.newsapp.repositories.NewsRepository

class NewsViewModel(
    val repository : NewsRepository
) : ViewModel() {
}