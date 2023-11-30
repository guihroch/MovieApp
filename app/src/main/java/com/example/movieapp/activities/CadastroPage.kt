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

class CadastroPage : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastroPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FF000000")

        binding.buttonContinuar.setOnClickListener {
            validarEmailCadastro()
        }

        binding.buttonCadastrar.setOnClickListener {
            //Finalizar com toast customizada
            //Retornar automaticamente para a loginPage
         autenticarCadastro()
        }


    }

    private fun logicaProgressBarCadastro() {
        binding.textInformeEmail.text = "Finalize seu cadastro informando a senha e clique no botão de cadastrar"
        binding.textSenha.visibility = View.VISIBLE
        binding.editTextSenha.visibility = View.VISIBLE
        binding.buttonContinuar.visibility = View.GONE
        binding.buttonCadastrar.visibility = View.VISIBLE
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
            },2000)
        } else {
            logicaProgressBarCadastro()
        }
    }

    private fun autenticarCadastro(){
        val senha = binding.editSenha.text.toString()
        if (senha.isEmpty()){
            binding.editTextSenha.helperText = "Senha é obrigatório"
            binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")


        } else {
            binding.containerProgressbar.visibility = View.VISIBLE
            binding.buttonCadastrar.visibility = View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.containerProgressbar.visibility = View.GONE
                binding.buttonCadastrar.visibility = View.VISIBLE
                toastSucesso()
            }, 2000)
        }
    }

}