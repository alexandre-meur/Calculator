package calculator.model

/**
 * This is the general type of all things that can be typed in the calculator
 */
abstract open class CalculatorChar{
    abstract fun getChar() : Char

    override fun toString() = String() + getChar()
    abstract override fun equals(other: Any?): Boolean
}