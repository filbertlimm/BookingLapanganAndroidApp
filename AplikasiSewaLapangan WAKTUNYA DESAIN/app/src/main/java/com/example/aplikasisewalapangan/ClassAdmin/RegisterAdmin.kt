package com.example.aplikasisewalapangan.ClassAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseAdmin.AdminDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RegisterAdmin : AppCompatActivity() {
    private lateinit var listAdmin : MutableList<Admin>
    private lateinit var database : AdminDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_admin)
        database = AdminDatabase.getDatabase(this)
        var ketemu = false
        val username = findViewById<EditText>(R.id.user4)
        val password = findViewById<EditText>(R.id.pass4)
        val nomorTelepon = findViewById<EditText>(R.id.noTelp2)
        val alamat = findViewById<EditText>(R.id.alamat2)
        val buttonLogin = findViewById<Button>(R.id.login4)
        val buttonRegister = findViewById<Button>(R.id.regis4)
        val alert = findViewById<TextView>(R.id.tv4)
        buttonRegister.setOnClickListener {
            for (admin in listAdmin){
                if (admin.user == username.text.toString()){
                    ketemu = true
                }
            }
            if (ketemu == true){
                ketemu = false
                alert.setText("Maaf Username Telah Digunakan")
                username.setText("")
                password.setText("")
                nomorTelepon.setText("")
                alamat.setText("")
            }else{
                alert.setText("")
                CoroutineScope(Dispatchers.IO).async {
                    database.adminDao().insert(
                        Admin(0,username.text.toString(),password.text.toString(),nomorTelepon.text.toString(),alamat.text.toString(),"Belum")
                    )
                }
                startActivity(Intent(this, LoginAdmin::class.java))
                finish()
            }
        }
        buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginAdmin::class.java))
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            listAdmin = database.adminDao().selectall2()
        }
    }
}