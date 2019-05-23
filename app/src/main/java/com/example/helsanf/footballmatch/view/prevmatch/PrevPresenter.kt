package com.example.helsanf.footballmatch.prevmatch

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.model.MatchRespone
import com.example.helsanf.footballmatch.rest.ApiInterface
import com.example.helsanf.footballmatch.rest.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevPresenter(private val context: Context,private val view : PrevView , private val apiRepository: ApiRepository) {

    fun getLastMatch(liga : String){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getLastMatch(liga).enqueue(object :  Callback<MatchRespone> {
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                view.hideLoading()
                val get : List<Event>? = response.body()!!.events
                if(get == null){
                    showToastMethod(context)
                }else{
                    view.ShowMatchList(get)

                }
            }

        })
    }

    fun getTeamSearch(query : String){
        view.showLoading()
        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.getSearchMatch(query).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {
                Log.d("tag", "responsennya ${t.message}")

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                view.hideLoading()
                val get : List<Event>? = response.body()!!.event
                if(get == null){
                    showToastMethod(context)
                } else{
                    view.ShowMatchList(get!!)

                }

            }

        })
    }
    fun showToastMethod(context: Context) {
        Toast.makeText(context, "Pertandingan di Liga telah Selesai", Toast.LENGTH_SHORT).show()
    }
}