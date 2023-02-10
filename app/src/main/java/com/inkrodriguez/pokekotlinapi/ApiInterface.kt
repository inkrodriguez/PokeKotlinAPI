package com.inkrodriguez.pokekotlinapi

import com.inkrodriguez.pokekotlinapi.api.myData
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiInterface {

    @GET("pokemon")
    fun getCount(): Call<myData>

    @GET("pokemon/{nomepoke}")
    fun getPokeInfo(@Path("nomepoke")nomepoke: String): Call<myDataTwo>

    @GET("pokemon/{id}")
    fun getPokeId(@Path("id")id: Int): Call<myDataTwo>




}