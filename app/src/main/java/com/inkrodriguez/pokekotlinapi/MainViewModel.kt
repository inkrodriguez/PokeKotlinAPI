package com.inkrodriguez.pokekotlinapi

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import com.inkrodriguez.pokekotlinapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    @SuppressLint("StaticFieldLeak")
    val getCtx: Context? = null
    var getBinding: ActivityMainBinding? = null
    var retrofit = Utils()


     fun getMyData() {

         val editPoke = getBinding?.editPoke?.text
         val tvPoke = getBinding?.tvPoke
         val imagem = getBinding?.imageView
         val retrofitData = retrofit.retrofitBuilder.getPokeInfo(editPoke.toString())

        retrofitData.enqueue(object : Callback<myDataTwo> {
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                var responseBody = response.body()!!

                tvPoke?.text = responseBody.name
                Glide.with(getCtx!!.applicationContext).load(responseBody.sprites.front_default)
                    .into(imagem!!)
            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                Toast.makeText(getCtx?.applicationContext, "Não existe!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}