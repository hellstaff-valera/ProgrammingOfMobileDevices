package com.example.correctword

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

class GameActivity : AppCompatActivity(){
    private val dateModel: DateModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val lengthWord = sharedPreferences.getString("word", "4")
        val time = sharedPreferences.getString("timer", "60000")
        Log.d("GameActivity", lengthWord.toString())
        dateModel.timer.value = time
        setContentView(R.layout.activity_game)
        when(lengthWord){
            "4" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar4.newInstance()).commit()
            "5" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar5.newInstance()).commit()
            "6" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar6.newInstance()).commit()
            "7" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar7.newInstance()).commit()
            "8" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar8.newInstance()).commit()
            "9" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentWord, WordChar9.newInstance()).commit()
        }
    }
}