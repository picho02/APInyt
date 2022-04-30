package com.example.apinyt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apinyt.databinding.ItemArticuloBinding
import com.squareup.picasso.Picasso

class Adapter(val articulos: List<Resultado>,val onItemListener: Adapter.OnItemListener): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemArticuloBinding.inflate(inflate)
        return MainViewHolder(binding,onItemListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val articulo:Resultado = articulos.get(position)
        holder.render(articulo)

    }

    override fun getItemCount(): Int {
        return articulos.size
    }

    interface OnItemListener {
        fun onItemClick(noticia:Resultado)
    }
}

class MainViewHolder(binding: ItemArticuloBinding,onItemListener: Adapter.OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private val binding = binding
    private val onItemListener = onItemListener
    private lateinit var noticia: Resultado
    init {
        binding.root.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        onItemListener.onItemClick(noticia)
    }
    fun render(articulo:Resultado){
        noticia = articulo
        binding.tvTitulo.text = articulo.title
        binding.tvFecha.text = articulo.fecha
        Picasso.get().load(articulo.media.first().media_metadata.first().urlFoto).into(binding.imageView)

    }

}


