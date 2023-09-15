package com.kotlin.newsapp.repositories

import com.kotlin.newsapp.data.model.News
import com.kotlin.newsapp.data.remote.Retrofit
import com.kotlin.newsapp.db.ArticleDatabase
import retrofit2.Response

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews() = Retrofit.api.getNewsByCountry()
}