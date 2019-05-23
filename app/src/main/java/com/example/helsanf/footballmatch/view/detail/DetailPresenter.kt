package com.example.helsanf.footballmatch

import android.util.Log
import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.model.MatchRespone
import com.example.helsanf.footballmatch.model.Teams
import com.example.helsanf.footballmatch.rest.ApiInterface
import com.example.helsanf.footballmatch.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private var view : DetailView , private var apiRepository: ApiRepository) {

    fun getDetailEvent(id_event : String){
        view.showloading()

        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailEvent(id_event).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getDetail : Event = response.body()!!.events.get(0)
                view.showDetailEvent(getDetail)
                Log.d("tag", "responsennya ${getDetail.strFilename}")
                view.hideloading()
                view.showView()

            }


        })
    }

    fun getDetailHome(id_teams : String){
        view.showloading()

        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getTeams : Teams = response.body()!!.teams.get(0)
                view.showHomeTeam(getTeams)
                view.hideloading()
                view.showView()

            }

        })
    }
    fun getDetailAway(id_teams : String){
        view.showloading()

        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getTeams : Teams = response.body()!!.teams.get(0)
                view.showAwayTeam(getTeams)
                view.hideloading()
                view.showView()


            }

        })
    }
}