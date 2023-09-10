package com.kotlin.newsapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofit {

    init {
     println("Retrofit invoked")
    }

    private const val url = "https://newsapi.org/v2/"

    fun getClient() : MyApi {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MyApi::class.java)
    }
}