package com.example.testbd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomEntity_login::class], version = 1)
abstract class RoomDB_login: RoomDatabase() {

    abstract fun loginDao(): RoomDao_login

}