package com.example.helsanf.footballmatch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.helsanf.footballmatch.R
import com.example.helsanf.footballmatch.model.LigaStatis
import com.squareup.picasso.Picasso

class LeugueStatisAdapter(private val context: Context , private val items : List<LigaStatis> , private val listener:(LigaStatis)->Unit)
    : RecyclerView.Adapter<LeugueStatisAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(viewgroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league_row, viewgroup, false))
    }

    override fun getItemCount(): Int {
     return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)

    }

    class ViewHolder(itemsView : View) : RecyclerView.ViewHolder(itemsView) {
        val ligaBadge: ImageView = itemView.findViewById(R.id.imgLiga)
        val ligaName: TextView = itemView.findViewById(R.id.nameLeague)
        fun bindItem(items : LigaStatis , listener: (LigaStatis) -> Unit){
            items.image?.let { Picasso.get().load(it).into(ligaBadge) }
            ligaName.text = items.name
            itemView.setOnClickListener { listener(items) }
        }
    }
}