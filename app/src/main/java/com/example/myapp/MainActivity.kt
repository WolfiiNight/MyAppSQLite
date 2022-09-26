package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edAge: EditText
    private lateinit var edEmail: EditText
    private lateinit var btnInsert: Button
    private lateinit var btnView: Button

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: UserAdapter? = null

//this is a second test


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        sqliteHelper = SQLiteHelper(this)
        btnInsert.setOnClickListener{ addUser() }
        btnView.setOnClickListener { getUsers() }
        adapter?.setOneClickItem {
            Toast.makeText(this,it.name,Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUsers() {
        val usrList = sqliteHelper.getAllUser()
        Log.e("pppp","${usrList.size}")

        adapter?.addItems(usrList)
    }

    private fun addUser() {
        val name = edName.text.toString()
        val email = edEmail.text.toString()
        val age = edAge.text.toString().toInt()

        if(name.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter required fild",Toast.LENGTH_SHORT).show()
        }else{
            val usr = UserModel(name = name, email = email, age = age)
            val status = sqliteHelper.insertUser(usr)
            //Check inster success or not success
            if (status > -1){
                Toast.makeText(this,"User Added...",Toast.LENGTH_SHORT).show()
                clearEditText()
                getUsers()
            }else{
                //Gestion de l'erreur
                Toast.makeText(this , "User not saved...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edName.setText("")
        edAge.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        edName = findViewById(R.id.name)
        edEmail = findViewById(R.id.email)
        edAge = findViewById(R.id.age)
        btnInsert = findViewById(R.id.btnInsert)
        btnView = findViewById(R.id.btnView)
        recyclerView = findViewById(R.id.recycleUser)
    }
}