package com.example.helsanf.footballmatch.nextmatch


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar
import com.example.helsanf.footballmatch.DetailActivity
import com.example.helsanf.footballmatch.preference.MyPreference
import com.example.helsanf.footballmatch.R
import com.example.helsanf.footballmatch.adapter.MatchAdapter
import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.rest.ApiRepository
import com.example.helsanf.footballmatch.utils.invisible
import com.example.helsanf.footballmatch.utils.visible
import com.mlsdev.animatedrv.AnimatedRecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentNext : Fragment(), NextView {

    private var recycle: AnimatedRecyclerView? = null
    private var adapter: MatchAdapter? = null
    private lateinit var presenter: NextPresenter
    private var match: MutableList<Event> = mutableListOf()
    private var progress: ProgressBar? = null
    private lateinit var myPreference: MyPreference

    //    private var activity : Activity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_next, container, false)

        setHasOptionsMenu(true)
        recycle = rootview.findViewById(R.id.rv_next)
        progress = rootview.findViewById(R.id.progressNext)
        myPreference = MyPreference(this.activity!!)
        showMatch()
        return rootview
    }


    override fun showLoading() {
        progress!!.visible()
    }

    override fun hideLoading() {
        progress!!.invisible()
    }

    private fun itemMatchClicked(item : Event) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id_event",item.idEvent)
        intent.putExtra("id_home",item.idHomeTeam)
        intent.putExtra("id_away",item.idAwayTeam)
        startActivity(intent)
    }

    override fun showScheduleList(data: List<Event>) {
        //masukan ke dalam recyclerview
        match.clear()
        match.addAll(data)
        adapter = MatchAdapter(match,{itemMatch :Event -> itemMatchClicked(itemMatch)})

        recycle!!.adapter = adapter
        recycle!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recycle!!.layoutManager = layoutManager
        adapter!!.notifyDataSetChanged()
        recycle!!.scheduleLayoutAnimation()

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search, menu)
        val searchview = menu!!.findItem(R.id.searchView)?.actionView as SearchView
        searchview.isSubmitButtonEnabled = true
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(querys: String?): Boolean {
                if (querys != null) {
                    recycle!!.adapter = null
                    searchMatch(querys)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })

    }

    fun showMatch() {
        val request = ApiRepository()
        presenter = NextPresenter(this.activity!!,this, request)
        presenter.getTeamList(myPreference.getIdLiga())
    }

    fun searchMatch(query: String) {
        val request = ApiRepository()
        presenter = NextPresenter(this.activity!!,this, request)
        presenter.getTeamSearch(query)
    }


}
