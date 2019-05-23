package com.example.helsanf.footballmatch.prevmatch

import com.example.helsanf.footballmatch.model.Event

interface PrevView {
    fun showLoading()
    fun hideLoading()
    fun ShowMatchList(data : List<Event>)
}