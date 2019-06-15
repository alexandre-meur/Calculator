package calculator.model

/**
 * Class representing everything that has been typed by the user before an '='
 * Also stores the value of Ans if available
 */
class ListChar() {
    val list : MutableList<CalculatorChar> = mutableListOf()
    var answer : Double? = null
        get() = answer ?: throw Exception()
        set(value){field = value}

    /**
     * Add an element to the list
     */
    fun add(calculatorChar: CalculatorChar) = list.add(calculatorChar)

    /**
     * Removes all elements in the list
     */
    fun flush() = list.removeAll { true }

    /**
     * Returns the first number and removes it from this
     */
    fun firstNumber() : Double {
        var puissanceDeDix: Int = 0
        var result: Double = 0.0
        var currentDigit: CalculatorChar = list.first()

        //Read before comma
        while (currentDigit is Digit) {
            result += currentDigit.digit + (10 * puissanceDeDix)
            list.removeAt(0)
            puissanceDeDix++
            currentDigit = list.first()
        }

        //Read after comma if there is one
        if (currentDigit is Comma){
            //Comma is removed
            list.removeAt(0)
            currentDigit = list.first()
            puissanceDeDix = 1
            while (currentDigit is Digit) {
                result += currentDigit.digit / (10 * puissanceDeDix)
                list.removeAt(0)
                puissanceDeDix++
                currentDigit = list.first()
            }
        }

        return result
    }

    /**
     * Process what's in the list. Gives the result of the calcul or throw an exception
     */
    fun calcul() : Double {
        TODO()
    }

    /**
     * toString()
     */
    override fun toString() : String {
        val builder = StringBuilder()
        list.map { it.getChar() }
            .forEach { builder.append(it) }
        return builder.toString()
    }
}