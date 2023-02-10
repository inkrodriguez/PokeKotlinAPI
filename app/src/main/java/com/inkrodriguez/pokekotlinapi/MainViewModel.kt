package com.inkrodriguez.pokekotlinapi

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.inkrodriguez.pokekotlinapi.api.myData
import com.inkrodriguez.pokekotlinapi.api.myDataTwo
import com.inkrodriguez.pokekotlinapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private lateinit var context: Context

    fun setContext(context: Context){
        this.context = context
    }

    var getBinding: ActivityMainBinding? = null
    private var retrofit = Utils()
    var id = 1
    var novoId = 0
    var qtdPoke = 0


    fun getMyData() {
        var imagem = getBinding?.imgPoke
        var imagemLoading = getBinding!!.progress
        val editPoke = getBinding?.editPoke?.text
        var tvPoke = getBinding?.tvPoke
        val retrofitData = retrofit.retrofitBuilder.getPokeInfo(editPoke.toString())

        Glide.with(context).load(R.drawable.loading).into(imagemLoading)

        retrofitData.enqueue(object : Callback<myDataTwo> {
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    id = responseBody.id
                }

                if(responseBody?.name != null){
                    Toast.makeText(context, "Pokémon encontrado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Não existem arquivos deste pokémon!", Toast.LENGTH_SHORT).show()
                }

                tvPoke?.text = responseBody?.name
                Glide.with(context).load(responseBody?.sprites?.front_default)
                    .into(imagem!!)

                Glide.with(context).load(R.drawable.loadingoff).into(imagemLoading)

            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                Toast.makeText(context, "ERRO!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun nextPoke() {
        var editPoke = getBinding?.editPoke
        var tvPoke = getBinding?.tvPoke
        var imagem = getBinding?.imgPoke
        var imagemLoading = getBinding!!.progress
        novoId = id + 1

        if(novoId == 1009){
            novoId = 1
        }

        Glide.with(context).load(R.drawable.loading).into(imagemLoading)

        val retrofitDataTwo = retrofit.retrofitBuilder.getPokeId(novoId)

        retrofitDataTwo.enqueue(object : Callback<myDataTwo> {
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    id = responseBody.id
                }

                if(responseBody?.name != null){
                    Toast.makeText(context, "Pokémon encontrado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Não existem arquivos deste pokémon!", Toast.LENGTH_SHORT).show()
                }

                editPoke?.setText(responseBody?.name).toString()
                tvPoke?.text = responseBody?.name
                Glide.with(context).load(responseBody?.sprites?.front_default)
                    .into(imagem!!)

                Glide.with(context).load(R.drawable.loadingoff).into(imagemLoading)

            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                Toast.makeText(context, "ERRO!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun backPoke() {
        var editPoke = getBinding?.editPoke
        var tvPoke = getBinding?.tvPoke
        var imagem = getBinding?.imgPoke
        var imagemLoading = getBinding!!.progress
        novoId = id - 1

        if(novoId == 0){
            novoId = 1008
        }

        Glide.with(context).load(R.drawable.loading).into(imagemLoading)

        val retrofitDataTwo = retrofit.retrofitBuilder.getPokeId(novoId)

        retrofitDataTwo.enqueue(object : Callback<myDataTwo> {
            override fun onResponse(call: Call<myDataTwo>, response: Response<myDataTwo>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    id = responseBody.id
                }

                if(responseBody?.name != null){
                    Toast.makeText(context, "Pokémon encontrado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Não existem arquivos deste pokémon!", Toast.LENGTH_SHORT).show()
                }

                editPoke?.setText(responseBody?.name).toString()
                tvPoke?.text = responseBody?.name
                Glide.with(context).load(responseBody?.sprites?.front_default)
                    .into(imagem!!)

                Glide.with(context).load(R.drawable.loadingoff).into(imagemLoading)

            }

            override fun onFailure(call: Call<myDataTwo>, t: Throwable) {
                Toast.makeText(context, "ERRO!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}

