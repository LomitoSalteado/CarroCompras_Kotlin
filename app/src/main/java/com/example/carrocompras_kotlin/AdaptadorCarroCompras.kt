package com.example.carrocompras_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCarroCompras(
    var tvTotal: TextView,
    var carroCompras: ArrayList<Producto>
): RecyclerView.Adapter<AdaptadorCarroCompras.ViewHolder>() {

    var total: Double = 0.0

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNomProducto = itemView.findViewById(R.id.tvNomProducto) as TextView
        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        val tvPrecio = itemView.findViewById(R.id.tvPrecio) as TextView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_carro_compras, parent, false)

        total = 0.0

        carroCompras.forEach {
            total += it.precio
        }

        tvTotal.text = "$$total"

        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = carroCompras[position]

        holder.tvNomProducto.text = producto.nomProducto
        holder.tvDescripcion.text = producto.descripcion
        holder.tvPrecio.text = "$${producto.precio}"
    }

    override fun getItemCount(): Int {
        return carroCompras.size
    }

}