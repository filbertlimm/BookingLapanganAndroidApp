package com.example.aplikasisewalapangan.DatabaseAdmin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Admin::class], version = 1)
abstract class AdminDatabase : RoomDatabase(){
    abstract fun adminDao() : AdminDao
    companion object{
        @Volatile
        private var INSTANCE: AdminDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): AdminDatabase{
            if (INSTANCE == null){
                synchronized(AdminDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,AdminDatabase::class.java,"admin_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as AdminDatabase
        }
    }
}