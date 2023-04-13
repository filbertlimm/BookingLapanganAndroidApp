package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.aplikasisewalapangan.ClassAdmin.testTambah
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ProfileUser : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var database : UserDatabase
    var sessionId = 0
    var nama = ""
    var noTelp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)
        database = UserDatabase.getDatabase(this)

        var _btnProf = findViewById<Button>(R.id.btnProf)
        var _namaUser = findViewById<TextView>(R.id.namaUser)
        var _telpUser = findViewById<EditText>(R.id.noTelpUser)
//        var _alamatUser = findViewById<EditText>(R.id.alamatUser)
        var _telpLama = findViewById<TextView>(R.id.telpLama)

        //fetch
        sessionId = intent.getIntExtra("sessionId",0)
        nama = intent.getStringExtra("NamaUser").toString()
        noTelp = intent.getStringExtra("noTelpUser").toString()

//        _telpUser.setText(noTelp)
        _telpLama.setText(noTelp)
        _namaUser.setText(nama)
//        var telepon= _telpUser.text.toString()

        _btnProf.setOnClickListener{
            database.userDao().gantiNoTelp(_telpUser.text.toString(),sessionId)

            startActivity(Intent(this, ListJadwal::class.java)
                .apply {
                    putExtra("sessionId", sessionId)
                    putExtra("NamaUser", nama)
                    putExtra("noTelpUser", _telpUser.text.toString())
                })

        }
    }
    override fun onStart() {
        super.onStart()

    }
}