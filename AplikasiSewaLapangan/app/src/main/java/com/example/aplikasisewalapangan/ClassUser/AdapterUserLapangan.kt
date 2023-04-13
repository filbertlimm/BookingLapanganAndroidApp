package com.example.aplikasisewalapangan.ClassUser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.R
//import com.example.aplikasisewalapangan.adapterListLapangan

class adapterUserLapangan (private val listLapangan : MutableList<SubInfoLapangan>,private val listLapangan2 : MutableList<InfoLapangan>) :
    RecyclerView.Adapter<adapterUserLapangan.ListViewHolder>(){

    private lateinit var onItemClickCallback : OnItemClickCallback

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //        val username = itemView.findViewById<TextView>(R.id.userLA)
        val _namaLapangan = itemView.findViewById<TextView>(R.id.namaLapangan)
        val _alamatLapangan = itemView.findViewById<TextView>(R.id.alamatLapangan)
        val _tanggalLapangan = itemView.findViewById<TextView>(R.id.tanggalLapangan)
        val _mulaiLapangan = itemView.findViewById<TextView>(R.id.mulaiLapangan)
        val _akhirLapangan = itemView.findViewById<TextView>(R.id.akhirLapangan)

    }

    interface OnItemClickCallback{
        //        fun decline(admin : Admin)
//        fun accept(admin: Admin)
//        fun onItemClicked(data:InfoLapangan)
    }

    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback, function: () -> Unit){
        this.onItemClickCallback = OnItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterUserLapangan.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_lapangan_dipesan,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {


        var lapangan = listLapangan[position]

        for(lapangan2 in listLapangan2){
            if(lapangan2.id == lapangan.idLapangan){
                holder._namaLapangan.setText(lapangan2.nama.toString())
                holder._alamatLapangan.setText(lapangan2.lokasi.toString())
            }
        }

        holder._mulaiLapangan.setText(lapangan.jamMulai.toString())
        holder._akhirLapangan.setText(lapangan.jamAkhir.toString())
        holder._tanggalLapangan.setText(lapangan.tanggal.toString())

    }

    fun isiData(list : List<SubInfoLapangan>, list2 : List<InfoLapangan>){
        listLapangan.clear()
        listLapangan.addAll(list)
        notifyDataSetChanged()

        listLapangan2.clear()
        listLapangan2.addAll(list2)
        notifyDataSetChanged()

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun getItemCount(): Int {
        return listLapangan.size
    }
}
