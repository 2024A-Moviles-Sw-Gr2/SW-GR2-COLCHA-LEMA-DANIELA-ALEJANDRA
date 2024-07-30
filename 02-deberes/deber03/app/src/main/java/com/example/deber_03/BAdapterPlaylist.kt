package com.example.deber_03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BAdapterPlaylist(
    private val lista: ArrayList<BPlaylist>,
) : RecyclerView.Adapter<BAdapterPlaylist.MyViewHolder>()
{

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.img_playlist)
        val nombre: TextView = view.findViewById(R.id.tv_playlist_name)
        val description: TextView = view.findViewById(R.id.tv_playlist_info)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.playlist_view, parent, false)

        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val playlist = this.lista[position]
        holder.image.setImageResource(playlist.imageResource)
        holder.nombre.text = playlist.nombre
        holder.description.text = playlist.descripcion
    }
}