package com.nusademy.nusademy.dataapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient3 {

    private const val MAIN_URL = "https://api.github.com/"

    val retrofitBuilder = Retrofit.Builder().baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val instanceUserApi = retrofitBuilder.create(Api::class.java)

}