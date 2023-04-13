package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.ClassAdmin.LoginAdmin
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoginPage : AppCompatActivity() {
    private lateinit var listUser : MutableList<User>
    private lateinit var database : UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = UserDatabase.getDatabase(this)
        var ketemu = false
        var ketemuUsername = false
        var suspended = false
        val username = findViewById<EditText>(R.id.user1)
        val password = findViewById<EditText>(R.id.pass1)
        val buttonLogin = findViewById<Button>(R.id.login1)
        val buttonRegister = findViewById<Button>(R.id.regis1)
        val buttonAdmin = findViewById<Button>(R.id.adminpage1)
        val alert = findViewById<TextView>(R.id.tv1)

        var sessionId = 0
        var nama = ""
        var noTelp = ""
        buttonLogin.setOnClickListener {
            for (user in listUser){
                if (user.user == username.text.toString() && user.pass == password.text.toString() && user.status == "Active") {
                    ketemu = true
                    sessionId = user.id
                    nama = user.user.toString()
                    noTelp = user.nomorTelepon.toString()
                }
                else if (user.user == username.text.toString() && user.pass == password.text.toString() && user.status == "Suspended") {
                    suspended = true
                }
                else if (user.user == username.text.toString()){
                    ketemuUsername = true
                }
            }
            if (ketemu == true){
                alert.setText("")
                ketemu = false
//                startActivity(Intent(this, ListJadwal::class.java))
                startActivity(Intent(this@LoginPage, ListJadwal::class.java).apply {
                    putExtra("sessionId", sessionId)
                    putExtra("NamaUser", nama)
                    putExtra("noTelpUser", noTelp)
                })
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
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
            finish()
        }
        buttonAdmin.setOnClickListener {
            startActivity(Intent(this, LoginAdmin::class.java))
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