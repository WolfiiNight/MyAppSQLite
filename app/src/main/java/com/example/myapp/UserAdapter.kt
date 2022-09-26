package com.example.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var usrList: ArrayList<UserModel> = ArrayList()

    fun addItems(items: ArrayList<UserModel>){
        this.usrList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_usr,parent,false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val usr = usrList[position]
        holder.bindView(usr)
    }

    override fun getItemCount(): Int {
        return usrList.size
    }

     class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var age = view.findViewById<TextView>(R.id.tvAge)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        private var delete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(usr: UserModel){
            id.text = usr.id.toString()
            name.text = usr.name
            age.text = usr.age.toString()
            email.text = usr.email
        }
     }



}