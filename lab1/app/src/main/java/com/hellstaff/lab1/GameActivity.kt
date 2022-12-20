package com.hellstaff.lab1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hellstaff.lab1.databinding.ActivityGameBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class GameActivity : AppCompatActivity(), Adaptor.CheckWord {
    private lateinit var binding: ActivityGameBinding
    private val filename = "settings"
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
        if (!File(filename).exists() && filename.trim() != "") {
            val fileInputStream: FileInputStream? = openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String?
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }
            Log.d("onCreate", "File -> $stringBuilder")
            length = stringBuilder.split(' ')[2].toInt()
            time = stringBuilder.split(' ')[3].toLong() * 60 * 1000
        }
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
                val number = (0..Word().wordChar4.size).random()
                letters = Word().wordChar4[number]
            }
            5 -> {
                val number = (0..Word().wordChar5.size).random()
                letters = Word().wordChar5[number]
            }
            6 -> {
                val number = (0..Word().wordChar6.size).random()
                letters = Word().wordChar6[number]
            }
            7 -> {
                val number = (0..Word().wordChar7.size).random()
                letters = Word().wordChar7[number]
            }
            8 -> {
                val number = (0..Word().wordChar8.size).random()
                letters = Word().wordChar8[number]
            }
            9 -> {
                val number = (0..Word().wordChar9.size).random()
                letters = Word().wordChar9[number]
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