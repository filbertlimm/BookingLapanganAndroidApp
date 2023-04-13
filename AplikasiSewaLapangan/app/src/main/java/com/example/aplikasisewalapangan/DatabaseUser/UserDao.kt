package com.example.aplikasisewalapangan.DatabaseUser

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * from user ORDER BY id ASC")
    fun selectall() : MutableList<User>

    @Query("UPDATE user SET username=:Username,password=:Password,nomorTelepon=:NomorTelepon,status=:Status WHERE id=:Noid")
    fun update(Username:String,Password:String,NomorTelepon:String,Status:String, Noid: Int)

    @Query("SELECT * from user WHERE id=:Noid")
    suspend fun getInfo(Noid: Int) : User

    @Delete
    fun delete(user: User)

    //update no telp
    @Query("UPDATE user SET nomorTelepon=:NomorTelepon WHERE id=:Noid")
    fun gantiNoTelp(NomorTelepon: String, Noid: Int)
}