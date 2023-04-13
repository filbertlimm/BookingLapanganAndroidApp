package com.example.aplikasisewalapangan.DatabaseInfoLapangan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplikasisewalapangan.DatabaseUser.User

@Database(entities = [InfoLapangan::class], version = 1)
abstract class InfoLapanganDatabase : RoomDatabase(){
    abstract fun infoLapanganDao() : InfoLapanganDao
    companion object{
        @Volatile
        private var INSTANCE: InfoLapanganDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): InfoLapanganDatabase {
            if (INSTANCE == null){
                synchronized(InfoLapangan::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        InfoLapanganDatabase::class.java,"infoLapangan_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as InfoLapanganDatabase
        }
    }
}