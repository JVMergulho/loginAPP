package com.example.testbd

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.testbd.databinding.ActivityMainBinding
import com.example.testbd.databinding.LoginsLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var database: RoomDB_login
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext,
            RoomDB_login::class.java,
            "loginDB").build()

        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)

        CoroutineScope(Dispatchers.IO).launch{
            // insertLogin()
        }

    }

    suspend fun InsertLogin(login: RoomEntity_login){
        database.loginDao().insert(login)
    }

    suspend fun GetUserByUsername(username: String): RoomEntity_login{
        return database.loginDao().getByUsername(username)
    }

    suspend fun UpdateLogin(username: String, newPass: String) {
        return database.loginDao().updatePassword(username, newPass)
    }

    override fun onClick(p0: View?){
        when(p0?.id){
            R.id.btnLogin -> {
                val intent: Intent = Intent(this, Logins::class.java)
                startActivity(intent)
            }
            R.id.btnRegister -> {
                CoroutineScope(Dispatchers.IO).launch{

                    try{
                        val entity: RoomEntity_login = RoomEntity_login(
                            username = binding.edtEmail.text.toString(),
                            pwd = binding.edtPassword.text.toString(),
                            remember = binding.checkRemember.isChecked
                        )

                        database.loginDao().insert(entity)
                    }
                    catch(exception: Exception){
                        Log.d("LOG", "error: " + exception)
                    }
                }
            }
        }
    }
}