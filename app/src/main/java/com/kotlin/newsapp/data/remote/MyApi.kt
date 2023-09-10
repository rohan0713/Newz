package com.kotlin.newsapp.data.remote

import com.kotlin.newsapp.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("top-headlines?country=us&category=business&apiKey=f0bf5a24e34b46848ca2dc8474197bb6")
    suspend fun getNewsByCountry() : Response<News>
}