package com.hellstaff.repeattheword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptor(
    private val check: CheckWord,
    private val word: ArrayList<String>,
    private val trueWord: ArrayList<String>
) :
    RecyclerView.Adapter<Adaptor.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startWord: TextView = itemView.findViewById(R.id.startWord)
        val endWord: TextView = itemView.findViewById(R.id.endWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.startWord.text = word[position]
        holder.endWord.text = trueWord[position]

        holder.startWord.setOnClickListener {
            val positionTrueWord = checkArrayFill(trueWord)
            val swap = trueWord[positionTrueWord]
            trueWord.removeAt(positionTrueWord)
            trueWord.add(positionTrueWord, word[position])
            word.removeAt(position)
            word.add(position, swap)
            notifyItemChanged(positionTrueWord)
            notifyItemChanged(position)
            check.checkWord(trueWord)
        }

        holder.endWord.setOnClickListener {
            val positionWord = checkArrayFill(word)
            val swap = word[positionWord]
            word.removeAt(positionWord)
            word.add(positionWord, trueWord[position])
            trueWord.removeAt(position)
            trueWord.add(position, swap)
            notifyItemChanged(positionWord)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return word.size
    }

    private fun checkArrayFill(array: ArrayList<String>): Int {
        var i = 0
        while (i < array.size) {
            if (array[i] == "-")
                return i
            else i++
        }
        return 0
    }

    interface CheckWord {
        fun checkWord(word: ArrayList<String>)
    }
}