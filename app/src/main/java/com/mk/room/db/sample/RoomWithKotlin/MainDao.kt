package com.mk.room.db.sample.RoomWithKotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface  MainDao {
    @Query("SELECT * FROM tableName")
    fun getAll(): List<MainData>

    @Query("UPDATE tableName SET input_text = :inputText WHERE ID = :ID")
    fun update(ID: Int,inputText: String)

    @Insert
    fun insertAll(mainData: MainData)
    @Delete
    fun delete(mainData: MainData)
    @Delete
    fun deleteAll(mainData: List<MainData>)


}