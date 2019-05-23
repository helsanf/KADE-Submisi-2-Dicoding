package com.example.helsanf.footballmatch

import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.model.Teams

interface DetailView {
    fun showloading()
    fun hideloading()
    fun showView()
    fun showDetailEvent(event: Event)
    fun showHomeTeam(teams : Teams)
    fun showAwayTeam(teams : Teams)

}