package com.example.helsanf.footballmatch.view.liga

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.helsanf.footballmatch.R
import com.example.helsanf.footballmatch.adapter.LeugueStatisAdapter
import com.example.helsanf.footballmatch.model.LigaStatis
import com.example.helsanf.footballmatch.view.activity.MainActivity

class LeugueActivity : AppCompatActivity() {
    private var items : MutableList<LigaStatis> = mutableListOf()
    private lateinit var recler : RecyclerView
    private lateinit var adapter : LeugueStatisAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leugue)
        recler = findViewById(R.id.recycleLiga)
        initData()
    }

    fun getItemClick(item : LigaStatis){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("id_liga",item.idLiga)
        startActivity(intent)
    }
    private fun initData(){
        val name = resources.getStringArray(R.array.ligaName)
        val image = resources.obtainTypedArray(R.array.liga_image)
        val idLiga = resources.getStringArray(R.array.id_liga)

        items.clear()
        for (i in name.indices) {
            items.add(LigaStatis(name[i],image.getResourceId(i, 0),idLiga[i]))
        }
        adapter =LeugueStatisAdapter(this,items,{liga : LigaStatis -> getItemClick(liga)})
        recler!!.adapter = adapter
        recler!!.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recler!!.layoutManager = layoutManager
        adapter!!.notifyDataSetChanged()


        //Recycle the typed array
        image.recycle()
    }
}
