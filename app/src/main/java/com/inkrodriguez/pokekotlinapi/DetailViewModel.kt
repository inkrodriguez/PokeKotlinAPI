package com.inkrodriguez.pokekotlinapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inkrodriguez.pokekotlinapi.api.Type
import com.inkrodriguez.pokekotlinapi.api.TypeX
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.sql.Types
import kotlin.math.log

class DetailViewModel : ViewModel() {

    private var retrofit = Utils()
    var intent: Int = 0
    var namePoke = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var imgPoke = MutableLiveData<String>()
    var id = MutableLiveData<String>()
    var viewBackground = MutableLiveData<String>()

    fun getDetailPokemon(){

        val retrofitData = retrofit.retrofitBuilder.getPokeId(intent)

        retrofitData.enqueue(object : Callback<myDataTwo> {
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    namePoke.value = responseBody.name

                    id.value = "#" + responseBody.id

                    var lista2 = responseBody.types

                    var lista3: MutableList<String> = mutableListOf()

                    lista2.forEach {
                        lista3.add(it.type.name)
                        var separator = " | "
                        var listanova = java.lang.String.join(separator, lista3)
                        type.value = "Type: $listanova"

                    }

                    imgPoke.value = responseBody.sprites.front_default


                }

            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}