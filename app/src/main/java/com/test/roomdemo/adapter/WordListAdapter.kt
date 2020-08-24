package com.test.roomdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.roomdemo.R
import com.test.roomdemo.db.model.Word

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
//class WordListAdapter(context: Context) : PagingDataAdapter<Word,WordListAdapter.WordViewHolder>(UIMODEL_COMPARATOR) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size


//    object UIMODEL_COMPARATOR {
//        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<Word>() {
//            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
//                return (oldItem is Word && newItem is Word.RepoItem &&
//                        oldItem.repo.fullName == newItem.repo.fullName) ||
//                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
//                                oldItem.description == newItem.description)
//            }
//
//            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean =
//                oldItem == newItem
//        }
//    }
}
