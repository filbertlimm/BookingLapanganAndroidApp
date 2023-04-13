package com.example.aplikasisewalapangan.AdminApp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.ClassUser.ListJadwal
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.R

class adapterListUser (private val listUser : MutableList<User>) :
    RecyclerView.Adapter<adapterListUser.ListViewHolder>(){

    private lateinit var listLapangan : InfoLapanganDatabase

    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById<TextView>(R.id.userLA3)
        val nomorTelepon = itemView.findViewById<TextView>(R.id.noTelpLA3)
        val status = itemView.findViewById<TextView>(R.id.statusLA)
        val btnSuspend = itemView.findViewById<Button>(R.id.suspendLA2)
        val btnRemove = itemView.findViewById<Button>(R.id.removeLA)
        val _btnPesan = itemView.findViewById<Button>(R.id.btn_pesan)
        val _tvLapanganDipesan = itemView.findViewById<TextView>(R.id.tvLapanganDipesan)

    }
    interface OnItemClickCallback{
        fun suspend(user: User)
        fun remove(user: User)
        fun detailUserLapangan(user:User)
    }
    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = OnItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListUser.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.userlist,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var user = listUser[position]
        holder.username.setText("Username : " + user.user.toString())
        holder.nomorTelepon.setText("Nomor Telepon : " + user.nomorTelepon.toString())
        holder.status.setText("Status : " + user.status.toString())
        holder.btnSuspend.setOnClickListener {
            onItemClickCallback.suspend(user)
        }
        holder.btnRemove.setOnClickListener {
            onItemClickCallback.remove(user)
        }
        holder._btnPesan.setOnClickListener{
            onItemClickCallback.detailUserLapangan(user)
//            startActivity(Intent(this, ListJadwal))
        }

//        for (lapangan in listLapangan) {
//            holder._tvLapanganDipesan.text = holder._tvLapanganDipesan.text + " || " +
//        }
    }
    fun isiData(list : List<User>){
        listUser.clear()
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}