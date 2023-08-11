package com.task.cdse.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "https://gist.githubusercontent.com/khushboojain942/"


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiInterfaceService by lazy {
        retrofit.create(ApiInterfaceService::class.java)
    }
}
