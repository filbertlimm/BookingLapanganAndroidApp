package com.example.aplikasisewalapangan.DatabaseAdmin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "username")
    var user: String? = null,
    @ColumnInfo(name = "password")
    var pass: String? = null,
    @ColumnInfo(name = "nomorTelepon")
    var nomorTelepon: String? = null,
    @ColumnInfo(name = "alamat")
    var alamat: String? = null,
    @ColumnInfo(name = "statusVerifikasi")
    var statusVerifikasi: String? = null
)
