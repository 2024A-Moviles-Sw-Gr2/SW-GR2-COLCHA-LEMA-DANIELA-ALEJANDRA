package com.example.deber_03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CAdapterBuscar(
    private val lista: ArrayList<CExploreItem>
): RecyclerView.Adapter<CAdapterBuscar.MyViewHolder>(){

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        val left: ImageView = view.findViewById(R.id.img_left)
        val right: ImageView = view.findViewById(R.id.img_right)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.explore_view, parent, false)

        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = this.lista[position]
        holder.left.setImageResource(item.left)
        holder.right.setImageResource(item.right)

    }
}