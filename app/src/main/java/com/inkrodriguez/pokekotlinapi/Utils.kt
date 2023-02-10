package com.inkrodriguez.pokekotlinapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

}