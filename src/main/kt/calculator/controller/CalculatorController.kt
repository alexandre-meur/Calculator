package calculator.controller

import calculator.model.CalculatorChar
import calculator.model.ListChar

class CalculatorController(){

    val listChar = ListChar()
    var displayContent = "Welcome"

    /**
     * Called when a button is pressed on the view
     */
    fun sendInput(buttonPress : CalculatorChar) : Unit {
        println("buttonPress = ${buttonPress}")
    }

    /**
     * Use to send a new text to display on the calculator
     */
    fun getDisplay(messageDisplay : String) : String {
        TODO()
    }

    /**
     * Empty all memorized inputs
     */
    fun flush() = listChar.flush()


}