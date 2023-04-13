package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RegisterPage : AppCompatActivity() {
    private lateinit var listUser : MutableList<User>
    private lateinit var database : UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        database = UserDatabase.getDatabase(this)
        var ketemu = false
        val username = findViewById<EditText>(R.id.user2)
        val password = findViewById<EditText>(R.id.pass2)
        val nomorTelepon = findViewById<EditText>(R.id.noTelp1)
        val buttonLogin = findViewById<Button>(R.id.login2)
        val buttonRegister = findViewById<Button>(R.id.regis2)
        val alert = findViewById<TextView>(R.id.tv2)
        var text = ""
        buttonRegister.setOnClickListener {
            for (user in listUser){
                if(username.text.toString() == user.user){
                    ketemu = true
                }
            }
            if (ketemu == true){
                ketemu = false
                alert.setText("Maaf Username Telah Digunakan")
                username.setText("")
                password.setText("")
                nomorTelepon.setText("")
            }else{
                alert.setText("")
                CoroutineScope(Dispatchers.IO).async {
                    database.userDao().insert(
                        User(0,username.text.toString(),password.text.toString(),nomorTelepon.text.toString(),"Active")
                    )
                }
                startActivity(Intent(this, LoginPage::class.java))
                finish()
            }
        }
        buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            listUser = database.userDao().selectall()
        }
    }
}