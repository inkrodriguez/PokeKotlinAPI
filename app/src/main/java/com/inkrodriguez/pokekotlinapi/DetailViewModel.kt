package com.inkrodriguez.pokekotlinapi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inkrodriguez.pokekotlinapi.api.Type
import com.inkrodriguez.pokekotlinapi.api.TypeX
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import com.inkrodriguez.pokekotlinapi.databinding.ActivityDetailPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.math.RoundingMode
import java.sql.Types
import java.text.DecimalFormat
import kotlin.math.log

class DetailViewModel : ViewModel() {

    private var retrofit = Utils()
    var intent: Int = 0
    var namePoke = MutableLiveData<String>()
    var namePokePrevious = MutableLiveData<String>()
    var namePokeLater = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var weight = MutableLiveData<String>()
    var height = MutableLiveData<String>()
    var gender = MutableLiveData<String>()
    var abilities = MutableLiveData<String>()
    var imgPoke = MutableLiveData<String>()
    var id = MutableLiveData<String>()
    var xbinding: ActivityDetailPokemonBinding? = null

    fun formatting(valueResponse: Int): Float{
        return valueResponse / 10f
    }

    fun getDetailPokemon(intent: Int){

        val retrofitData = retrofit.retrofitBuilder.getPokeId(intent)

        retrofitData.enqueue(object : Callback<myDataTwo> {
            @SuppressLint("ResourceAsColor")
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    namePoke.value = responseBody.name

                    weight.value = formatting(responseBody.weight).toString() + " kg"

                    height.value = formatting(responseBody.height).toString() + " m"

                    id.value = "#" + responseBody.id

                    var listaAbilities = responseBody.abilities
                    var listaAbilities2: MutableList<String> = mutableListOf()
                    listaAbilities.forEach {
                        listaAbilities2.add(it.ability.name)
                        var separator = " | "
                        var listanova = java.lang.String.join(separator, listaAbilities2)
                        abilities.value = listanova
                    }

                    var lista2 = responseBody.types
                    var lista3: MutableList<String> = mutableListOf()
                    lista2.forEach {
                        lista3.add(it.type.name)
                        var separator = " | "
                        var listanova = java.lang.String.join(separator, lista3)
                        type.value = "$listanova"

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