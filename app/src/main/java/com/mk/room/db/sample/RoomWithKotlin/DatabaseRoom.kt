package com.mk.room.db.sample.RoomWithKotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MainData::class],version = 2,exportSchema = false)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseRoom? = null
        fun getDatabase(context: Context): DatabaseRoom{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseRoom::class.java,
                        "room_db"
                ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}