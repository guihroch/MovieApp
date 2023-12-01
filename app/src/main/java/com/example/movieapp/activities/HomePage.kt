package com.example.movieapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.adapter.AdapterCategoria
import com.example.movieapp.api.RetrofitService
import com.example.movieapp.databinding.ActivityHomePageBinding
import com.example.movieapp.model.Categoria
import com.example.movieapp.model.Categorias
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var adapterCategoria: AdapterCategoria
    private val listaCategoria: MutableList<Categoria> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        getMovies()
        recyclerViewConfig()
        binding.iconLogout.setOnClickListener {
            logout()
        }



    }

    private fun getMovies() {
        //Config Retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(RetrofitService::class.java)

        retrofit.listaCategorias().enqueue(object : Callback<Categorias> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if (response.code() == 200) {
                    response.body().let {
                        adapterCategoria.listaCategoria.addAll(it!!.categoria)
                        adapterCategoria.notifyDataSetChanged()

                    }
                    binding.containerLoading.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {

            }
        })
    }

    private fun recyclerViewConfig() {
        val recyclerViewCategoria = binding.recyclerViewFilmes
        recyclerViewCategoria.setHasFixedSize(true)
        recyclerViewCategoria.layoutManager = LinearLayoutManager(this)
        adapterCategoria = AdapterCategoria(this, listaCategoria)
        recyclerViewCategoria.adapter = adapterCategoria
    }

    private fun logout(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}