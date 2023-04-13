package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.aplikasisewalapangan.R

class PembayaranUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_user)
        var sessionId = intent.getIntExtra("sessionId",0)

        var _btnBayar = findViewById<Button>(R.id.btnBayar)

        _btnBayar.setOnClickListener{
            startActivity(Intent(this,KonfirmasiBayar::class.java)
                .apply {
                    putExtra("sessionId", sessionId)
                }
            )
        }
    }
}