package com.example.movieapp.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ActivityDetalhesPageBinding

class DetalhesPage : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")


        val nome = intent.extras?.getString("nome")
        val capa = intent.extras?.getString("url_imagem")
        val descricao = intent.extras?.getString("descricao")
        val elenco = intent.extras?.getString("elenco")

        binding.nomeFilme.text = nome
        Glide.with(this).load(capa).centerCrop().into(binding.capaFilme)
        binding.descricaoFilme.text = descricao
        binding.elencoFilme.text = "$elenco..."

        binding.icArrowBackToHomePage.setOnClickListener {
            backToHomePage()
        }


    }

    private fun backToHomePage(){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        finish()
    }
}