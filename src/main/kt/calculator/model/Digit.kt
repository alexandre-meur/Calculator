package calculator.model

import calculator.exception.NotADigitException

/**
 * This class represents the digits wrote on the calculator i.e. between 0-9 and also the comma
 */
class Digit(val digit : Short) : CalculatorChar(){

    constructor(intValue: Int) : this(intValue.toShort())

    /**
     * Checking digit received. An exception is thrown if it's incorrect
     */
    init{ if(digit !in CORRECT_DIGITS) throw NotADigitException()}

    override fun getChar() = (digit + 48).toChar()

    override fun equals(other: Any?): Boolean =
        if(other !is Digit) false
        else digit == other.digit
}