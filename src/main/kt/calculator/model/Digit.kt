package calculator.model

import calculator.exception.NotADigitException

/**
 * This class represents the digits wrote on the calculator i.e. between 0-9 and also the comma
 */
class Digit(val digit : Short) : CalculatorChar(){

    /**
     * Checking digit received. An exception is thrown if it's incorrect
     */
    init{ if(digit !in correctDigits) throw NotADigitException()}

    override fun getChar() = (digit + 48).toChar()
}