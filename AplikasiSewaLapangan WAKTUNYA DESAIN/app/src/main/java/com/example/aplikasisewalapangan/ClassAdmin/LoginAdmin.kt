package com.example.aplikasisewalapangan.ClassAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.AdminApp.AdminAppLogin
import com.example.aplikasisewalapangan.ClassUser.LoginPage
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseAdmin.AdminDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoginAdmin : AppCompatActivity() {
    private lateinit var listAdmin : MutableList<Admin>
    private lateinit var database : AdminDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)
        database = AdminDatabase.getDatabase(this)
        var uid = 0
        var ketemu = false
        var ketemuUsername = false
        var belumVerifikasi = false
        var suspended = false
        val username = findViewById<EditText>(R.id.user3)
        val password = findViewById<EditText>(R.id.pass3)
        val buttonLogin = findViewById<Button>(R.id.login3)
        val buttonRegister = findViewById<Button>(R.id.regis3)
        val alert = findViewById<TextView>(R.id.tv3)
        var sessID = 0
        var alamatAdmin = ""
        val _btnBack = findViewById<Button>(R.id.btn_back2)
        _btnBack.setOnClickListener{
            startActivity(Intent(this, LoginPage::class.java))
        }
        buttonLogin.setOnClickListener {
            if (username.text.toString() == "loginadmin" && password.text.toString() == "passwordadmin"){
                startActivity(Intent(this, AdminAppLogin::class.java))
                finish()
            }else{
                for (admin in listAdmin){
                    if (admin.user == username.text.toString() && admin.pass == password.text.toString() && admin.statusVerifikasi == "Sudah"){
                        ketemu = true
                        uid = admin.id
                        sessID = admin.id
                        alamatAdmin = admin.alamat.toString()
                    }
                    else if (admin.user == username.text.toString() && admin.pass == password.text.toString() && admin.statusVerifikasi == "Suspended"){
                        suspended = true
                    }
                    else if (admin.user == username.text.toString() && admin.pass == password.text.toString()){
                        belumVerifikasi = true
                    }
                    else if (admin.user == username.text.toString()){
                        ketemuUsername = true
                    }
                }
                if (ketemu == true){
                    alert.setText("")
                    ketemu = false
                    startActivity(Intent(this, MainPageAdmin::class.java)
                        .apply{putExtra("sessionAdmin",sessID)
                            putExtra("alamatAdmin", alamatAdmin)}
                    )
//                    startActivity(intent)
                    username.setText("")
                    password.setText("")
                    finish()
                }
                else if (suspended == true){
                    suspended = false
                    alert.setText("Your Account Has Been Suspended")
                    username.setText("")
                    password.setText("")
                }
                else if (belumVerifikasi == true){
                    belumVerifikasi = false
                    alert.setText("Belum Di Verifikasi")
                    username.setText("")
                    password.setText("")
                }
                else if (ketemuUsername == true){
                    ketemuUsername = false
                    alert.setText("Wrong Password")
                    username.setText("")
                    password.setText("")
                }
                else{
                    alert.setText("Invalid Credential")
                    username.setText("")
                    password.setText("")
                }
            }
        }
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterAdmin::class.java))
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