package com.example.aplikasisewalapangan.ClassUser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.R
//import com.example.aplikasisewalapangan.adapterListLapangan

class adapterListLapangan (private val listLapangan : MutableList<InfoLapangan>) :
    RecyclerView.Adapter<adapterListLapangan.ListViewHolder>(){

    private lateinit var onItemClickCallback : OnItemClickCallback

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        val username = itemView.findViewById<TextView>(R.id.userLA)
//        val nomorTelepon = itemView.findViewById<TextView>(R.id.noTelpLA)
//        val alamat = itemView.findViewById<TextView>(R.id.alamatLA)
//        val btnAccept = itemView.findViewById<Button>(R.id.accLA)
//        val btnDecline = itemView.findViewById<Button>(R.id.decLA)

        val _namaLapangan = itemView.findViewById<TextView>(R.id.tvNamaLapangan)
        val _alamatLapangan = itemView.findViewById<TextView>(R.id.tvAlamatLapangan)
        val _jamBuka = itemView.findViewById<TextView>(R.id.tvJamBuka)
        val _jamTutup = itemView.findViewById<TextView>(R.id.tvJamTutup)
        val _jenisLogo = itemView.findViewById<TextView>(R.id.tvJenisLapanganLogo)
        val _harga = itemView.findViewById<TextView>(R.id.tvHarga)

    }

    interface OnItemClickCallback{
//        fun decline(admin : Admin)
//        fun accept(admin: Admin)
        fun onItemClicked(data:InfoLapangan)
    }

    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback, function: () -> Unit){
        this.onItemClickCallback = OnItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListLapangan.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lapanganlist,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var lapangan = listLapangan[position]

//        holder.username.setText(lapangan.user.toString())
//        holder.nomorTelepon.setText(lapangan.nomorTelepon.toString())
//        holder.alamat.setText(lapangan.alamat.toString())

        holder._namaLapangan.setText(lapangan.nama.toString())
        holder._alamatLapangan.setText(lapangan.lokasi.toString())
        holder._jamBuka.setText(lapangan.jamMulai.toString())
        holder._jamTutup.setText(lapangan.jamAkhir.toString())

        var logo = ""
        if(lapangan.jenis=="basket"){
            logo = "🏀"
        }else if(lapangan.jenis=="sepak"){
            logo ="⚽"
        }else if(lapangan.jenis=="badminton"){
            logo="🏸"
        }
        holder._jenisLogo.setText(logo)

        holder._harga.setText(lapangan.harga.toString())

//        holder.btnDecline.setOnClickListener {
//            onItemClickCallback.decline(lapangan)
//        }
//
//        holder.btnAccept.setOnClickListener {
//            onItemClickCallback.accept(lapangan)
//        }

        holder._namaLapangan.setOnClickListener{
            onItemClickCallback.onItemClicked(listLapangan[position])
        }
    }

    fun isiData(list : List<InfoLapangan>){
        listLapangan.clear()
        listLapangan.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun getItemCount(): Int {
        return listLapangan.size
    }
    }
