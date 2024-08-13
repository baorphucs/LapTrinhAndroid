package com.example.lap08;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataSqlite extends SQLiteOpenHelper {
    public DataSqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE IF NOT EXISTS QLSV (id INTEGER PRIMARY KEY AUTOINCREMENT, maSV TEXT, tenSV TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS QLSV");
        onCreate(db);
    }

    public void TruyVanKhongTraVe(String sql, String[] selectionArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (selectionArgs != null) {
            db.execSQL(sql, selectionArgs);
        } else {
            db.execSQL(sql);
        }
    }

    public Cursor TruyVanTraVe(String sql, String[] selectionArgs) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (selectionArgs != null) {
            return db.rawQuery(sql, selectionArgs);
        } else {
            return db.rawQuery(sql, null);
        }
    }
}
