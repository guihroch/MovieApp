package com.example.movieapp.api

import com.example.movieapp.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("filmes")
    fun listaCategorias(): Call<Categorias>
}