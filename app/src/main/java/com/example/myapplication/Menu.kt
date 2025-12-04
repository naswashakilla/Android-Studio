package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Menu : AppCompatActivity() {

    private lateinit var namaPembeli: EditText
    private lateinit var rvMenu: RecyclerView
    private lateinit var btnPesan: Button
    private lateinit var menuAdapter: MenuAdapter

    private lateinit var rbCash: RadioButton
    private lateinit var rbTransfer: RadioButton
    private lateinit var rbQris: RadioButton

    private val daftarMenu = mutableListOf(
        MenuItem("Strawberry Cheesecake", 40000, imageResId = R.drawable.strawberry_cheesecake),
        MenuItem("Blueberry Cheesecake", 40000, imageResId = R.drawable.blueberry_cheesecake),
        MenuItem("Tiramisu Cake", 35000, imageResId = R.drawable.tiramisu),
        MenuItem("Tiramisu Matcha Cake", 40000, imageResId = R.drawable.tiramisumatcha),
        MenuItem("Matcha French Toast", 50000, imageResId = R.drawable.matchatoast),
        MenuItem("Strawberry Milkshake", 45000, imageResId = R.drawable.strawberrymilk),
        MenuItem("Orange Berry Maple", 48000, imageResId = R.drawable.orangejuice)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        namaPembeli = findViewById(R.id.namaPembeli)
        rvMenu = findViewById(R.id.rvMenu)
        btnPesan = findViewById(R.id.btnPesan)

        rbCash = findViewById(R.id.rbCash)
        rbTransfer = findViewById(R.id.rbTransfer)
        rbQris = findViewById(R.id.rbQris)

        menuAdapter = MenuAdapter(daftarMenu) {
        }

        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = menuAdapter

        btnPesan.setOnClickListener {
            val nama = namaPembeli.text.toString().trim()

            if (nama.isEmpty()) {
                Toast.makeText(this, "Nama Pembeli Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pesanan = daftarMenu.filter {
                it.jumlah > 0
            }

            if (pesanan.isEmpty()) {
                Toast.makeText(this, "Tidak Ada Pesanan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val total = pesanan.sumOf { it.jumlah * it.harga }

            val metodePembayaran = when {
                rbCash.isChecked -> "Cash"
                rbTransfer.isChecked -> "Transfer"
                rbQris.isChecked -> "Qris"
                else -> "Cash"
            }

            val kasir = "Admin"
            val kodePemesanan = "0RD-${(1000..9999).random()}"

            val intent = Intent(this, NotaHasil::class.java).apply{
                putExtra("nama", nama)
                putExtra("total", total)
                putExtra("payment", metodePembayaran)
                putExtra("kode", kodePemesanan)
                putParcelableArrayListExtra("pesanan", ArrayList(pesanan))
            }
            startActivity(intent)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}