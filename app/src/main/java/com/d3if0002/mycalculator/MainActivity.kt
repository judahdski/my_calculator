package com.d3if0002.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayTextView: TextView = findViewById(R.id.mainDisplayText)
        val secondDisplayView: TextView = findViewById(R.id.secondDisplayText)

        setCalculatorSystem(displayTextView, secondDisplayView)
    }

    private fun setCalculatorSystem(mainDisplayTextView: TextView, secondDisplayView: TextView) {
        var numbersText = "0"
        mainDisplayTextView.text = numbersText

//        numbers
        val buttonOne: Button = findViewById(R.id.satu)
        val buttonTwo: Button = findViewById(R.id.dua)
        val buttonThree: Button = findViewById(R.id.tiga)
        val buttonFour: Button = findViewById(R.id.empat)
        val buttonFive: Button = findViewById(R.id.lima)
        val buttonSix: Button = findViewById(R.id.enam)
        val buttonSeven: Button = findViewById(R.id.tujuh)
        val buttonEight: Button = findViewById(R.id.delapan)
        val buttonNine: Button = findViewById(R.id.sembilan)
        val buttonZero: Button = findViewById(R.id.nol)

        fun setNumberPadFunc(number: Int) {
            if (numbersText == "0")  {
                numbersText = number.toString()
            } else {
                numbersText += number.toString()
            }

            mainDisplayTextView.text = numbersText
        }

        buttonOne.setOnClickListener { setNumberPadFunc(1) }
        buttonTwo.setOnClickListener { setNumberPadFunc(2) }
        buttonThree.setOnClickListener { setNumberPadFunc(3) }
        buttonFour.setOnClickListener { setNumberPadFunc(4) }
        buttonFive.setOnClickListener { setNumberPadFunc(5) }
        buttonSix.setOnClickListener { setNumberPadFunc(6) }
        buttonSeven.setOnClickListener { setNumberPadFunc(7) }
        buttonEight.setOnClickListener { setNumberPadFunc(8) }
        buttonNine.setOnClickListener { setNumberPadFunc(9) }
        buttonZero.setOnClickListener { setNumberPadFunc(0) }

//            system
        var firstNumber = 0
        var operators = "."
        var secondNumber = 0

//        operator
        val plusButton: Button = findViewById(R.id.tambah)
        val minusButton: Button = findViewById(R.id.kurang)
        val multiplyButton: Button = findViewById(R.id.kali)
        val dividerButton: Button = findViewById(R.id.bagi)

        fun setOpsFunc(operator: String) {
            if (numbersText != "0")  {
//                masukin firstNumber
                firstNumber = numbersText.toInt()
//                setting untuk secondDisplayView
                numbersText += " ${operator}"
                secondDisplayView.text = numbersText
//                reset numbersText
                numbersText = "0"
//                tentukan operator
                operators = operator
                mainDisplayTextView.text = numbersText
            } else {
                Toast.makeText(this, "Gaboleh Operator, harap masukan angka terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        plusButton.setOnClickListener { setOpsFunc("+") }
        minusButton.setOnClickListener { setOpsFunc("-") }
        multiplyButton.setOnClickListener { setOpsFunc("x") }
        dividerButton.setOnClickListener { setOpsFunc("/") }

//        equals
        fun setResultBtnFunc() {
//            jika user belum memasukan angka sama sekali
            if (firstNumber == 0) Toast.makeText(this, "Harap masukan terlebih dulu angka dan operasinya. Trims :)", Toast.LENGTH_SHORT).show()

//                masukin value secondNumber
            secondNumber = numbersText.toInt()
//            update tampilan di secondDisplayText
            val secondDisplayViewText = "${firstNumber.toString()} ${operators} ${secondNumber.toString()}"
            secondDisplayView.text = secondDisplayViewText
//              reset numbersText
            numbersText = "0"

            val result = if (operators != ".") {
                when(operators) {
                    "+" -> sum(firstNumber, secondNumber)
                    "-" -> minus(firstNumber, secondNumber)
                    "x" -> multiply(firstNumber, secondNumber)
                    "/" -> divided(firstNumber, secondNumber)
                    else -> 0
                }
            } else {
                Toast.makeText(this, "Harap masukan terlebih dulu operasinya. Trims :)", Toast.LENGTH_SHORT).show()

                numbersText.toInt()
            }

            mainDisplayTextView.text = result.toString()
            numbersText = result.toString()
        }

        val resultButton: Button = findViewById(R.id.equals)
        resultButton.setOnClickListener { setResultBtnFunc() }

//        clear
        val clearButton: Button = findViewById(R.id.clear)
        clearButton.setOnClickListener {
            if (numbersText == "0")  {
                Toast.makeText(this, "Sudah NOL! Jangan di CLEAR lagi.", Toast.LENGTH_SHORT).show()
            }

            numbersText = "0"
            mainDisplayTextView.text = "0"
            secondDisplayView.text = " "
        }
    }

    private fun sum(f: Int, s: Int) = f+s

    private fun minus(f: Int, s: Int) = f-s

    private fun multiply(f: Int, s: Int) = f*s

    private fun divided(f: Int, s: Int): Int {
        Toast.makeText(this, "Can't divided by 0.", Toast.LENGTH_SHORT).show()
        return if(s != 0) f/s else 0
    }

}
