package com.test.roomdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.roomdemo.R
import com.test.roomdemo.db.model.Word
import com.test.roomdemo.utils.WordCallBack
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapterOne(context : Context) : PagedListAdapter<Word, WordListAdapterOne.WordViewHolderOne>(WordCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListAdapterOne.WordViewHolderOne {
        return WordViewHolderOne(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: WordListAdapterOne.WordViewHolderOne, position: Int) {
        val word: Word = getItem(position)!!
        holder.bind(word)

    }

    class WordViewHolderOne(itemView : View) : RecyclerView.ViewHolder(itemView){
        var txt_Word = itemView.textView

        fun bind(person: Word) {
            txt_Word.text = person.word
        }
    }
}