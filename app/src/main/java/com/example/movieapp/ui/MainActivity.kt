package com.example.movieapp.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        window.statusBarColor = Color.parseColor("#171515")



        binding.toCadastroPage.setOnClickListener {
            val intent = Intent(this, CadastroPage::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            validarLogin()
        }
    }

    private fun autenticarUsuario(email: String, senha: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { login ->
            if (login.isSuccessful) {
                binding.buttonLogin.visibility = View.GONE
                binding.containerProgressbarLogin.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.buttonLogin.visibility = View.VISIBLE
                    binding.containerProgressbarLogin.visibility = View.GONE
                    customToastSucesso()
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                }, 2000)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao realizar login", Toast.LENGTH_SHORT).show()
        }
    }


    private fun validarLogin() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        when {
            email.isEmpty() -> {
                binding.editTextEmail.helperText = "Email é obrigatório"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.editTextEmail.helperText = ""
                }, 2000)
            }

            !email.matches(emailRegex.toRegex()) -> {
                binding.editTextEmail.helperText = "Digite um email válido"
                binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.editTextEmail.helperText = ""
                }, 2000)

            }

            senha.isEmpty() || senha.length < 6 -> {
                binding.editTextSenha.helperText = "Digite uma senha válida"
                binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.editTextSenha.helperText = ""
                }, 2000)
            }

            else -> {
                autenticarUsuario(email, senha)
            }
        }
    }

    private fun customToastSucesso() {
        //Fazer toast erro
        val view =
            LayoutInflater.from(this).inflate(R.layout.custom_toast_sucesso_cadastro, null, false)
        val toastSucesso = Toast(this)
        toastSucesso.view = view
        toastSucesso.duration = Toast.LENGTH_SHORT
        toastSucesso.show()
    }

    fun customToastError() {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_toast_error_login, null, false)
        val toastErro = Toast(this)
        toastErro.view = view
        toastErro.duration = Toast.LENGTH_SHORT
        toastErro.show()
    }
}



