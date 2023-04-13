package com.example.aplikasisewalapangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import org.w3c.dom.Text

class adapterBelumDipesan (private val listLapangan : MutableList<InfoLapangan>,private val listLapangan2 : MutableList<SubInfoLapangan>,private var mode : Int) :
    RecyclerView.Adapter<adapterBelumDipesan.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama = itemView.findViewById<TextView>(R.id.tvNamaLapangan)
        val jenis = itemView.findViewById<TextView>(R.id.tvJenisLapanganLogo)
        val alamat = itemView.findViewById<TextView>(R.id.tvAlamatLapangan)
        val jamMulai = itemView.findViewById<TextView>(R.id.tvJamBuka)
        val jamAkhir = itemView.findViewById<TextView>(R.id.tvJamTutup)
        val harga = itemView.findViewById<TextView>(R.id.tvHarga)

        val info = itemView.findViewById<TextView>(R.id.tvList)
    }

    interface OnItemClickCallback {

    }

    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = OnItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterBelumDipesan.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.lapanganlist, parent, false)
        return ListViewHolder(view)
    }
    var text = ""

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var adminLapangan = listLapangan[position]
        if (mode == 0) {
            for (lapangan in listLapangan2) {
                if (lapangan.idLapangan.toString() == adminLapangan.id.toString()) {
                    text = text + lapangan.tanggal.toString() + " : " + lapangan.jamMulai.toString() + " - " + lapangan.jamAkhir.toString() + "\n"
                }
            }
        }
//        text = "||"
        holder.nama.setText("Nama Lapangan : " + adminLapangan.nama.toString())
        holder.alamat.setText("Alamat : " + adminLapangan.lokasi.toString())
        holder.jamMulai.setText("" + adminLapangan.jamMulai.toString())
        holder.jamAkhir.setText("" + adminLapangan.jamAkhir.toString())
        holder.harga.setText("Rp " + adminLapangan.harga.toString())
//        holder.harga.setText(text)
//        holder.jenis.setText(text)
        holder.info.setText(text)
    }

    fun isiData(list: List<InfoLapangan>, list2: List<SubInfoLapangan>, Mode: Int) {
        listLapangan.clear()
        listLapangan.addAll(list)
        notifyDataSetChanged()

        listLapangan2.clear()
        listLapangan2.addAll(list2)
        notifyDataSetChanged()
        mode = Mode
    }
    override fun getItemCount(): Int {
        return listLapangan.size
    }
}
