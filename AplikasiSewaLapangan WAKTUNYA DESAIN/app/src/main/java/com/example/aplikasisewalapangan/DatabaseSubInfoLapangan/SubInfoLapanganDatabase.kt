package com.example.aplikasisewalapangan.DatabaseSubInfoLapangan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase

@Database(entities = [SubInfoLapangan::class], version = 1)
abstract class SubInfoLapanganDatabase : RoomDatabase(){
    abstract fun subInfoLapanganDao() : SubInfoLapanganDao
    companion object{
        @Volatile
        private var INSTANCE: SubInfoLapanganDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): SubInfoLapanganDatabase {
            if (INSTANCE == null){
                synchronized(SubInfoLapangan::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SubInfoLapanganDatabase::class.java,"subInfoLapangan_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as SubInfoLapanganDatabase
        }
    }
}