package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.movieapp.activities.CadastroPage
import com.example.movieapp.activities.HomePage
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        autenticacaoUsuario()

        binding.toCadastroPage.setOnClickListener {
            val intent = Intent(this, CadastroPage::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
        autenticacaoUsuario()
        }
    }
    private fun autenticacaoUsuario(){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
       /* binding.buttonLogin.visibility = View.GONE
        binding.containerProgressbarLogin.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.buttonLogin.visibility = View.VISIBLE
            binding.containerProgressbarLogin.visibility = View.GONE
        }, 2000)
   */ }
}