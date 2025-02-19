package com.example.testbd

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var database: RoomDB_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = Room.databaseBuilder(applicationContext,
            RoomDB_login::class.java,
            "loginDB").build()

//        CorotineScope(Dispatchers.IO).launch{
//
//        }

    }

    suspend fun InsertLogin(login: RoomEntity_login){
        database.loginDao().insert(login)
    }

    suspend fun GetUserByUsername(username: String): RoomEntity_login{
        return database.loginDao().getByUsername(username)
    }

    suspend fun UpdateLogin(username: String, newPass: String): RoomEntity_login{
        database.loginDao().updatePassword(username, newPass)
    }
}