package com.example.aplikasisewalapangan.DatabaseInfoLapangan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InfoLapangan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "nama")
    var nama: String? = null,
    @ColumnInfo(name = "jenis")
    var jenis: String? = null,
    @ColumnInfo(name = "alamat")
    var lokasi: String? = null,
    @ColumnInfo(name = "harga")
    var harga: Int = 0,
    @ColumnInfo(name = "idPemilik")
    var idPemilik: Int = 0,
    @ColumnInfo(name = "jamBuka")
    var jamMulai: String? = null,
    @ColumnInfo(name = "jamTutup")
    var jamAkhir: String? = null,
)
