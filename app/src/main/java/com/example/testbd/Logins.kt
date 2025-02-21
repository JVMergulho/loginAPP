package com.example.testbd

import android.os.Bundle
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.testbd.databinding.LoginsLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Logins: AppCompatActivity() {
    lateinit var binding: LoginsLayoutBinding
    lateinit var database: RoomDB_login

    lateinit var adapter: RecyclerViewLoginAdapter

    override fun onCreate(savedIntanceState: Bundle?){
        super.onCreate(savedIntanceState)

        binding = LoginsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerViewLoginAdapter()
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        database = Room.databaseBuilder(applicationContext,
            RoomDB_login::class.java,
            "loginDB").build()

        CoroutineScope(Dispatchers.IO).launch {
            val result: List<RoomEntity_login> = database.loginDao().getAll()

            withContext(Dispatchers.Main){
                adapter.addAll(result)
            }
        }
    }
}