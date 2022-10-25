package com.example.correctword

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.correctword.databinding.FragmentWordChar6Binding

class WordChar6 : Fragment() {
    private lateinit var binding: FragmentWordChar6Binding
    private val dateModel: DateModel by activityViewModels()
    private var wordTrue = arrayOfNulls<String>(6)
    private var arrayWord = arrayOfNulls<String>(6)
    private var arrayTextView = arrayOfNulls<TextView>(6)
    private var arrayTextViewTrue = arrayOfNulls<TextView>(6)
    private var counterTrueWord: Int = 0
    private var time: Long = 60000
    private var timerWork: Boolean = false
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordChar6Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arrayTextView = arrayOf(
            binding.textChar1,
            binding.textChar2,
            binding.textChar3,
            binding.textChar4,
            binding.textChar5,
            binding.textChar6
        )
        arrayTextViewTrue = arrayOf(
            binding.textCharTrue1,
            binding.textCharTrue2,
            binding.textCharTrue3,
            binding.textCharTrue4,
            binding.textCharTrue5,
            binding.textCharTrue6
        )

        binding.textChar1.setOnClickListener {
            val letter = binding.textChar1.text
            binding.textChar1.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }
        binding.textChar2.setOnClickListener {
            val letter = binding.textChar2.text
            binding.textChar2.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }
        binding.textChar3.setOnClickListener {
            val letter = binding.textChar3.text
            binding.textChar3.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }
        binding.textChar4.setOnClickListener {
            val letter = binding.textChar4.text
            binding.textChar4.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }
        binding.textChar5.setOnClickListener {
            val letter = binding.textChar5.text
            binding.textChar5.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }
        binding.textChar6.setOnClickListener {
            val letter = binding.textChar6.text
            binding.textChar6.text = "-"
            if (letter != "-")
                fillArray(arrayTextViewTrue, letter)
        }

        binding.textCharTrue1.setOnClickListener {
            val letter = binding.textCharTrue1.text
            binding.textCharTrue1.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        binding.textCharTrue2.setOnClickListener {
            val letter = binding.textCharTrue2.text
            binding.textCharTrue2.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        binding.textCharTrue3.setOnClickListener {
            val letter = binding.textCharTrue3.text
            binding.textCharTrue3.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        binding.textCharTrue4.setOnClickListener {
            val letter = binding.textCharTrue4.text
            binding.textCharTrue4.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        binding.textCharTrue5.setOnClickListener {
            val letter = binding.textCharTrue5.text
            binding.textCharTrue5.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        binding.textCharTrue6.setOnClickListener {
            val letter = binding.textCharTrue6.text
            binding.textCharTrue6.text = "-"
            if (letter != "-")
                fillArray(arrayTextView, letter)
        }
        onStart()
    }

    override fun onStart() {
        super.onStart()
        dateModel.timer.observe(activity as LifecycleOwner) {
            time = it.toLong()
            counterTrueWord = 0
            wordToTextView()
            timerWork = true
            startCountDownTimer(time)
        }
    }

    companion object {
        fun newInstance() = WordChar6().apply { Bundle().apply {} }
    }

    private fun checkArrayFill(array: Array<TextView?>): Int {
        var i = 0
        while (i < array.size) {
            if (array[i]!!.text == "-")
                return i
            else i++
        }
        return 0
    }

    private fun fillArray(arrayTextTrue: Array<TextView?>, letter: CharSequence) {
        val counter: Int = checkArrayFill(arrayTextTrue)
        arrayTextTrue[counter]!!.text = letter
        if (winWord(arrayTextTrue) && counter == 5) {
            counterTrueWord++
            Toast.makeText(activity, "Вгадано! $counterTrueWord", Toast.LENGTH_LONG).show()
            if (timerWork) wordToTextView()
        }
    }

    private fun winWord(arrayTextTrue: Array<TextView?>): Boolean {
        var i = 0
        while (i < arrayTextTrue.size) {
            if (arrayTextTrue[i]!!.text != wordTrue[i])
                return false
            i++
        }
        return true
    }

    private fun wordToTextView() {
        val number = (0..Word().wordChar6.size).random()
        Word().wordChar6[number].forEachIndexed { index, char ->
            arrayWord[index] = char.toString()
        }
        Word().wordChar6[number].forEachIndexed { index, char -> wordTrue[index] = char.toString() }
        arrayWord.shuffle()
        fillArray(arrayTextView, arrayWord)
        fillArray(arrayTextViewTrue)
    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1) {
            override fun onTick(timeMili: Long) {
                binding.textTimer.text = (timeMili / 1000).toString()
            }
            override fun onFinish() {
                timerWork = false
                binding.textTimer.text = "Час вийшов!"
                fillArray(arrayTextView)
                fillArray(arrayTextViewTrue)
                onCreateDialog()
            }
        }.start()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun onCreateDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Гру завершено")
        builder.setMessage("Ваш результат: $counterTrueWord")
        builder.setNeutralButton("Почати ще раз") { _, _ -> onStart() }
        builder.show()
    }

    private fun fillArray(array: Array<TextView?>) {
        var i = 0
        while (i < array.size) {
            array[i]!!.text = "-"
            i++
        }
    }

    private fun fillArray(array: Array<TextView?>, arrayString: Array<String?>) {
        var i = 0
        while (i < array.size) {
            array[i]!!.text = arrayString[i]
            i++
        }
    }
}