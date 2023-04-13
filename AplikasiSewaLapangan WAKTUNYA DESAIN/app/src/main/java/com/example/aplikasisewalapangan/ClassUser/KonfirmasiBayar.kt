package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.aplikasisewalapangan.DatabaseUser.User
import com.example.aplikasisewalapangan.DatabaseUser.UserDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class KonfirmasiBayar : AppCompatActivity() {
    private lateinit var listUser : MutableList<User>
    private lateinit var database : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_bayar)

        var _btnKonf = findViewById<Button>(R.id.btnKonf)
        var sessionId = intent.getIntExtra("sessionId",0)
        var nama =""
        var noTelp = ""
        database = UserDatabase.getDatabase(this)

//        val img = findViewById<ImageView>(R.id.centangBayar).apply {
//            setBackgroundResource(R.drawable.avd_anim)
//            checkAnim = background as AnimationDrawable
//        }

//            img.setOnClickListener {
        _btnKonf.setOnClickListener{
//            intent ke main menu user
            for (user in listUser){
                if (user.id == sessionId) {
                    nama = user.user.toString()
                    noTelp = user.nomorTelepon.toString()
                }
            }

            startActivity(Intent(this@KonfirmasiBayar,ListJadwal::class.java)
                .apply {
                putExtra("sessionId", sessionId)
                putExtra("NamaUser", nama)
                putExtra("noTelpUser", noTelp)
            })
        }

    }
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).async {
            listUser = database.userDao().selectall()
        }
    }
}