package com.example.calculator20

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.math.sqrt
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var screen: TextView
    private lateinit var plusButton: TextView
    private lateinit var cancelButton: TextView
    private lateinit var minusButton: TextView
    private lateinit var multipleButton: TextView
    private lateinit var divideButton: TextView
    private lateinit var sqrtButton: TextView
    private lateinit var powButton: TextView
    var triger: Boolean = false
    var isResult: Boolean = false
    var currentScreenDigit: Double = 0.0
    var screenIsEmpty: Boolean = true
    var DividedByZero: Boolean = false
    var operation:Any = 0
    var isSign: Boolean = false
    var pointPressed: Boolean = false
    var firstNum:Double = 0.0
    var pointPermission:Boolean = true
    var signPermission:Boolean = true
    var temp:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screen = findViewById(R.id.result)
        plusButton = findViewById(R.id.btn_plus)
        minusButton = findViewById(R.id.btn_minus)
        cancelButton = findViewById(R.id.btn_cancel)
        multipleButton = findViewById(R.id.btn_multiply)
        divideButton = findViewById(R.id.btn_divide)
        powButton = findViewById(R.id.pow)
        sqrtButton = findViewById(R.id.sqrt)
    }

    fun GetResult(firstnum: Double, secondnum: Double, operation: Any): Any {
        isResult = true
        var result = 0.0


        when (operation) {
            "11" -> result = String.format("%.3f", firstnum / secondnum).toDouble()
            "12" -> result = String.format("%.3f", firstnum * secondnum).toDouble()
            "13" -> result = String.format("%.3f", firstnum - secondnum).toDouble()
            "14" -> result = Math.round((firstnum + secondnum) * 1000.0) / 1000.0
            "22" -> result = String.format("%.3f", Math.pow(firstnum, secondnum)).toDouble()

            else -> result = 6.0
        }
        var splitRtersult = result.toString().split(".")
        if (splitRtersult[1] == "0"){
            return splitRtersult[0]
        }
        return result
    }

    @SuppressLint("SetTextI18n")
    fun convertSign(view: View) {
        if (!DividedByZero) {
        if (!screenIsEmpty) {
                currentScreenDigit = -currentScreenDigit
                screen.text = currentScreenDigit.toString()
        }
    }
    }

    fun backspace(view: View) {
        if (!DividedByZero) {
            if (operation == "11" && currentScreenDigit == 0.0) {
                screen.text = "error, pass C"
                cancelButton.rotationY = 58F
                DividedByZero = true
            } else {
            if (screen.text.isNotEmpty()) {
                screen.text = screen.text.toString().substring(0, screen.text.length - 1)
                if (!screen.text.isNotEmpty()) {
                    screenIsEmpty = true
                    currentScreenDigit = 0.0
                    operation = 0
                    plusButton.rotationY = 0F
                    minusButton.rotationY = 0F
                    multipleButton.rotationY = 0F
                    divideButton.rotationY = 0F
                } else if (screen.text.isNotEmpty()) {
                    currentScreenDigit = screen.text.toString().toDouble()
                }
            }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun Numbers(view: View) {
        if (!DividedByZero) {
            if (screen.text.count()<13){
                signPermission = true
                pointPermission = true
                if (triger == true) {
                    screen.text = view.tag.toString()
                    // screen.text = view.tag.toString()
                    triger = false
                } else {
                    if (screen.text.toString() == "0") {
                        screen.text = view.tag.toString()
                        // screen.text = view.tag.toString()
                    } else {
                        screen.text = screen.text.toString() + view.tag.toString()
                    }
                }
                isResult = false
                currentScreenDigit = screen.text.toString().toDouble()
                screenIsEmpty = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun Buttons(view: View) {
        if (!DividedByZero) {
        if (screen.text != "" && view.tag.toString() != "10" && view.tag.toString() != "15" && view.tag.toString() != "16") {
            if (operation == "11" && currentScreenDigit == 0.0) {
                screen.text = "error, pass C"
                cancelButton.rotationY = 58F
                DividedByZero = true
            } else {
                plusButton.rotationY = 0F
                minusButton.rotationY = 0F
                multipleButton.rotationY = 0F
                divideButton.rotationY = 0F
                powButton.rotationX = 0F

                if (view.tag.toString() == "11") {
                    divideButton.rotationY = -58F
                    if (isSign && !isResult) { // |
                        screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
                    } else {
                        isSign = true
                    }
                    triger = true
                } else if (view.tag.toString() == "12") { // *
                   multipleButton.rotationY = -58F
                    if (isSign && !isResult) {
                        screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
                    } else {
                        isSign = true
                    }
                    triger = true
                } else if (view.tag.toString() == "13") { // -
                    minusButton.rotationY = -58F
                    if (isSign && !isResult) {
                        screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
                    } else {
                        isSign = true
                    }
                    triger = true
                } else if (view.tag.toString() == "14") { // +
                    plusButton.rotationY = -58F
                    if (isSign && !isResult) {
                        screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
                    } else {
                        isSign = true
                    }
                    triger = true
                }
                else if (view.tag.toString() == "22") { // stepen
                    powButton.rotationX = -58F
                    if (isSign && !isResult) {
                        screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
                    } else {
                        isSign = true
                    }
                    triger = true
                }
                pointPressed = false
                pointPermission = false
                firstNum = screen.text.toString().toDouble() //zapretit
                currentScreenDigit = screen.text.toString().toDouble()
                operation = view.tag.toString()
            } //konec else
        } // konec 10 15
            if (view.tag.toString() == "16"){
    if (!screenIsEmpty && !screen.text.contains(".")){
        screen.text = screen.text.toString() + "."
        pointPressed = true
    } else if (screenIsEmpty) {
        screen.text = "0."
        pointPressed = true
    }
}
            if (view.tag.toString() == "15") {
                powButton.rotationX = 0F
                plusButton.rotationY = 0F
                minusButton.rotationY = 0F
                multipleButton.rotationY = 0F
                divideButton.rotationY = 0F
    if (operation.toString() == "11" && currentScreenDigit == 0.0){
        screen.text = "error, pass C"
        cancelButton.rotationY = 58F
        DividedByZero = true
    } else if (!isResult && !screenIsEmpty && operation != 0){

            screen.text = (GetResult(firstNum, currentScreenDigit, operation)).toString()
            pointPressed = true
            firstNum = (screen.text).toString().toDouble()
            isSign = false
            currentScreenDigit = screen.text.toString().toDouble()
            signPermission = false
    }
}
}
        if (view.tag.toString() == "10"){
            plusButton.rotationY = 0F
            minusButton.rotationY = 0F
            multipleButton.rotationY = 0F
            divideButton.rotationY = 0F
            cancelButton.rotationY = 0F
            powButton.rotationX = 0F
            screen.text = ""
            operation = 0
            firstNum = 0.0
            currentScreenDigit = 0.0
            triger = false
            isSign = false
            DividedByZero = false
            isResult = false
            pointPressed = false
            screenIsEmpty = true
            signPermission = false
            temp = ""
        }
    }

    fun secretFunction(view: View) {
        if (sqrtButton.visibility == View.GONE && powButton.visibility == View.GONE){
            sqrtButton.visibility = View.VISIBLE
            powButton.visibility = View.VISIBLE
        } else{
            sqrtButton.visibility = View.GONE
            powButton.visibility = View.GONE
        }
    }

    fun Sqrt(view: View) {
        if (!DividedByZero) {
            if (operation == "11" && currentScreenDigit == 0.0) {
                screen.text = "error, pass C"
                cancelButton.rotationY = 58F
                DividedByZero = true
            } else {
                if (!screen.text.contains("-", true) && !screenIsEmpty){
                    temp = "%.3f".format(sqrt(screen.text.toString().toDouble()))
                    if(temp.contains( ".000",true)){
                        screen.text = temp.split(".")[0]
                    } else {
                        screen.text = temp
                    }
                }
           }
       }
    }
}







