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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        window.statusBarColor = Color.parseColor("#FF000000")



        binding.toCadastroPage.setOnClickListener {
            val intent = Intent(this, CadastroPage::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            autenticarUsuario(email, senha)
        }
    }

    private fun autenticarUsuario(email: String, senha: String) {
         auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { login ->
            if (login.isSuccessful){
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            } else {

            }
        }
       /* val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        binding.buttonLogin.visibility = View.GONE
        binding.containerProgressbarLogin.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.buttonLogin.visibility = View.VISIBLE
            binding.containerProgressbarLogin.visibility = View.GONE
        }, 2000)
   */ }


    }
