package com.harman.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.harman.myapplication.entity.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    public void addData(Todo todo);

    @Query("select * from todo")
    public List<Todo> getMyTodo();

    @Delete
    public void delete(Todo todo);

    @Update
    public void update(Todo todo);

}
