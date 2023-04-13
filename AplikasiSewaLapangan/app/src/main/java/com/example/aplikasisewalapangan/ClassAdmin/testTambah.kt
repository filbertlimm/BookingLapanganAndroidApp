package com.example.aplikasisewalapangan.ClassAdmin

import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.aplikasisewalapangan.DatabaseAdmin.Admin
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDao
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.R
import com.example.aplikasisewalapangan.adapterBelumDipesan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class testTambah : AppCompatActivity() {
    val DB = InfoLapanganDatabase.getDatabase(this)
    private var arrAdmin : MutableList<InfoLapangan> = mutableListOf()
    private lateinit var adapterAdmin : adapterBelumDipesan
    private lateinit var listSub : MutableList<SubInfoLapangan>
    lateinit var selectedItem : String
    var sessID = 0
    var alamatAdmin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tambah)
        var _addNamaLap = findViewById<EditText>(R.id.addNamaLap)
        var _addJenis = findViewById<Spinner>(R.id.addJenisLap)
        var _addAlamat = findViewById<TextView>(R.id.addAlamat)
        var _addJamBuka = findViewById<EditText>(R.id.addJamBuka)
        var _addJamTutup = findViewById<EditText>(R.id.addJamTutup)
        var _addHarga = findViewById<EditText>(R.id.addHarga)
        var _konf = findViewById<Button>(R.id.konfirmLap)
        //fetch id & alamat
        sessID = intent.getIntExtra("sessionAdmin", 0)
        alamatAdmin = intent.getStringExtra("alamatAdmin").toString()
//        adapterAdmin = adapterBelumDipesan(arrAdmin,listSub,1)
        //pilihan dropdown
        var option = listOf("basket", "sepak", "badminton")
        //buat adapter dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, option)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Masukin adapter ke dropdown
        _addJenis.adapter = adapter
        //format tanggal dan waktu
        /*val currentDate = LocalDateTime.now()*/
        val formatter2 = DateTimeFormatter.ofPattern("HH:mm")
        val time = "07.00"
        val formattedTime = time.format(formatter2) //waktu
        _addJamBuka.setText(formattedTime)
        _addAlamat.text = alamatAdmin

        _addJenis.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item from the dropdown menu
                selectedItem = parent.getItemAtPosition(position).toString()
//
                // Get an instance of the Room database
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        _konf.setOnClickListener {
            val DD = InfoLapangan(0, _addNamaLap.text.toString(), selectedItem, alamatAdmin,_addHarga.text.toString().toInt(), sessID,_addJamBuka.text.toString(),_addJamTutup.text.toString())
            DB.infoLapanganDao().insert(DD)
            startActivity(Intent(this, MainPageAdmin::class.java)
                .apply{putExtra("sessionAdmin",sessID)
                putExtra("alamatAdmin", alamatAdmin)})
            finish()
        }
    }
}