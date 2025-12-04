package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.Dialog
import kotlin.jvm.java
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var cvForm: CardView
    lateinit var cvCalculator:CardView
    lateinit var cvMenu:CardView
    lateinit var cvKonversiSuhu:CardView
    lateinit var cvProfile:CardView
    lateinit var cvExit:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        init()

        cvForm.setOnClickListener{
            Toast.makeText(this@MainActivity, "Card View Form di klik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Form::class.java)
            startActivity(intent)
        }
        cvCalculator.setOnClickListener{
            Toast.makeText(this@MainActivity, "Card View Calculator di klik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Kalkulator::class.java)
            startActivity(intent)
        }
        cvMenu.setOnClickListener{
            Toast.makeText(this@MainActivity, "Card View Menu di klik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Menu::class.java)
            startActivity(intent)
        }
        cvKonversiSuhu.setOnClickListener{
            Toast.makeText(this@MainActivity, "Card View Konversi Suhu di klik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, KonversiSuhu::class.java)
            startActivity(intent)
        }
        cvProfile.setOnClickListener{
            Toast.makeText(this@MainActivity, "Card View Profile di klik", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Profile::class.java)
            startActivity(intent)
        }

        cvExit.setOnClickListener{
            val customDialog =Dialog(this)
            customDialog.setContentView(R.layout.activity_exit)
            customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val btnTidak = customDialog.findViewById<Button>(R.id.btnTidak)
            val btnKeluar = customDialog.findViewById<Button>(R.id.btnKeluar)

            btnTidak.setOnClickListener {
                customDialog.dismiss()
            }
            btnKeluar.setOnClickListener {
                customDialog.dismiss()
                finish()
            }
            customDialog.show()

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init(){
        cvForm =findViewById(R.id.form)
        cvCalculator =findViewById(R.id.calculator)
        cvMenu = findViewById(R.id.Menu)
        cvKonversiSuhu = findViewById(R.id.konversiSuhu)
        cvProfile = findViewById(R.id.Profile)
        cvExit = findViewById(R.id.exit)
    }
}