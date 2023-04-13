package com.example.aplikasisewalapangan.ClassUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapangan
import com.example.aplikasisewalapangan.DatabaseInfoLapangan.InfoLapanganDatabase
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapangan
import com.example.aplikasisewalapangan.DatabaseSubInfoLapangan.SubInfoLapanganDatabase
import com.example.aplikasisewalapangan.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.time.LocalDateTime

class DetailLapangan : AppCompatActivity() {

    private lateinit var databaseSubInfoLapangan : SubInfoLapanganDatabase
    var sessionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_lapangan)
        databaseSubInfoLapangan = SubInfoLapanganDatabase.getDatabase(this)

        sessionId = intent.getIntExtra("sessionId",0)
        var formatJam = arrayListOf<String>()
        var selectedJamAwal : String? = "07.00"
        var selectedJamAkhir : String? = "08.00"
        var _detNama = findViewById<TextView>(R.id.detNama)
        var _detAlamat = findViewById<TextView>(R.id.detAlamat)
        var _detJenis = findViewById<TextView>(R.id.detJenis)
        var _detHarga = findViewById<TextView>(R.id.detHarga)
        var _btnPesan = findViewById<Button>(R.id.detPesan)
        var _dropAwal = findViewById<Spinner>(R.id.detJamMulai)
        var _dropAkhir = findViewById<Spinner>(R.id.detJamAkhir)
        var _detTanggal = findViewById<EditText>(R.id.detTanggal)
        var bentrok = false
        val currentDate = LocalDateTime.now()
        _detTanggal.setText(currentDate.toString().substring(0,10))
        for (i in 1..24){
            if(i < 10){
                formatJam.add("0" + i + ".00")
            }else{
                formatJam.add("" + i + ".00")
            }
        }
        val intentId = intent.getStringExtra("idLapangan")
        val intentNamaLapangan = intent.getStringExtra("namaLapangan")
        val intentAlamat = intent.getStringExtra("alamatLapangan")
        val intentHarga = intent.getStringExtra("hargaLapangan")
        val intentJamBuka = intent.getStringExtra("jamMulai")
        val intentJamTutup = intent.getStringExtra("jamAkhir")
        val intentJenis = intent.getStringExtra("jenisLapangan")
        val idPemilik = intent.getStringExtra("idPemilik").toString().toInt()
        var indexAwal = 0
        var indexAkhir = 0
        var cariJamAwal = true
        for (i in formatJam){
            if (i == intentJamBuka){
                cariJamAwal = false
            }else if (i == intentJamTutup){
                indexAkhir++
                break
            }else{
                if (cariJamAwal == true){
                    indexAwal++
                }
                indexAkhir++
            }
        }
        var listtAwal = arrayListOf<String>()
        var listtAkhir = arrayListOf<String>()
        indexAkhir--
        for (i in indexAwal..indexAkhir){
            listtAwal.add(formatJam.get(i))
        }
        indexAkhir++
        indexAwal++
        for (j in indexAwal..indexAkhir){
            listtAkhir.add(formatJam.get(j))
        }
        //buat adapter dropdown jamMulai
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listtAwal)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Masukin adapter ke dropdown
        _dropAwal.adapter = adapter
        //buat adapter dropdown jamAkhir
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, listtAkhir)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Masukin adapter ke dropdown
        _dropAkhir.adapter = adapter2
        _dropAwal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item from the dropdown menu
                selectedJamAwal = parent.getItemAtPosition(position).toString()
                if (selectedJamAkhir?.substring(0,2)!!.toInt() > selectedJamAwal?.substring(0,2)!!.toInt()){
                    var harga = (selectedJamAkhir?.substring(0,2)!!.toInt() - selectedJamAwal?.substring(0,2)!!.toInt()) * intentHarga!!.toInt()
                    _detHarga.setText(harga.toString())
                }
//
                // Get an instance of the Room database
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        _dropAkhir.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item from the dropdown menu
                selectedJamAkhir = parent.getItemAtPosition(position).toString()
                if (selectedJamAkhir?.substring(0,2)!!.toInt() > selectedJamAwal?.substring(0,2)!!.toInt()){
                    var harga = (selectedJamAkhir?.substring(0,2)!!.toInt() - selectedJamAwal?.substring(0,2)!!.toInt()) * intentHarga!!.toInt()
                    _detHarga.setText(harga.toString())
                }
//
                // Get an instance of the Room database
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        _detNama.setText(intentNamaLapangan.toString())
        _detAlamat.setText(intentAlamat.toString())
        _detHarga.setText(intentHarga.toString())

        var logo = ""
        if(intentJenis=="basket"){
            logo = "🏀"
        }else if(intentJenis=="sepak"){
            logo ="⚽"
        }else if(intentJenis=="badminton"){
            logo="🏸"
        }
        _detJenis.setText(logo)

        _btnPesan.setOnClickListener{

            CoroutineScope(Dispatchers.IO).async {
                bentrok = false
                val listLapangan = databaseSubInfoLapangan.subInfoLapanganDao().selectDate(_detTanggal.text.toString(), intentId.toString().toInt())
                for (lapangan in listLapangan){
                    var awal1 = 0
                    var awal2 = 0
                    var akhir1 = 0
                    var akhir2 = 0
                    if (selectedJamAwal?.substring(0,1) == "0"){
                        awal1 = selectedJamAwal?.substring(1,2).toString().toInt()
                    }else{
                        awal1 = selectedJamAwal?.substring(0,2).toString().toInt()
                    }
                    if (selectedJamAkhir?.substring(0,1) == "0"){
                        akhir1 = selectedJamAkhir?.substring(1,2).toString().toInt()
                    }else{
                        akhir1 = selectedJamAkhir?.substring(0,2).toString().toInt()
                    }
                    if (lapangan.jamMulai?.substring(0,1) == "0"){
                        awal2 = lapangan.jamMulai?.substring(1,2).toString().toInt()
                    }else{
                        awal2 = lapangan.jamMulai?.substring(0,2).toString().toInt()
                    }
                    if (lapangan.jamAkhir?.substring(0,1) == "0"){
                        akhir2 = lapangan.jamAkhir?.substring(1,2).toString().toInt()
                    }else{
                        akhir2 = lapangan.jamAkhir?.substring(0,2).toString().toInt()
                    }
                    if (awal1 >= awal2 && awal1 < akhir2){
                        bentrok = true
                    }
                    if (akhir1 > awal2 && akhir1 <= akhir2){
                        bentrok = true
                    }
                    if (akhir1 <= awal1){
                        bentrok = true
                    }
                    println(akhir1)
                    println(akhir2)
                    println(awal1)
                    println(awal2)
                    println(bentrok)
                }
                if (bentrok == false){
                    CoroutineScope(Dispatchers.IO).async {
                        databaseSubInfoLapangan.subInfoLapanganDao().insert(SubInfoLapangan(0,idPemilik,intentId.toString().toInt(),sessionId,_detTanggal.text.toString(),selectedJamAwal.toString(),selectedJamAkhir.toString()))
                    }
                    startActivity(Intent(this@DetailLapangan,PembayaranUser::class.java).apply {
                        putExtra("sessionId", sessionId)
                    })
                }
            }
        }

    }
}
