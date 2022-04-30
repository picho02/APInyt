package com.example.apinyt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class Adapter(val articulos: List<Resultado>): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return MainViewHolder(inflate.inflate(R.layout.item_articulo,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val articulo:Resultado = articulos.get(position)
        holder.render(articulo)
    }

    override fun getItemCount(): Int {
        return articulos.size
    }

}

class MainViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    fun render(articulo:Resultado){
        view.findViewById<TextView>(R.id.tvTitulo).text = articulo.title
        view.findViewById<TextView>(R.id.tvFecha).text = articulo.fecha
        Picasso.get().load(articulo.media.first().media_metadata.first().urlFoto).into(view.findViewById<ImageView>(R.id.imageView))
    }

}
