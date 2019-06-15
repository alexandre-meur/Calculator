package calculator.model

import calculator.exception.NotAnOperationException

/**
 * This class represents operations entered in the calculator
 */
class Operation(private val operation : Char) : CalculatorChar(){

    /**
     * Checking if the operation is correct. Throws an exception otherwise
     */
    init{ if(operation !in correctOperations) throw NotAnOperationException()}

    override fun getChar() = operation
}
