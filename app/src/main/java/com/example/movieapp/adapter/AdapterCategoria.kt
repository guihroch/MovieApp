package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemCategoriaBinding
import com.example.movieapp.model.Categoria

class AdapterCategoria(
    private val context: Context,
    val listaCategoria: MutableList<Categoria>
) : RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val lista = ItemCategoriaBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoriaViewHolder(lista)
    }

    override fun getItemCount(): Int {
        return listaCategoria.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.categoria.text = listaCategoria[position].titulo
        holder.recyclerViewFilmes.adapter = AdapterFilme(context, listaCategoria[position].filme)
        holder.recyclerViewFilmes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    inner class CategoriaViewHolder(binding: ItemCategoriaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoria = binding.textCategoriaFilme
        val recyclerViewFilmes = binding.recyclerViewFilmes

    }
}

