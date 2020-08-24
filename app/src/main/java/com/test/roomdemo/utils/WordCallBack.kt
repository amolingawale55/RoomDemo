package com.test.roomdemo.utils

import androidx.recyclerview.widget.DiffUtil
import com.test.roomdemo.db.model.Word

class WordCallBack : DiffUtil.ItemCallback<Word>() {

    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}