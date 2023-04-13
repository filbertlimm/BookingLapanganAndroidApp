package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.ClassAdmin.LoginAdmin
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

class ListJadwal : AppCompatActivity() {
    //      Ambil Database Room
    private lateinit var listUser : MutableList<User>
    private lateinit var database : UserDatabase
//    private lateinit var _listPesanan : ListView

    private lateinit var adapterListLapangan: adapterListLapangan
    private lateinit var adapterUserLapangan: adapterUserLapangan

    private lateinit var databaseLapangan : InfoLapanganDatabase
    private lateinit var databaseLapangan2 : SubInfoLapanganDatabase

    var sessionId = 0
    var nama = ""
    var noTelp = ""

    private var arrLapangan : MutableList<InfoLapangan> = mutableListOf()
    private var arrLapangan2 : MutableList<SubInfoLapangan> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_jadwal)
//      Database Room initialize
        database = UserDatabase.getDatabase(this)
        databaseLapangan = InfoLapanganDatabase.getDatabase(this)
        databaseLapangan2 = SubInfoLapanganDatabase.getDatabase(this)
        adapterUserLapangan = adapterUserLapangan(arrLapangan2,arrLapangan)

//      Recycler
        var rvLapangan = findViewById<RecyclerView>(R.id.listPesanan)
        rvLapangan.layoutManager = LinearLayoutManager(this)
        rvLapangan.adapter = adapterUserLapangan

//         Intent get
        sessionId = intent.getIntExtra("sessionId",0)
        nama = intent.getStringExtra("NamaUser").toString()
        noTelp = intent.getStringExtra("noTelpUser").toString()

//        Initialize Buttons
        var _btnBasket = findViewById<Button>(R.id.btnBasket)
        var _btnBadminton = findViewById<Button>(R.id.btnBadminton)
        var _btnSepakbola = findViewById<Button>(R.id.btnSepakbola)
        var _btnProfile = findViewById<Button>(R.id.btnProfile)
        var _btnLogout = findViewById<Button>(R.id.btnLogout)
//        var _dummy = findViewById<TextView>(R.id.dummyNama)

//        Initialize ListView and TextView
//        val _status = findViewById<TextView>(R.id.tvStatus)

//        _dummy.setText(noTelp)

//        Initialize random variables
        _btnProfile.setOnClickListener{
            startActivity(Intent(this, ProfileUser::class.java)
                .apply {
                    putExtra("sessionId", sessionId)
                    putExtra("NamaUser", nama)
                    putExtra("noTelpUser", noTelp)
                }
            )
        }

        _btnLogout.setOnClickListener{
            startActivity(Intent(this,LoginPage::class.java))
        }

        _btnBasket.setOnClickListener{
            val eIntent = Intent(this@ListJadwal, JadwalJenisLapangan::class.java).apply {
                putExtra("jenisLapangan", "basket")
                putExtra("sessionId", sessionId)
            }
            startActivity(eIntent)
        }
        _btnSepakbola.setOnClickListener{
            val eIntent = Intent(this@ListJadwal, JadwalJenisLapangan::class.java).apply {
                putExtra("jenisLapangan", "sepak")
                putExtra("sessionId", sessionId)
            }
            startActivity(eIntent)
        }
        _btnBadminton.setOnClickListener{
            val eIntent = Intent(this@ListJadwal, JadwalJenisLapangan::class.java).apply {
                putExtra("jenisLapangan", "badminton")
                putExtra("sessionId", sessionId)
            }
            startActivity(eIntent)
        }

    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            //         Intent get
//            val sessionId = intent.getIntExtra("sessionId")
            listUser = database.userDao().selectall()
            arrLapangan = databaseLapangan.infoLapanganDao().selectall()
            arrLapangan2 = databaseLapangan2.subInfoLapanganDao().selectCust(sessionId)
            adapterUserLapangan.isiData(arrLapangan2,arrLapangan)

        }
    }
}
