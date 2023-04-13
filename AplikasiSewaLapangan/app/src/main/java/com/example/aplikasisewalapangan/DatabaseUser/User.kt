package com.example.aplikasisewalapangan.DatabaseUser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "username")
    var user: String? = null,
    @ColumnInfo(name = "password")
    var pass: String? = null,
    @ColumnInfo(name = "nomorTelepon")
    var nomorTelepon: String? = null,
    @ColumnInfo(name = "status")
    var status: String? = null

)
