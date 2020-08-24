package com.test.roomdemo.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.roomdemo.R
import com.test.roomdemo.adapter.WordListAdapter
import com.test.roomdemo.db.model.Word
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.test.roomdemo.adapter.WordListAdapterOne
import com.test.roomdemo.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get a new or existing ViewModel from the ViewModelProvider.
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapterOne(this)

        wordViewModel.allWords.observe(this, Observer {
            Log.e("TAGTAGTAG", "List size : " + it.size)
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter

        btn_savedata.setOnClickListener(View.OnClickListener {
            var word = Word(et_word.text.toString())
            wordViewModel.insert(word)
        })

        //region New Activity -- Please ignore this part
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
//            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
//            startActivityForResult(intent, newWordActivityRequestCode)

            startActivity(Intent(this, PlayerActivity::class.java))
        }
        //endregion

    }

        //region New Activity -- Please ignore this part
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    //endregion

}