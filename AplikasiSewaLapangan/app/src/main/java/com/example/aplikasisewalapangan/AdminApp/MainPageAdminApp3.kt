package com.example.aplikasisewalapangan.AdminApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.ClassUser.ListJadwal
import com.example.aplikasisewalapangan.ClassUser.adapterListLapangan
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseAdmin.AdminDatabase
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainPageAdminApp3 : AppCompatActivity() {
    private lateinit var DB : UserDatabase
    private lateinit var adapterUser : adapterListUser
    private var arrUser : MutableList<User> = mutableListOf()

    private lateinit var LapanganDB : InfoLapanganDatabase
    private var arrLapangan : MutableList<InfoLapangan> = mutableListOf()
    private lateinit var adapterListLapangan: adapterListLapangan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_admin_app3)
        DB = UserDatabase.getDatabase(this)
        adapterUser = adapterListUser(arrUser)
        var rvAdmin = findViewById<RecyclerView>(R.id.rvAdminApp3)
        val btnTab1 = findViewById<Button>(R.id.tab31)
        val btnTab2 = findViewById<Button>(R.id.tab32)
        val btnLogout = findViewById<FloatingActionButton>(R.id.logout3)

        rvAdmin.layoutManager = LinearLayoutManager(this)
        rvAdmin.adapter = adapterUser
        adapterUser.setOnItemClickCallback(object : adapterListUser.OnItemClickCallback{
            override fun suspend(user: User) {
                CoroutineScope(Dispatchers.IO).async {
                    DB.userDao().update(user.user.toString(),user.pass.toString(),user.nomorTelepon.toString(),"Suspended",user.id)
                    val allUser = DB.userDao().selectall()
                    withContext(Dispatchers.Main){
                        adapterUser.isiData(allUser)
                    }
                }
            }

            override fun remove(user: User) {
                CoroutineScope(Dispatchers.IO).async {
                    DB.userDao().delete(user)
                    val allUser = DB.userDao().selectall()
                    withContext(Dispatchers.Main){
                        adapterUser.isiData(allUser)
                    }
                }
            }

            override fun detailUserLapangan(user: User) {
                startActivity(Intent(this@MainPageAdminApp3, ListJadwal::class.java)
                    .apply {
                        putExtra("sessionId",user.id)
                        putExtra("NamaUser",user.user.toString())
                        putExtra("noTelpUser",user.nomorTelepon.toString())
                    }
                )
                finish()
            }

        })
        btnTab1.setOnClickListener {
            startActivity(Intent(this, MainPageAdminApp::class.java))
            finish()
        }
        btnTab2.setOnClickListener {
            startActivity(Intent(this, MainPageAdminApp2::class.java))
            finish()
        }
        btnLogout.setOnClickListener{
            startActivity(Intent(this, AdminAppLogin::class.java))
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            val user = DB.userDao().selectall()
            adapterUser.isiData(user)
        }
    }
}