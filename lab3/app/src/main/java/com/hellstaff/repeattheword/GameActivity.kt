package com.hellstaff.repeattheword

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.hellstaff.repeattheword.databinding.ActivityGameBinding
import java.util.concurrent.Executors

class GameActivity : AppCompatActivity(), Adaptor.CheckWord {
    private lateinit var binding: ActivityGameBinding
    private var length: Int = 0
    private var time: Long = 0
    private var counterTrueWord: Short = 0
    private var timerWork: Boolean = false
    private var timer: CountDownTimer? = null
    private var word: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val service = Executors.newScheduledThreadPool(2)
        service.execute{
            length = sharedPreferences.getString("word", "4")!!.toInt()
            time = sharedPreferences.getString("timer", "60000")!!.toLong()}
        service.shutdown()
    }

    override fun onStart() {
        super.onStart()
        counterTrueWord = 0
        startCountDownTimer(time)
        timerWork = true
    }

    override fun onResume() {
        super.onResume()
        val letters = fetchDate()
        letters.shuffle()
        val adaptor = Adaptor(this, letters, fetchDate("-"))
        binding.word.layoutManager = GridLayoutManager(this, length)
        binding.word.adapter = adaptor
    }

    private fun fetchDate(): ArrayList<String> {
        var letters = ""
        val wordTrue = ArrayList<String>()
        when (length) {
            4 -> {
                val number = (0..resources.getStringArray(R.array.word_4_char).size).random()
                letters = resources.getStringArray(R.array.word_4_char)[number]
            }
            5 -> {
                val number = (0..resources.getStringArray(R.array.word_5_char).size).random()
                letters = resources.getStringArray(R.array.word_5_char)[number]
            }
            6 -> {
                val number = (0..resources.getStringArray(R.array.word_6_char).size).random()
                letters = resources.getStringArray(R.array.word_6_char)[number]
            }
            7 -> {
                val number = (0..resources.getStringArray(R.array.word_7_char).size).random()
                letters = resources.getStringArray(R.array.word_7_char)[number]
            }
            8 -> {
                val number = (0..resources.getStringArray(R.array.word_8_char).size).random()
                letters = resources.getStringArray(R.array.word_8_char)[number]
            }
            9 -> {
                val number = (0..resources.getStringArray(R.array.word_9_char).size).random()
                letters = resources.getStringArray(R.array.word_9_char)[number]
            }
        }
        word = letters
        letters.forEach { element -> wordTrue.add(element.toString()) }
        return wordTrue
    }

    private fun fetchDate(symbol: String): ArrayList<String> {
        val item = ArrayList<String>()
        for (i in 0 until length) {
            item.add(symbol)
        }
        return item
    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(timeMili: Long) {
                if ((timeMili / 1000 % 60).toString().length == 1) binding.textTimer.text =
                    "0" + (timeMili / 60000).toString() + ":0" + (timeMili / 1000 % 60).toString()
                else binding.textTimer.text =
                    "0" + (timeMili / 60000).toString() + ":" + (timeMili / 1000 % 60).toString()
            }

            override fun onFinish() {
                timerWork = false
                binding.textTimer.text = "Час вийшов!"
                onCreateDialog()
            }
        }.start()
    }

    override fun checkWord(word: ArrayList<String>) {
        Log.d("checkWord", word.joinToString(""))
        if (word.joinToString("") == this.word) {
            counterTrueWord++
            Toast.makeText(this, "Вгадано! $counterTrueWord", Toast.LENGTH_LONG).show()
            onResume()
        }
    }

    private fun onCreateDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Гру завершено")
        builder.setMessage("Ваш результат: $counterTrueWord")
        builder.setNeutralButton("Почати ще раз") { _, _ -> onStart() }
        builder.show()
    }
}