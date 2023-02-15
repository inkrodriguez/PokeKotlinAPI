package com.inkrodriguez.pokekotlinapi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.inkrodriguez.pokekotlinapi.databinding.ActivityDetailPokemonBinding

class DetailActivityPokemon : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailPokemonBinding
    var id = 0

    fun recuperarIntent(){
        id = intent.getIntExtra("id", 0)
        viewModel.intent = id
    }

    override fun onStart() {
        super.onStart()
        recuperarIntent()
        viewModel.getDetailPokemon(id)
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel.xbinding = this.binding
        recuperarIntent()

        viewModel.namePoke.observe(this, Observer {
            binding.tvName.text = it.toString()
        })

        viewModel.type.observe(this, Observer {
            binding.tvType.text = it.toString()
        })

        viewModel.imgPoke.observe(this, Observer {
            Glide.with(this).load(it).into(binding.imagePoke)
        })

        viewModel.id.observe(this, Observer {
            binding.tvId.text = it.toString()
        })

        viewModel.type.observe(this, Observer {
            binding.tvType.text = it.toString()
        })

        viewModel.abilities.observe(this, Observer {
            binding.tvAbilities.text = it.toString()
        })

        viewModel.weight.observe(this, Observer {
            binding.tvWeight.text = it.toString()
        })

        viewModel.height.observe(this, Observer {
            binding.tvHeight.text = it.toString()
        })

    }

}