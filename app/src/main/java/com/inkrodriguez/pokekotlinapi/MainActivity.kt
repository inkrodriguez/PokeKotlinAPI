package com.inkrodriguez.pokekotlinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.inkrodriguez.pokekotlinapi.api.myData
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import com.inkrodriguez.pokekotlinapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://pokeapi.co/api/v2/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            getmydata()
        }
    }


    private fun getmydata(){

        var editPoke = binding.editPoke.text

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        val retrofitData2 = retrofitBuilder.getPokeInfo(editPoke.toString())

        retrofitData2.enqueue(object: Callback<myDataTwo>{
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                var responseBody = response.body()!!

                binding.tvPoke.text = responseBody.name
                Glide.with(this@MainActivity).load(responseBody.sprites.front_default).into(binding.imageView)
            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                Toast.makeText(applicationContext, "Não existe!", Toast.LENGTH_SHORT).show()
            }
        })

//        retrofitData.enqueue(object: Callback<myData> {
//            override fun onResponse(call: Call<myData>, response: Response<myData>) {
//
//                var responseBody = response.body()!!
//
//                responseBody.results.forEach {
//                    binding.tvPoke.text = "${it.name} e ${it.url}"
//                }
//
//            }
//
//            override fun onFailure(call: Call<myData>, t: Throwable) {
//                Toast.makeText(applicationContext, "$t", Toast.LENGTH_SHORT).show()
//            }
//        })

    }

}