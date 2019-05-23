package com.example.helsanf.footballmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.helsanf.footballmatch.model.Event
import com.example.helsanf.footballmatch.model.Teams
import com.example.helsanf.footballmatch.rest.ApiRepository
import com.example.helsanf.footballmatch.utils.invisible
import com.example.helsanf.footballmatch.utils.visible
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), DetailView {


    var id_event: String? = null
    var id_home: String? = null
    var id_away: String? = null
    private lateinit var presenter: DetailPresenter
    var txtScoreHome: TextView? = null
    var txtScoreAway: TextView? = null
    var txtNamaTeams: TextView? = null
    var txtGoalHome: TextView? = null
    var txtGoalAway: TextView? = null
    var txtKiperHome: TextView? = null
    var txtKiperAway: TextView? = null
    var txtBekHome: TextView? = null
    var txtBekAway: TextView? = null
    var txtGelandangHome: TextView? = null
    var txtGelandangAway: TextView? = null
    var txtPenyerangHome: TextView? = null
    var txtPenyerangAway: TextView? = null
    var imgHome: ImageView? = null
    var imgAway: ImageView? = null
    var progress: ProgressBar? = null
    var linear: LinearLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        txtScoreAway = findViewById(R.id.scoreAway)
        txtScoreHome = findViewById(R.id.scoreHome)
        imgHome = findViewById(R.id.imgViewHome)
        imgAway = findViewById(R.id.imgAway)
        txtNamaTeams = findViewById(R.id.namaTeamsBertanding)
        txtGoalHome = findViewById(R.id.goalHome)
        txtGoalAway = findViewById(R.id.goalAway)
        txtKiperHome = findViewById(R.id.kiperHome)
        txtKiperAway = findViewById(R.id.kiperAway)
        txtBekHome = findViewById(R.id.bekHome)
        txtBekAway = findViewById(R.id.bekAway)
        txtGelandangHome = findViewById(R.id.gelandangHome)
        txtGelandangAway = findViewById(R.id.gelandangAway)
        txtPenyerangHome = findViewById(R.id.penyerangHome)
        txtPenyerangAway = findViewById(R.id.penyerangAway)
        progress = findViewById(R.id.progressDetail)
        linear = findViewById(R.id.linearLayout)


        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this, apiRepository)
        id_event = intent.getStringExtra("id_event")
        id_home = intent.getStringExtra("id_home")
        id_away = intent.getStringExtra("id_away")

        Log.d("Id Event : ", id_home)
        presenter.getDetailEvent(id_event!!)
        presenter.getDetailHome(id_home!!)
        presenter.getDetailAway(id_away!!)

    }

    override fun showHomeTeam(teams: Teams) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(imgHome)

    }

    override fun showAwayTeam(teams: Teams) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(imgAway)

    }


    override fun showView() {

        linear?.visible()
    }

    override fun showloading() {
        progress?.visible()

    }

    override fun hideloading() {
        progress?.invisible()

    }

    override fun showDetailEvent(event: Event) {
        txtScoreHome!!.text = event.intHomeScore
        txtScoreAway!!.text = event.intAwayScore
        txtNamaTeams!!.text = event.strEvent
        txtGoalHome!!.text = event.strHomeGoalDetails
        txtGoalAway!!.text = event.strAwayGoalDetails
        txtKiperHome!!.text = event.strHomeLineupGoalkeeper
        txtKiperAway!!.text = event.strAwayLineupGoalkeeper
        txtBekHome!!.text = event.strHomeLineupDefense
        txtBekAway!!.text = event.strAwayLineupDefense
        txtPenyerangHome!!.text = event.strHomeLineupForward
        txtPenyerangAway!!.text = event.strAwayLineupForward
        txtGelandangHome!!.text = event.strHomeLineupMidfield
        txtGelandangAway!!.text = event.strAwayLineupMidfield
    }

}
