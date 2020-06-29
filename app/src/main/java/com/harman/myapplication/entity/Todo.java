package com.harman.myapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todo")
public class Todo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;


    @ColumnInfo(name = "toto_title")
    private String title;

    @ColumnInfo(name = "todo_description")
    private String description;

    @Ignore
    public Todo() {

    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    private String text1;
    private String text2;

    public Todo(long id, String title, String description,String text1, String text2) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.text1 = text1;
        this.text2=text2;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
