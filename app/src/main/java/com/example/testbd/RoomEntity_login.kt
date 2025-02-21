package com.example.testbd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class RoomEntity_login(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "pwd") val pwd: String,
    @ColumnInfo(name = "remember") val remember: Boolean
)
