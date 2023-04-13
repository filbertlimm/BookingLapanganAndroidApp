package com.example.aplikasisewalapangan.DatabaseSubInfoLapangan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubInfoLapanganDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(subInfoLapangan: SubInfoLapangan)
    @Query("SELECT * from subInfoLapangan ORDER BY id ASC")
    fun selectall() : MutableList<SubInfoLapangan>
    @Query("SELECT * from subinfolapangan WHERE idUser=:IdCust")
    fun selectCust(IdCust:Int) : MutableList<SubInfoLapangan>
    @Query("SELECT * from subInfoLapangan WHERE tanggal=:Date and idLapangan=:IdLapangan")
    fun selectDate(Date : String?,IdLapangan : Int) : MutableList<SubInfoLapangan>
}