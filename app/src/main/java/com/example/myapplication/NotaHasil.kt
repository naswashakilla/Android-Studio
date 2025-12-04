package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NotaHasil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nota_hasil)

        val tvNota: TextView = findViewById(R.id.tvNota)

        val nama = intent.getStringExtra("nama")
        val subTotal = intent.getIntExtra("total", 0)
        val metodePembayaran = intent.getStringExtra("payment") ?: "CASH"
        val kodePemesanan = intent.getStringExtra("kode") ?: "ORD-${(1000..9999).random()}"

        val pesanan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra("pesanan", MenuItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra<MenuItem>("pesanan")
        }

        val tanggal = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        val line = "----------------------------------------------\n"
        val sb = StringBuilder()

        sb.append("                  FLAVOURLILY\n")
        sb.append("               Pastry and Bakery\n")
        sb.append("               Jl. Slamet Riyadi\n")
        sb.append("                T : (021)3518289\n")
        sb.append(line)

        sb.append("                  PRINT BILL\n")
        sb.append(line)

        sb.append("Tanggal         : $tanggal\n")
        sb.append("Kode Pesanan   : $kodePemesanan\n")
        sb.append("Nama Pembeli   : $nama\n\n")

        sb.append("Daftar Pesanan:\n\n")

        pesanan?.forEach {
            val namaMenu = it.nama.take(18)
            val qty = it.jumlah
            val hargaTotal = it.harga * it.jumlah

            sb.append(
                String.format(
                    "%-18s x%-2d Rp%8d\n",
                    namaMenu,
                    qty,
                    hargaTotal
                )
            )
        }

        sb.append("\n")
        sb.append(line)

        val totalAkhir = subTotal

        sb.append(String.format("%-20s Rp%10d\n", "Sub Total", subTotal))
        sb.append(String.format("%-20s Rp%10d\n\n", "Total", totalAkhir))

        // PAYMENT METHOD
        sb.append(String.format("%-20s %s\n", "Payment", metodePembayaran))
        sb.append(String.format("%-20s Rp%10d\n", "Paid", totalAkhir))
        sb.append(String.format("%-20s Rp%10d\n", "Change", 0))

        sb.append(line)
        sb.append("    Thank You For Coming !!!")

        tvNota.text = sb.toString()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}