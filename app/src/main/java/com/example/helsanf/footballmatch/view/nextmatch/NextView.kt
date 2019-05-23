package com.example.helsanf.footballmatch.nextmatch

import android.content.Intent
import com.example.helsanf.footballmatch.model.Event

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showScheduleList(data : List<Event>)

}