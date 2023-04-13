package com.example.aplikasisewalapangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin

class adapterListAdmin (private val listAdmin : MutableList<Admin>) :
    RecyclerView.Adapter<adapterListAdmin.ListViewHolder>(){
    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById<TextView>(R.id.userLA)
        val nomorTelepon = itemView.findViewById<TextView>(R.id.noTelpLA)
        val alamat = itemView.findViewById<TextView>(R.id.alamatLA)
        val btnAccept = itemView.findViewById<Button>(R.id.accLA)
        val btnDecline = itemView.findViewById<Button>(R.id.decLA)
    }
    interface OnItemClickCallback{
        fun decline(admin : Admin)
        fun accept(admin: Admin)
    }
    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = OnItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListAdmin.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adminlist,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var admin = listAdmin[position]
        holder.username.setText("Username : " + admin.user.toString())
        holder.nomorTelepon.setText("Nomor Telepon : " + admin.nomorTelepon.toString())
        holder.alamat.setText("Alamat : " + admin.alamat.toString())
        holder.btnDecline.setOnClickListener {
            onItemClickCallback.decline(admin)
        }
        holder.btnAccept.setOnClickListener {
            onItemClickCallback.accept(admin)
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