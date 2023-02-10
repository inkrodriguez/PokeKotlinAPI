package com.inkrodriguez.pokekotlinapi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.setContext(applicationContext)
        viewModel.getBinding = this.binding

        binding.btnBack.setOnClickListener {
            viewModel.backPoke()
        }

        binding.btnNext.setOnClickListener {
            viewModel.nextPoke()
        }

        binding.button.setOnClickListener {
            if(binding.editPoke.text.isEmpty()){
                Toast.makeText(this, "Nenhum dado foi inserido!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getMyData()
            }
        }

        binding.imgPoke.setOnClickListener {
            startActivity(Intent(this@MainActivity, DetailActivityPokemon::class.java).putExtra("id", viewModel.id))
        }


    }
}

