package com.manish.assignment.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "capital")
    public String capital;

    @ColumnInfo(name  = "region")
    public String region;

    @ColumnInfo(name = "subregion" )
    public String subregion;


    @ColumnInfo(name = "population")
    public int population;

    @ColumnInfo(name = "languages")
    public  String languages;

    @ColumnInfo(name = "Flag")
    public String flag;

    @ColumnInfo(name = "border")
    public String borders;



}
