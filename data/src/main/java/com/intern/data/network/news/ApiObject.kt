package com.intern.data.network.news

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiObject {
    private const val BASE_URL: String = "https://newsapi.org/v2/"

    val api: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}
