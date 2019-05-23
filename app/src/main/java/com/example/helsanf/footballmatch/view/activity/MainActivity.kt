package com.example.helsanf.footballmatch.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.helsanf.footballmatch.preference.MyPreference
import com.example.helsanf.footballmatch.R
import com.example.helsanf.footballmatch.adapter.MyPagerAdapter
import com.example.helsanf.footballmatch.model.League
import com.example.helsanf.footballmatch.rest.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var imgLiga: ImageView
    private lateinit var txtLiga: TextView
    private lateinit var presenter: MainPresenter
    private lateinit var myPreference: MyPreference
    var idLiga: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_pager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(view_pager_main)
        imgLiga = findViewById(R.id.liga)
        txtLiga = findViewById(R.id.textLiga)
        val apiRepository = ApiRepository()
        presenter = MainPresenter(this, apiRepository)
        idLiga = intent.getStringExtra("id_liga")
        myPreference = MyPreference(this)
        myPreference.setIdLiga(idLiga)
        presenter.getDetailLiga(idLiga)

    }

    override fun showLiga(liga: League) {
        Picasso.get()
            .load(liga.strFanart1)
            .into(imgLiga)

        txtLiga.text = liga.strLeague
    }
}
