package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.activities.DetalhesPage
import com.example.movieapp.databinding.ItemFilmeBinding
import com.example.movieapp.model.Filme

class AdapterFilme(private val context: Context, private val listafilme: MutableList<Filme>) :
    RecyclerView.Adapter<AdapterFilme.AdapterFilmeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilmeViewHolder {
        val listaFilmes = ItemFilmeBinding.inflate(LayoutInflater.from(context), parent, false)
        return AdapterFilmeViewHolder(listaFilmes)
    }

    override fun getItemCount(): Int {
        return listafilme.size
    }

    override fun onBindViewHolder(holder: AdapterFilmeViewHolder, position: Int) {
        Glide.with(context).load(listafilme[position].capa).centerCrop().into(holder.capa)
        holder.capa.setOnClickListener {
            val intent = Intent(context, DetalhesPage::class.java)
            intent.putExtra("url_imagem", listafilme[position].capa)
            intent.putExtra("nome", listafilme[position].nome)
            intent.putExtra("descricao", listafilme[position].descricao)
            intent.putExtra("elenco", listafilme[position].elenco)
            context.startActivity(intent)
        }
    }

    inner class AdapterFilmeViewHolder(binding: ItemFilmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val capa = binding.capaFilme

    }
}