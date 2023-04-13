package com.example.aplikasisewalapangan.DatabaseInfoLapangan

import androidx.room.*

@Dao
interface InfoLapanganDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(infoLapangan: InfoLapangan)
    @Query("SELECT * from infoLapangan ORDER BY id ASC")
    fun selectall() : MutableList<InfoLapangan>
    @Query("UPDATE infoLapangan SET nama=:Nama,jenis=:Jenis,alamat=:Lokasi,harga=:Harga,idPemilik=:IdPemilik,jamBuka=:JamMulai, jamTutup=:JamAkhir WHERE id=:Noid")
    fun update(Nama: String,Jenis: String,Lokasi: String,Harga: Int,IdPemilik: Int,JamMulai : String,JamAkhir : String, Noid: Int)
    @Query("SELECT * from infolapangan WHERE id=:Noid")
    suspend fun getInfo(Noid: Int) : InfoLapangan
    @Delete
    fun delete(infoLapangan: InfoLapangan)
    //query billy
//    @Query("SELECT * from infoLapangan WHERE status=:Status and idPemilik=:IdPemilik")
//    fun getStatus(Status: String, IdPemilik: Int) : MutableList<InfoLapangan>
    @Query("SElECT * from infolapangan WHERE idPemilik=:IdPemilik")
    fun getLapangan(IdPemilik: Int) : MutableList<InfoLapangan>
    //query filbert
//    @Query("SELECT * from infolapangan WHERE idCustomer=:IdCustomer")
//    fun selectCustomer(IdCustomer: Int) : MutableList<InfoLapangan>
    @Query("SELECT * from infolapangan WHERE jenis=:Jenis")
    fun selectJenis(Jenis:String) : MutableList<InfoLapangan>
//    @Query("UPDATE infolapangan SET idCustomer=:IdCustomer, status=:Status WHERE id=:Noid")
//    fun pesan(IdCustomer: Int, Noid: Int, Status: String)
}