package com.example.helsanf.footballmatch.preference

import android.content.Context

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MyPreference(context: Context) {
    val PREFERENCE_NAME = "MyPreference"
    val ID_LIGA = "ID_LIGA"

    val preference = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun getIdLiga() : String {
        return preference.getString(ID_LIGA,"")
    }

    fun setIdLiga(idLiga : String){
        val editor = preference.edit()
        editor.putString(ID_LIGA,idLiga)
        editor.apply()
    }
}