package com.example.aplikasisewalapangan.AdminApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseAdmin.AdminDatabase
import com.example.aplikasisewalapangan.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainPageAdminApp2 : AppCompatActivity() {
    private lateinit var DB : AdminDatabase
    private lateinit var adapterAdmin : adapterListAdmin2
    private var arrAdmin : MutableList<Admin> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_admin_app2)
        DB = AdminDatabase.getDatabase(this)
        adapterAdmin = adapterListAdmin2(arrAdmin)
        var rvAdmin = findViewById<RecyclerView>(R.id.rvAdminApp2)
        val btnTab1 = findViewById<Button>(R.id.tab21)
        val btnTab3 = findViewById<Button>(R.id.tab23)
        val btnLogout = findViewById<FloatingActionButton>(R.id.logout2)
        rvAdmin.layoutManager = LinearLayoutManager(this)
        rvAdmin.adapter = adapterAdmin
        adapterAdmin.setOnItemClickCallback(object : adapterListAdmin2.OnItemClickCallback{
            override fun suspend(admin: Admin) {
                CoroutineScope(Dispatchers.IO).async {
                    DB.adminDao().update(admin.user.toString(),admin.pass.toString(),admin.nomorTelepon.toString(),admin.alamat.toString(),"Suspended",admin.id)
                    val allAdmin = DB.adminDao().selectall("Sudah")
                    withContext(Dispatchers.Main){
                        adapterAdmin.isiData(allAdmin)
                    }
                }
            }
        })
        btnTab1.setOnClickListener {
            startActivity(Intent(this, MainPageAdminApp::class.java))
            finish()
        }
        btnTab3.setOnClickListener {
            startActivity(Intent(this, MainPageAdminApp3::class.java))
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
            val admin = DB.adminDao().selectall("Sudah")
            adapterAdmin.isiData(admin)
        }
    }
}