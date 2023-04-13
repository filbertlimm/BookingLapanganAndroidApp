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
import com.example.aplikasisewalapangan.adapterListAdmin
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainPageAdminApp : AppCompatActivity() {
    private lateinit var DB : AdminDatabase
    private lateinit var adapterAdmin : adapterListAdmin
    private var arrAdmin : MutableList<Admin> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_admin_app)
        DB = AdminDatabase.getDatabase(this)
        adapterAdmin = adapterListAdmin(arrAdmin)
        var rvAdmin = findViewById<RecyclerView>(R.id.rvAdminApp)
        val btnTab2 = findViewById<Button>(R.id.tab12)
        val btnTab3 = findViewById<Button>(R.id.tab13)
        val btnLogout = findViewById<FloatingActionButton>(R.id.logout1)
        rvAdmin.layoutManager = LinearLayoutManager(this)
        rvAdmin.adapter = adapterAdmin
        adapterAdmin.setOnItemClickCallback(object : adapterListAdmin.OnItemClickCallback{
            override fun decline(admin: Admin) {
                CoroutineScope(Dispatchers.IO).async {
                    DB.adminDao().delete(admin)
                    val allAdmin = DB.adminDao().selectall("Belum")
                    withContext(Dispatchers.Main){
                        adapterAdmin.isiData(allAdmin)
                    }
                }
            }

            override fun accept(admin: Admin) {
                CoroutineScope(Dispatchers.IO).async {
                    DB.adminDao().update(admin.user.toString(),admin.pass.toString(),admin.nomorTelepon.toString(),admin.alamat.toString(),"Sudah",admin.id)
                    val allAdmin = DB.adminDao().selectall("Belum")
                    withContext(Dispatchers.Main){
                        adapterAdmin.isiData(allAdmin)
                    }
                }
            }
        })
        btnTab2.setOnClickListener {
            startActivity(Intent(this, MainPageAdminApp2::class.java))
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
            val admin = DB.adminDao().selectall("Belum")
            adapterAdmin.isiData(admin)
        }
    }
}