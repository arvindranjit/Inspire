package com.arvindranjit.inspire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.arvindranjit.inspire.Todo;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Todo.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Todo.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertTodo(String label, String DateTime, int Difficulty, int color1, int color2) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Todo.COLUMN_LABEL, label);
        values.put(Todo.COLUMN_BYTIMESTAMP, DateTime);
        values.put(Todo.COLUMN_DIFFICULTY, Difficulty);
        values.put(Todo.COLUMN_STATUS, 0);
        values.put(Todo.COLUMN_COLOR1, color1);
        values.put(Todo.COLUMN_COLOR2, color2);

        // insert row
        long id = db.insert(Todo.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Todo getTodo(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Todo.TABLE_NAME,
                new String[]{Todo.COLUMN_ID, Todo.COLUMN_LABEL, Todo.COLUMN_TIMESTAMP, Todo.COLUMN_BYTIMESTAMP, Todo.COLUMN_DIFFICULTY, Todo.COLUMN_STATUS, Todo.COLUMN_COLOR1, Todo.COLUMN_COLOR2},
                Todo.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Todo todo = new Todo(
                cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Todo.COLUMN_LABEL)),
                cursor.getString(cursor.getColumnIndex(Todo.COLUMN_TIMESTAMP)),
                cursor.getString(cursor.getColumnIndex(Todo.COLUMN_BYTIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_DIFFICULTY)),
                cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_STATUS)),
                cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_COLOR1)),
                cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_COLOR2))

        );

        // close the db connection
        cursor.close();

        return todo;
    }



    public List<Todo> getAllNotes() {
        List<Todo> todos = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Todo.TABLE_NAME + " ORDER BY " +
                Todo.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo();
                todo.setId(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_ID)));
                todo.setLabel(cursor.getString(cursor.getColumnIndex(Todo.COLUMN_LABEL)));
                todo.setTimestamp(cursor.getString(cursor.getColumnIndex(Todo.COLUMN_TIMESTAMP)));
                todo.setByTimestamp(cursor.getString(cursor.getColumnIndex(Todo.COLUMN_BYTIMESTAMP)));
                todo.setDifficulty(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_DIFFICULTY)));
                todo.setStatus(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_STATUS)));
                todo.setcolor1(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_COLOR1)));
                todo.setcolor2(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_COLOR2)));

                todos.add(todo);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return todos;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Todo.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Todo.COLUMN_LABEL, todo.getLabel());
        values.put(Todo.COLUMN_BYTIMESTAMP, todo.getByTimestamp());
        values.put(Todo.COLUMN_DIFFICULTY, todo.getDifficulty());
        values.put(Todo.COLUMN_STATUS, todo.getStatus());


        // updating row
        return db.update(Todo.TABLE_NAME, values, Todo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
    }

    public void deleteNote(Todo note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Todo.TABLE_NAME, Todo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}