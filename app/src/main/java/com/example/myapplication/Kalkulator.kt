package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Kalkulator : AppCompatActivity() {

    private lateinit var tvExpression: TextView
    private lateinit var tvDisplay: TextView

    private var expression = ""
    private var currentInput = ""
    private var operator: String? = null
    private var firstNumber: Double? = null
    private var secondNumber: Double? = null
    private var hasResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        tvExpression = findViewById(R.id.tvExpression)
        tvDisplay = findViewById(R.id.tvDisplay)

        setupNumberButtons()
        setupOperatorButtons()
        setupFunctions()
    }

    private fun setupNumberButtons() {
        val btnIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in btnIds) {
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()

                if (hasResult) {
                    expression = ""
                    currentInput = ""
                    hasResult = false
                }

                currentInput += value
                expression += value
                updateExpression()
                tvDisplay.text = currentInput
            }
        }

        findViewById<Button>(R.id.btnTitik).setOnClickListener {
            if (!currentInput.contains(".")) {
                if (currentInput.isEmpty()) currentInput = "0"
                currentInput += "."
                expression += "."
                updateExpression()
                tvDisplay.text = currentInput
            }
        }
    }

    private fun setupOperatorButtons() {
        val operators = mapOf(
            R.id.btnTambah to "+",
            R.id.btnMinus to "-",
            R.id.btnKali to "×",
            R.id.btnBagi to "÷"
        )

        for ((id, symbol) in operators) {
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    if (firstNumber == null) {
                        firstNumber = currentInput.toDouble()
                    } else if (operator != null) {
                        secondNumber = currentInput.toDouble()
                        firstNumber = calculate(firstNumber!!, secondNumber!!, operator!!)
                        tvDisplay.text = formatNumber(firstNumber!!)
                    }

                    operator = symbol
                    expression += " $symbol "
                    updateExpression()

                    currentInput = ""
                }
            }
        }
    }

    private fun setupFunctions() {

        findViewById<Button>(R.id.btnSamaDengan).setOnClickListener {
            if (firstNumber != null && operator != null && currentInput.isNotEmpty()) {
                secondNumber = currentInput.toDouble()
                val result = calculate(firstNumber!!, secondNumber!!, operator!!)

                expression += " ="
                updateExpression()

                tvDisplay.text = formatNumber(result)

                firstNumber = result
                currentInput = ""
                operator = null
                hasResult = true
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            currentInput = ""
            firstNumber = null
            secondNumber = null
            operator = null
            tvExpression.text = ""
            tvDisplay.text = "0"
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {

            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                tvExpression.text = expression
            }

            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                tvDisplay.text = if (currentInput.isEmpty()) "0" else currentInput
            }
        }

        findViewById<Button>(R.id.btnAtau).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                if (currentInput.startsWith("-")) {
                    currentInput = currentInput.substring(1)
                } else {
                    currentInput = "-$currentInput"
                }
                tvDisplay.text = currentInput
            }
        }
    }

    private fun calculate(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "×" -> a * b
            "÷" -> if (b != 0.0) a / b else 0.0
            else -> 0.0
        }
    }

    private fun updateExpression() {
        tvExpression.text = expression
    }

    private fun formatNumber(num: Double): String {
        return if (num % 1 == 0.0) num.toInt().toString() else num.toString()
    }
}
