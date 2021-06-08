package com.manish.assignment.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.manish.assignment.util.Converter;

@Database(entities = {User.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class Appdatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static Appdatabase INSTANCE;

    public static  Appdatabase getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),Appdatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();


        }
        return  INSTANCE;
    }

}
