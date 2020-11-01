package com.mk.room.db.sample.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict =  REPLACE)
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void deleteAll(List<MainData> mainData);

    @Query("UPDATE table_name SET text = :txt WHERE ID = :ID")
    void update(int ID , String txt);

    @Query("SELECT * FROM table_name")
    List<MainData> getAll();
}
