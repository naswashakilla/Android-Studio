package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityKonversiSuhuBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class KonversiSuhu : AppCompatActivity() {

    private lateinit var binding: ActivityKonversiSuhuBinding

    private val temperatures = arrayOf("°C", "°R", "°F", "K")
    private var selectedIndex1: Int = 0
    private var selectedIndex2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKonversiSuhuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

        setupSpinners()

        binding.layoutRumus.visibility = View.GONE

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUI() {
        binding.editSp2.keyListener = null
        binding.editSp2.isFocusable = false
        binding.editSp2.isClickable = false

        binding.editSp1.hint = temperatures[selectedIndex1]
        binding.editSp2.hint = temperatures[selectedIndex2]
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatures)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner1.adapter = adapter
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedIndex1 = position
                binding.editSp1.hint = temperatures[position]
                if (binding.editSp1.text?.isNotEmpty() == true) performConversion()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinner2.adapter = adapter
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedIndex2 = position
                binding.editSp2.hint = temperatures[position]
                if (binding.editSp1.text?.isNotEmpty() == true) performConversion()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun performConversion() {
        val inputStr = binding.editSp1.text.toString()

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Masukkan nilai suhu terlebih dahulu", Toast.LENGTH_SHORT).show()
            binding.layoutRumus.visibility = View.GONE
            return
        }

        val inputValue = inputStr.toDoubleOrNull()
        if (inputValue == null) {
            Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
            binding.layoutRumus.visibility = View.GONE
            return
        }

        val (result, formula) = convertTemperature(inputValue, selectedIndex1, selectedIndex2)

        if (result.isNaN()) {
            binding.editSp2.setText("ERROR")
            binding.layoutRumus.visibility = View.GONE
            return
        }

        val formattedResult = String.format(Locale.US, "%.2f", result)
        binding.editSp2.setText(formattedResult)

        binding.textFormula.text = formula
        binding.layoutRumus.visibility = View.VISIBLE
    }

    private fun convertTemperature(value: Double, fromIndex: Int, toIndex: Int): Pair<Double, String> {
        val from = temperatures[fromIndex]
        val to = temperatures[toIndex]

        val valueInC: Double = when (fromIndex) {
            0 -> value
            1 -> value * 1.25
            2 -> (value - 32.0) * (5.0 / 9.0)
            3 -> value - 273.15
            else -> return Pair(Double.NaN, "Unit tidak valid.")
        }

        val finalValue: Double = when (toIndex) {
            0 -> valueInC
            1 -> valueInC * 0.8
            2 -> (valueInC * (9.0 / 5.0)) + 32.0
            3 -> valueInC + 273.15
            else -> Double.NaN
        }

        val formula = when {
            from == to -> "T($from) = $value"
            from == "°C" && to == "°R" -> "T(°R) = T(°C) × 4/5"
            from == "°C" && to == "°F" -> "T(°F) = (T(°C) × 9/5) + 32"
            from == "°C" && to == "K" -> "T(K) = T(°C) + 273.15"
            from == "°F" && to == "°C" -> "T(°C) = (T(°F) - 32) × 5/9"
            else -> "Konversi dari $from ke $to"
        }

        return Pair(finalValue, formula)
    }
}