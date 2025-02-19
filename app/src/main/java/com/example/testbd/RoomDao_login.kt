package com.example.testbd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao_login {

    @Query("SELECT * FROM login")
    fun getAll(): List<RoomEntity_login>

    @Query("SELECT * FROM login WHERE username LIKE :username")
    fun getByUsername(username: String): RoomEntity_login

    @Insert
    fun insert(login: RoomEntity_login)

    @Delete
    fun delete(login: RoomEntity_login)

    @Query("UPDATE login SET pwd = :password WHERE username = :username")
    fun updatePassword(username: String, password: String)
}