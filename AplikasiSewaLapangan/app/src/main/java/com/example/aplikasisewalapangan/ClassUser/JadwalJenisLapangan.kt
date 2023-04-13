package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class JadwalJenisLapangan : AppCompatActivity() {
    private lateinit var listUser : MutableList<User>
    private lateinit var database : UserDatabase

//    Lapangan Stuff
    private lateinit var adapterListLapangan: adapterListLapangan
    private lateinit var databaseLapangan : InfoLapanganDatabase
    private lateinit var databaseSubLapangan : SubInfoLapangan
    private var arrLapangan : MutableList<InfoLapangan> = mutableListOf()

    var sessionId = 0
    var jenisLapangan = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_jenis_lapangan)

        //         Intent get
        sessionId = intent.getIntExtra("sessionId",0)
        jenisLapangan = intent.getStringExtra("jenisLapangan").toString()

//        Initialize Database
        database = UserDatabase.getDatabase(this)
        databaseLapangan = InfoLapanganDatabase.getDatabase(this)
        adapterListLapangan = adapterListLapangan(arrLapangan)

        //Recycler View
        val _rvJenisLapangan = findViewById<RecyclerView>(R.id.rvJenisLapangan)
        _rvJenisLapangan.layoutManager = LinearLayoutManager(this)
        _rvJenisLapangan.adapter = adapterListLapangan

        var _tvJenis = findViewById<TextView>(R.id.tvJenis)

        if(jenisLapangan=="sepak"){
            _tvJenis.setText("Sepak Bola")

        }else{
            _tvJenis.setText(jenisLapangan)
        }

        //CLICK ON NAME LAPANGAN UNTUK PINDAH!!
        adapterListLapangan.setOnItemClickCallback(object : adapterListLapangan.OnItemClickCallback{
             override fun onItemClicked(data:InfoLapangan){
//                Toast.makeText(this@JadwalJenisLapangan, data.nama, Toast.LENGTH_LONG).show()
//                 val eIntent = Intent(this@JadwalJenisLapangan, DetailLapangan::class.java).apply {
//                 }
//                 startActivity(eIntent)

                val intent = Intent(this@JadwalJenisLapangan, DetailLapangan::class.java).apply {
                    putExtra("idLapangan",data.id.toString())
                    putExtra("namaLapangan",data.nama.toString())
                    putExtra("alamatLapangan",data.lokasi.toString())
                    putExtra("jamMulai",data.jamMulai.toString())
                    putExtra("jamAkhir",data.jamAkhir.toString())
                    putExtra("hargaLapangan",data.harga.toString())

                    putExtra("jenisLapangan", data.jenis.toString())

                    putExtra("sessionId", sessionId)
                    putExtra("idPemilik",data.idPemilik.toString())
                }
                startActivity(intent)
            }
        })


    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            val note = databaseLapangan.infoLapanganDao().selectJenis(jenisLapangan)
            adapterListLapangan.isiData(note)
        }
    }
}