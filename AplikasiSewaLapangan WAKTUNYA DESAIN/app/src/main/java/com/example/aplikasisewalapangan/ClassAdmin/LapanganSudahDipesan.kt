package com.example.aplikasisewalapangan.ClassAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapanganDatabase
import com.example.aplikasisewalapangan.R
import com.example.aplikasisewalapangan.adapterBelumDipesan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LapanganSudahDipesan : AppCompatActivity() {
    private lateinit var DB : InfoLapanganDatabase
    private lateinit var DB2 : SubInfoLapanganDatabase
    private lateinit var adapterAdmin : adapterBelumDipesan
    private var arrAdmin : MutableList<InfoLapangan> = mutableListOf()
    private var arrSub : MutableList<SubInfoLapangan> = mutableListOf()
    var sessID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapangan_sudah_dipesan)
//        val BtnBlmDpsn = findViewById<Button>(R.id.LapBelumDipesan)
        val BtnTmbhLap =findViewById<Button>(R.id.btnPageTambah)
        sessID = intent.getIntExtra("sessionAdmin", 0)
//        BtnBlmDpsn.setOnClickListener {
////            startActivity(Intent(this, LapanganBelumDipesan::class.java))
//            startActivity(Intent(this, LapanganBelumDipesan::class.java)
//                .apply{
//                    putExtra("sessionAdmin",sessID)
//                }
//            )
//            finish()
//        }
        BtnTmbhLap.setOnClickListener {
//            startActivity(Intent(this, MainPageAdmin::class.java))
            startActivity(Intent(this, MainPageAdmin::class.java)
                .apply{
                    putExtra("sessionAdmin",sessID)
                }
            )
            finish()
        }

        DB = InfoLapanganDatabase.getDatabase(this)
        DB2 = SubInfoLapanganDatabase.getDatabase(this)
        adapterAdmin = adapterBelumDipesan(arrAdmin,arrSub,0)
        var rvAdmin = findViewById<RecyclerView>(R.id.rvAdmin3)
        rvAdmin.layoutManager = LinearLayoutManager(this)
        rvAdmin.adapter = adapterAdmin
//            override fun accept(admin: Admin) {
//                CoroutineScope(Dispatchers.IO).async {
//                    DB.adminDao().update(admin.user.toString(),admin.pass.toString(),admin.nomorTelepon.toString(),admin.alamat.toString(),"Sudah",admin.id)
//                    val allAdmin = DB.adminDao().selectall("Belum")
//                    withContext(Dispatchers.Main){
//                        adapterAdmin.isiData(allAdmin)
//                    }
//                }
//            }
//        })
    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            CoroutineScope(Dispatchers.IO).async {
                arrAdmin = DB.infoLapanganDao().getLapangan(sessID)
                arrSub = DB2.subInfoLapanganDao().selectall()
                adapterAdmin.isiData(arrAdmin,arrSub,0)
            }
        }
    }
}