package com.example.myapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context:Context) :
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){



    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val AGE = "age"
        private const val EMAIL = "email"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE " + TBL_USER + "("
                + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT,"
                + EMAIL + " TEXT," + AGE + " INTEGER" + ")")
        db?.execSQL(createTblUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    fun insertUser(std:UserModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(AGE, std.age)
        contentValues.put(EMAIL, std.email)
        val success = db.insert(TBL_USER,null,contentValues)
        db.close()
        return success
    }

    fun getAllUser(): ArrayList<UserModel>{
        val usrList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id:Int
        var name:String
        var age:Int
        var email:String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt((cursor.getColumnIndex("id")))
                name = cursor.getString((cursor.getColumnIndex("name")))
                age = cursor.getInt((cursor.getColumnIndex("age")))
                email = cursor.getString((cursor.getColumnIndex("email")))
                val usr = UserModel(id = id, name = name, age = age, email = email)
                usrList.add(usr)
            }while(cursor.moveToNext())
        }
        return usrList

    }
}