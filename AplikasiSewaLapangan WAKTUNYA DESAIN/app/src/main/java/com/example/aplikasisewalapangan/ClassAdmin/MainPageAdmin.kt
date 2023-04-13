package com.example.aplikasisewalapangan.ClassAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.ClassUser.KonfirmasiBayar
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseAdmin.AdminDatabase
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import com.example.aplikasisewalapangan.adapterBelumDipesan
import com.example.aplikasisewalapangan.adapterListAdmin
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MainPageAdmin : AppCompatActivity() {
    private lateinit var DB : InfoLapanganDatabase
    private lateinit var adapterAdmin : adapterBelumDipesan
    private var listSub : MutableList<SubInfoLapangan> = mutableListOf()
    private var arrAdmin : MutableList<InfoLapangan> = mutableListOf()
    private lateinit var listUser : MutableList<Admin>
    private lateinit var database : AdminDatabase
    var sessID = 0
    var alamatAdmin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_admin)
//        val btnBlmDpsn = findViewById<Button>(R.id.LapBelumDipesan)
        val btnSdhDpsn = findViewById<Button>(R.id.LapSudahDipesan)
        val _fab = findViewById<FloatingActionButton>(R.id.fab)
//        val _printSessId = findViewById<TextView>(R.id.printSessID)

        sessID = intent.getIntExtra("sessionAdmin", 0 )

//        btnBlmDpsn.setOnClickListener{
////            startActivity(Intent(this, LapanganBelumDipesan::class.java))
//            startActivity(Intent(this, LapanganBelumDipesan::class.java)
//                .apply{
//                    putExtra("sessionAdmin",sessID)
//                }
//            )
//            finish()
//        }
        btnSdhDpsn.setOnClickListener{
//            startActivity(Intent(this, LapanganSudahDipesan::class.java))
            startActivity(Intent(this, LapanganSudahDipesan::class.java)
                .apply{
                    putExtra("sessionAdmin",sessID)
                }
            )
            finish()
        }
        DB = InfoLapanganDatabase.getDatabase(this)
        adapterAdmin = adapterBelumDipesan(arrAdmin,listSub,1)
        var rvAdmin = findViewById<RecyclerView>(R.id.rvAdmin)
        rvAdmin.layoutManager = LinearLayoutManager(this)
        rvAdmin.adapter = adapterAdmin
        alamatAdmin = intent.getStringExtra("alamatAdmin").toString()
//        _printSessId.text = sessID.toString()

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
        _fab.setOnClickListener{
            startActivity(Intent(this, testTambah::class.java)
                .apply{
                    putExtra("sessionAdmin",sessID)
                    putExtra("alamatAdmin", alamatAdmin)
                }
                    )
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            val admin = DB.infoLapanganDao().getLapangan(sessID)
            adapterAdmin.isiData(admin,listSub,1)
            listUser = database.adminDao().selectall2()

        }
    }
}