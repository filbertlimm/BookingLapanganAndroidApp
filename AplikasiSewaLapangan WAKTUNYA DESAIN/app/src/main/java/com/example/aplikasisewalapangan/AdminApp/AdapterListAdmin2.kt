package com.example.aplikasisewalapangan.AdminApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.R

class adapterListAdmin2 (private val listAdmin : MutableList<Admin>) :
    RecyclerView.Adapter<adapterListAdmin2.ListViewHolder>(){
    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById<TextView>(R.id.userLA2)
        val nomorTelepon = itemView.findViewById<TextView>(R.id.noTelpLA2)
        val alamat = itemView.findViewById<TextView>(R.id.alamatLA2)
        val btnSuspend = itemView.findViewById<Button>(R.id.suspendLA)
    }
    interface OnItemClickCallback{
        fun suspend(admin : Admin)
    }
    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = OnItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListAdmin2.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adminlist2,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var admin = listAdmin[position]
        holder.username.setText("Username : " + admin.user.toString())
        holder.nomorTelepon.setText("Nomor Telepon : " + admin.nomorTelepon.toString())
        holder.alamat.setText("Alamat : " + admin.alamat.toString())
        holder.btnSuspend.setOnClickListener {
            onItemClickCallback.suspend(admin)
        }
    }
    fun isiData(list : List<Admin>){
        listAdmin.clear()
        listAdmin.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listAdmin.size
    }
}