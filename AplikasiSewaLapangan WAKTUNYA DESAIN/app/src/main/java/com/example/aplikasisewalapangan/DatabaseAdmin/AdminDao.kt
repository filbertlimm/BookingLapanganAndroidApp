package com.example.aplikasisewalapangan.DatabaseAdmin

import androidx.room.*
@Dao
interface AdminDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(admin: Admin)
    @Query("SELECT * from admin WHERE statusVerifikasi=:status ORDER BY id ASC")
    fun selectall(status:String) : MutableList<Admin>
    @Query("SELECT * from admin ORDER BY id ASC")
    fun selectall2() : MutableList<Admin>
    @Query("UPDATE admin SET username=:Username,password=:Password,nomorTelepon=:NomorTelepon,alamat=:Alamat,statusVerifikasi=:StatusVerifikasi WHERE id=:Noid")
    fun update(Username:String,Password:String,NomorTelepon:String,Alamat:String,StatusVerifikasi:String, Noid: Int)
    @Query("SELECT * from admin WHERE id=:Noid")
    suspend fun getInfo(Noid: Int) : Admin
    @Delete
    fun delete(admin: Admin)
}