package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Form : AppCompatActivity() {

    lateinit var etNama: EditText
    lateinit var etAlamat:EditText
    lateinit var etNomor:EditText
    lateinit var btnSimpan: Button
    lateinit var jenisAgama: Spinner
    lateinit var jeniskelamin: RadioGroup
    lateinit var Membaca: CheckBox
    lateinit var Menulis: CheckBox
    lateinit var Olahraga: CheckBox
    lateinit var Makan: CheckBox




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        init()

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val nomor = etNomor.text.toString()
            val agama = jenisAgama.selectedItem.toString()
            val kelamin =
                if (jeniskelamin.checkedRadioButtonId == R.id.perempuan){
                    "Perempuan"
                } else "Laki-laki"

            val hobiList = mutableListOf<String>()
            if (Membaca.isChecked) hobiList.add("Membaca")
            if (Menulis.isChecked) hobiList.add("Menulis")
            if (Olahraga.isChecked) hobiList.add("Olahraga")
            if (Makan.isChecked) hobiList.add("Makan")
            val hobi = if (hobiList.isEmpty())"Tidak ada" else hobiList.joinToString(", ")


            val keHasil = Intent(this@Form, HasilForm::class.java)

            keHasil.putExtra("nama", nama)
            keHasil.putExtra("alamat", alamat)
            keHasil.putExtra("nomor", nomor)
            keHasil.putExtra("agama", agama)
            keHasil.putExtra("kelamin", kelamin)
            keHasil.putExtra("hobi", hobi)

            startActivity(keHasil)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init(){
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNomor = findViewById(R.id.etNomor)
        jenisAgama = findViewById(R.id.jenisagama)
        jeniskelamin = findViewById(R.id.jeniskelamin)
        Membaca = findViewById(R.id.membaca)
        Menulis = findViewById(R.id.menulis)
        Olahraga = findViewById(R.id.olahraga)
        Makan = findViewById(R.id.makan)


        btnSimpan = findViewById(R.id.btnSimpan)

    }
}