package com.harman.myapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.harman.myapplication.entity.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoAppDataBase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
