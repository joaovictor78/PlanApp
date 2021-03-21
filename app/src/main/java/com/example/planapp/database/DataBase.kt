package com.example.corretagemapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context?) : SQLiteOpenHelper(context, "USUARIOS", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ScriptDLL.createTableCompromisso)
        db.execSQL(ScriptDLL.createTableDataCompromisso)
        db.execSQL(ScriptDLL.createFuncionario)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        if (!db.isReadOnly) {
            db.execSQL("PRAGMA foreign_keys=1")
        }
    }
}