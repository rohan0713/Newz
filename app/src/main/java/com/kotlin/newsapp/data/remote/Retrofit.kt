package com.kotlin.newsapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Retrofit {

    init {
     println("Retrofit invoked")
    }

//    fun getClient() : String{
//        return "hello"
//    }

    companion object{

        //lazy in Kotlin initializes a property in a lazy manner. Essentially, it creates a reference
        // but only goes for the initialization when the property is used or called for the first time
        // This way, the variable is initialized only once and then its value is cached for further use in the program.
        private val retro by lazy {

            val url = "https://newsapi.org/v2/"

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // Lazy meant to avoid unnecessary object creation.
        // prefer using it with immutable types
        val api: MyApi by lazy {
            retro.create(MyApi::class.java)
        }

//        fun getClient() : String{
//            return "hello"
//        }
    }

    // On the other hand, the = lazy statement holds a reference to the delegate object instead,
// by which you may use the isInitialized() delegation method or access it with the value property

}