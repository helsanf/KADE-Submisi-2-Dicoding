package com.example.helsanf.footballmatch.rest

import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.model.LeagueResponse
import com.example.helsanf.footballmatch.model.MatchRespone
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getScheduleNext(@Query("id")id_liga : String) : Call<MatchRespone>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getLastMatch(@Query("id") id_liga : String) : Call<MatchRespone>

    @GET("api/v1/json/1/searchevents.php")
    fun getSearchMatch(@Query("e") query : String) : Call<MatchRespone>

    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailEvent(@Query("id") id_event : String) : Call<MatchRespone>

    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") id_team : String) : Call<MatchRespone>

    @GET("api/v1/json/1/lookupleague.php")
    fun getDetailLeugue(@Query("id") id_league : String) : Call<LeagueResponse>
}