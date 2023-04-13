package com.example.aplikasisewalapangan.AdminApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.ClassAdmin.LoginAdmin
import com.example.aplikasisewalapangan.R



class AdminAppLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_app_login)
        val username = findViewById<EditText>(R.id.user5)
        val password = findViewById<EditText>(R.id.pass5)
        val pin = findViewById<EditText>(R.id.pin)
        val buttonLogin = findViewById<Button>(R.id.login5)
        val alert = findViewById<TextView>(R.id.tv5)
        var ketemu = false
        val listUser = mutableListOf<AdminApp>()
        listUser.add(AdminApp("mika","mika123","135246"))
        val _btnBack = findViewById<Button>(R.id.btn_back)

        _btnBack.setOnClickListener{
            startActivity(Intent(this, LoginAdmin::class.java))
        }

        buttonLogin.setOnClickListener {
            for (adminApp in listUser){
                if (adminApp.username == username.text.toString() && adminApp.password == password.text.toString() && adminApp.pin == pin.text.toString()){
                    ketemu = true
                    startActivity(Intent(this, MainPageAdminApp::class.java))
                    finish()
                }
            }
            username.setText("")
            password.setText("")
            pin.setText("")
            if (ketemu == false){
                alert.setText("Invalid Credential")
            }
        }
    }
}