package calculator.model

import calculator.exception.NotAnOperationException
import calculator.exception.ReadingListCharException

/**
 * Class representing everything that has been typed by the user before an '='
 * Also stores the value of Ans if available
 */
class ListChar() {
    val list : MutableList<CalculatorChar> = mutableListOf()
    var answer : Double? = null

    /**
     * Add an element to the list
     */
    fun add(calculatorChar: CalculatorChar) = list.add(calculatorChar)

    /**
     * Operator + is used to add items quicker to the list. It throws exceptions if incorrectly used.
     */
    operator fun plus(i : Int) = add(Digit(i))

    operator fun plus(c : Char){
        when {
            c in correctOperations -> add(Operation(c))
            c == ',' -> add(Comma())
            c == 'A' -> add(Answer())
            else -> throw Exception("Wrong usage of operator plus on ListChar")
        }
    }

    /**
     * Removes all elements in the list
     */
    fun flush() = list.removeAll { true }

    /**
     * Returns the first number and removes it from the list. Throws an exception if
     * there's no number at the start. Answer is considered as a number.
     */
    fun nextNumber() : Double {
        var puissanceDeDix : Int
        var result = 0.0
        var currentDigit: CalculatorChar

        if(list.isEmpty() ||  (list.first() !is Digit && list.first() !is Answer)) {
            throw ReadingListCharException("Can't read at the start of ListChar")
        }


        //If Answer is there, we return it or throw an exception if it's undefined
        if(list.first() is Answer) {
            list.removeAt(0)
            return answer ?: throw Exception("What's wrong here")
        }

        //Read before comma
        while (list.isNotEmpty() && list.first() is Digit) {
            currentDigit = list.first() as Digit
            result *= 10
            result += currentDigit.digit
            list.removeAt(0)
        }

        //Read after comma if there is one
        if (list.isNotEmpty() && list.first() is Comma){
            //Comma is removed
            list.removeAt(0)
            puissanceDeDix = 1
            while (list.isNotEmpty() && list.first() is Digit) {
                currentDigit = list.first() as Digit
                result += currentDigit.digit.toDouble() / (Math.pow(10.0, puissanceDeDix.toDouble()))
                list.removeAt(0)
                puissanceDeDix++
            }
        }
        return result
    }

    /**
     * Return the operation at the start of the lsit and removes it from the list.
     * Throws an exception if there's no operation at the start
     */
    fun nextOperation() : Operation{
        if(list.isNotEmpty()){
            val op = list.first()
            list.removeAt(0)
            if(op is Operation) return op
        }
        throw ReadingListCharException("Was expecting an operation")
    }


    /**
     * Process what's in the list. Return the result as a String and store the calcul in answer.
     * Throws an exception if something goes wrong
     */
    fun calcul() : String {
        val number1 = nextNumber()
        val op = nextOperation()
        val number2 = nextNumber()
        var result : Double
        var display : String = ""

        when(op.getChar()) {
            '+' -> result = number1 + number2
            '-' -> result = number1 - number2
            '*' -> result = number1 * number2
            '/' -> result = number1 / number2
            ':' -> result = Math.floor(number1 / number2)
            else -> throw NotAnOperationException()
        }

        //We manage Euclidean division
        if(op.getChar() == ':') {
            result = Math.floor(result)
            display = result.toString() + " r: "+number1%number2
        }else {
            display = result.toString()
        }

        answer = result
        return display
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