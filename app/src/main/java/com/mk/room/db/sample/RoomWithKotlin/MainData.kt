package com.mk.room.db.sample.RoomWithKotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableName" )
data class MainData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val ID : Int,
    @ColumnInfo(name = "input_text")
    val inputText: String
)