package com.example.helsanf.footballmatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.helsanf.footballmatch.R
import com.example.helsanf.footballmatch.model.Event
import java.text.ParseException
import java.text.SimpleDateFormat

class MatchAdapter(private val match: List<Event> , private val listener : (Event) -> Unit) : RecyclerView.Adapter<MatchHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MatchHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_match, viewGroup, false)
        return MatchHolder(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }
}

class MatchHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val homeTeam: TextView = itemview.findViewById(R.id.homeTeams)
    val awayTeam : TextView = itemview.findViewById(R.id.awayTeams)
    val tanggal : TextView = itemview.findViewById(R.id.tanggalMatch)
    val scoreHome : TextView = itemview.findViewById(R.id.scoreHome)
    val scoreAway : TextView = itemview.findViewById(R.id.scoreAway)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(match: Event , listener: (Event) -> Unit) {
        val getDate : String = match.dateEvent

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = simpleDateFormat.parse(getDate)
            val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
            val dateFix = newFormat.format(date)
            tanggal.text = dateFix
        } catch (e: ParseException) {
            e.printStackTrace()
        }

            scoreAway.text = match.intAwayScore
            scoreHome.text = match.intHomeScore


        awayTeam.text = match.strAwayTeam
        homeTeam.text = match.strHomeTeam

        itemView.setOnClickListener { listener(match) }
    }
}
