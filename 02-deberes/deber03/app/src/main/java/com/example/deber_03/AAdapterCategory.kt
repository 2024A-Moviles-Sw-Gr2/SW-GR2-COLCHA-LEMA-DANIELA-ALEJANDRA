package com.example.deber_03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class AAdapterCategory(
    private val lista: ArrayList<String>,
) : RecyclerView.Adapter<AAdapterCategory.MyViewHolder>()
{

    inner class MyViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view){
        val categoryBtn: Button = view.findViewById(R.id.btn_category)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.categories_view, parent, false)

        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.categoryBtn.text = this.lista[position]
    }
}