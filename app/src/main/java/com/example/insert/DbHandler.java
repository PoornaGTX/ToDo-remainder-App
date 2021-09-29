package com.example.insert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "todo";
    private static final String TABLE_NAME = "todo";

    private static final String ID ="id";
    private static final String TITLE ="title";
    private static final String DESCRIPTION ="description";
    private static final String STARTED ="started";
    private static final String FINISHED ="finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

         String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" "+"("
                 +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                 +TITLE+ " TEXT,"
                 +DESCRIPTION+ " TEXT,"
                 +STARTED+ " TEXT,"
                 +FINISHED+ " TEXT"+
                 ");";

         db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

         String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

         db.execSQL(DROP_TABLE_QUERY);
         onCreate(db);
    }


    public void addToDo(ToDo todo){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDescrpition());
        contentValues.put(STARTED,todo.getStarted());
        contentValues.put(FINISHED,todo.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }

    public int countToDo(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME ;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<ToDo> getAllToDos() {
        List<ToDo> toDos = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do{

                ToDo todo = new ToDo();

                todo.setId(cursor.getInt(0));
                todo.setTitle(cursor.getString(1));
                todo.setDescrpition(cursor.getString(2));
                todo.setStarted(cursor.getLong(3));
                todo.setStarted(cursor.getLong(4));

                toDos.add(todo);

            }while(cursor.moveToNext());
        }
        return toDos;
    }

    //delete item
    public  void deleteToDO(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID +" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //get a singale todo

    public ToDo getSingaleToDo(int id){

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor= db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED,FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)},null,null,null);


        ToDo todo;
        if(cursor != null ) {
            cursor.moveToFirst();
            todo = new ToDo(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)

            );

            return todo ;

        }
        return null;
    }

    public int updateSingletToDo(ToDo todo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDescrpition());
        contentValues.put(STARTED,todo.getStarted());
        contentValues.put(FINISHED,todo.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",new String[]{String.valueOf(todo.getId())});

        db.close();
        return status;

    }

}