package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("capas") val filme: MutableList<Filme> = mutableListOf()
)

data class Filme(
    @SerializedName("nome") val nome:String? = null,
    @SerializedName("url_imagem") val capa: String? = null,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("descricao") val descricao: String? = null,
    @SerializedName("elenco") val elenco: String? = null
)

data class Categorias(
    @SerializedName("categoria") val categoria: MutableList<Categoria>
)
