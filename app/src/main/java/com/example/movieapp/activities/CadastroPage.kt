package com.example.movieapp.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityCadastroPageBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastroPage : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroPageBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastroPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FF000000")

        binding.buttonContinuar.setOnClickListener {
            validarEmailCadastro()
        }
        binding.buttonCadastrar.setOnClickListener {
            cadastroFirebase()
        }


    }


    private fun toLoginPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toastSucesso() {
        //Fazer toast erro
        val view =
            LayoutInflater.from(this).inflate(R.layout.toast_customizada_sucesso, null, false)
        val toastSucesso = Toast(this)
        toastSucesso.view = view
        toastSucesso.duration = Toast.LENGTH_SHORT
        toastSucesso.show()
    }

    fun customToastError() {
        val view = LayoutInflater.from(this).inflate(R.layout.toast_customizada_error, null, false)
        val toastErro = Toast(this)
        toastErro.view = view
        toastErro.duration = Toast.LENGTH_SHORT
        toastErro.show()
    }

    private fun validarEmailCadastro() {
        val email = binding.editEmail.text.toString()
        if (email.isEmpty()) {
            binding.editTextEmail.helperText = "Email é obrigatório"
            binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
            Handler(Looper.getMainLooper()).postDelayed({
                binding.editTextEmail.helperText = ""
                binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
            }, 2000)
        } else {
            binding.textInformeEmail.text =
                "Finalize seu cadastro informando a senha e clique no botão de cadastrar"
            binding.textSenha.visibility = View.VISIBLE
            binding.editTextSenha.visibility = View.VISIBLE
            binding.buttonContinuar.visibility = View.GONE
            binding.buttonCadastrar.visibility = View.VISIBLE
        }
    }


    private fun cadastroFirebase() {
        val emailCadastro = binding.editEmail.text.toString()
        val senhaCadastro = binding.editSenha.text.toString()
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro)
            .addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    binding.containerProgressbar.visibility = View.VISIBLE
                    binding.buttonCadastrar.visibility = View.GONE
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.containerProgressbar.visibility = View.GONE
                        binding.buttonCadastrar.visibility = View.VISIBLE
                        toastSucesso()
                    }, 2000)
                }
            }.addOnFailureListener {
                val erro = it
                when {
                    erro is FirebaseAuthWeakPasswordException -> {
                        binding.editTextSenha.helperText =
                            "A senha deve ter pelo menos 6 caracteres"
                        binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.editTextSenha.helperText = ""
                            binding.editTextSenha.boxStrokeColor = Color.parseColor("#171515")
                        }, 2000)
                    }

                    erro is FirebaseAuthUserCollisionException -> {
                        binding.editTextEmail.helperText = "Este usuário ja foi cadastrado!"
                        binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.editTextEmail.helperText = ""
                            binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
                        }, 2000)
                    }

                    erro is FirebaseNetworkException -> {
                        Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        customToastError()
                    }
                }
            }
    }
}