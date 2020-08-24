package com.test.roomdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.roomdemo.R
import com.test.roomdemo.db.model.PlayerModel
import com.test.roomdemo.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    lateinit var viewModel : PlayerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        viewModel.playerList.observe(this, Observer {
            Log.e("TAG","Data : "+  it)
        })
        setClickLister()
    }

    fun saveData(){
        var playerModel = PlayerModel(et_player_name.text.toString(),"20")
        viewModel.insert(playerModel)
    }

    fun setClickLister(){
        btn_save.setOnClickListener(View.OnClickListener {
            saveData()
        })

        btn_delete.setOnClickListener(View.OnClickListener {
            deleteData()
        })
    }

    fun deleteData(){
        viewModel.deleteAll()
    }
}