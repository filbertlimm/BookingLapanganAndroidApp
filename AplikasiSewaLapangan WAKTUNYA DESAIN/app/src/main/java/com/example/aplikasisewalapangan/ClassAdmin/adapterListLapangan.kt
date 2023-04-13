package com.example.aplikasisewalapangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan

class adapterListLapangan (private val listLapangan : MutableList<InfoLapangan>) :
    RecyclerView.Adapter<adapterListLapangan.ListViewHolder>(){
    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvNama = itemView.findViewById<TextView>(R.id.tvNamaLapangan)
        val tvAlamat = itemView.findViewById<TextView>(R.id.tvAlamatLapangan)
        val tvMulai = itemView.findViewById<TextView>(R.id.tvJamBuka)
        val tvAkhir = itemView.findViewById<TextView>(R.id.tvJamTutup)
        val tvHarga = itemView.findViewById<TextView>(R.id.tvHarga)
    }
    interface OnItemClickCallback{

    }
    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = OnItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListLapangan.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lapanganlist,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var admin = listLapangan[position]
    }
    fun isiData(list : List<InfoLapangan>){
        listLapangan.clear()
        listLapangan.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listLapangan.size
    }
}