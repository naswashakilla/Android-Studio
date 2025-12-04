package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)

        val tvNama = findViewById<TextView>(R.id.HasilNama)
        val tvAlamat = findViewById<TextView>(R.id.HasilAlamat)
        val tvNomor = findViewById<TextView>(R.id.HasilNomor)
        val tvAgama = findViewById<TextView>(R.id.HasilAgama)
        val tvKelamin = findViewById<TextView>(R.id.HasilKelamin)
        val tvHobi = findViewById<TextView>(R.id.HasilHobi)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val nama = intent.getStringExtra("nama")
        val alamat = intent.getStringExtra("alamat")
        val nomor = intent.getStringExtra("nomor")
        val agama = intent.getStringExtra("agama")
        val kelamin = intent.getStringExtra("kelamin")
        val hobi = intent.getStringExtra("hobi")

        tvNama.text = nama
        tvAlamat.text = alamat
        tvNomor.text = nomor
        tvAgama.text = agama
        tvKelamin.text = kelamin
        tvHobi.text = hobi

        btnKembali.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}