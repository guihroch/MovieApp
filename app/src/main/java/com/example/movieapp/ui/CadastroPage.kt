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
            logicaValidarEmailCadastro()
        }
        binding.buttonCadastrar.setOnClickListener {
            binding.containerProgressbar.visibility = View.VISIBLE
            binding.buttonCadastrar.visibility = View.GONE
                cadastroFirebase()
        }

        binding.icArrowBackLoginPage.setOnClickListener {
            toLoginPage()
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
            LayoutInflater.from(this).inflate(R.layout.custom_toast_sucesso_cadastro, null, false)
        val toastSucesso = Toast(this)
        toastSucesso.view = view
        toastSucesso.duration = Toast.LENGTH_LONG
        toastSucesso.show()
    }

    fun customToastError() {
        val view =
            LayoutInflater.from(this).inflate(R.layout.custom_toast_error_cadastro, null, false)
        val toastErro = Toast(this)
        toastErro.view = view
        toastErro.duration = Toast.LENGTH_LONG
        toastErro.show()

    }

    private fun logicaValidarEmailCadastro() {
        val email = binding.editEmail.text.toString()
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

        when {
            email.isEmpty() -> {
                binding.editTextEmail.helperText = "Email é obrigatório!"
                binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.editTextEmail.helperText = ""
                    binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
                }, 2000)
            }

            !email.matches(emailRegex.toRegex()) -> {
                binding.editTextEmail.helperText = "Digite um email válido!"
                binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.editTextEmail.helperText = ""
                    binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
                }, 2000)

            }
            else -> {
                binding.textInformeEmail.text =
                    "Finalize seu cadastro informando a senha e clique no botão de cadastrar"
                binding.textSenha.visibility = View.VISIBLE
                binding.editTextSenha.visibility = View.VISIBLE
                binding.buttonContinuar.visibility = View.GONE
                binding.buttonCadastrar.visibility = View.VISIBLE
            }
        }


    }

    private fun cadastroFirebase() {
        val emailCadastro = binding.editEmail.text.toString()
        val senhaCadastro = binding.editSenha.text.toString()
        auth = FirebaseAuth.getInstance()
        if(senhaCadastro.isEmpty()) {
            binding.editTextSenha.helperText =
                "Senha é obrigatório!"
            binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
            Handler(Looper.getMainLooper()).postDelayed({
                binding.editTextSenha.helperText = ""
                binding.editTextSenha.boxStrokeColor = Color.parseColor("#171515")
            }, 2000)
        } else {
            auth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro)
                .addOnCompleteListener { cadastro ->
                    if (cadastro.isSuccessful) {
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
                            "A senha deve ter pelo menos 6 caracteres!"
                        binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                        binding.containerProgressbar.visibility = View.GONE
                        binding.buttonCadastrar.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.editTextSenha.helperText = ""
                            binding.editTextSenha.boxStrokeColor = Color.parseColor("#171515")
                        }, 2000)
                    }

                    erro is FirebaseAuthUserCollisionException -> {
                        binding.editTextEmail.helperText = "Este usuário ja foi cadastrado!"
                        binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                        binding.containerProgressbar.visibility = View.GONE
                        binding.buttonCadastrar.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.editTextEmail.helperText = ""
                            binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
                        }, 2000)
                    }
                    erro is FirebaseNetworkException -> {
                        Toast.makeText(this, "Sem conexão com a internet!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        customToastError()
                    }
                }
            }
    }
}}