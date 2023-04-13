package com.example.aplikasisewalapangan.DatabaseSubInfoLapangan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubInfoLapangan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "idPemilik")
    var idPemilik : Int = 0,
    @ColumnInfo(name = "idLapangan")
    var idLapangan : Int = 0,
    @ColumnInfo(name = "idUser")
    var idUser : Int = 0,
    @ColumnInfo(name = "tanggal")
    var tanggal : String? = null,
    @ColumnInfo(name = "jamMulai")
    var jamMulai : String? = null,
    @ColumnInfo(name = "jamAkhir")
    var jamAkhir : String? = null
)
