package calculator.model

import calculator.exception.NotADigitException
import calculator.exception.NotAnOperationException
import org.junit.Test

class CalculatorCharTest{

    @Test
    fun getCharDigitTest(){
        val listDigit : MutableList<Digit> = mutableListOf()
        for(i in 1..9) listDigit.add(Digit(i.toShort()))
        for(char in listDigit) assert(char.digit in CORRECT_DIGITS)
    }

    @Test
    fun getCharOperationTest(){
        val listOperation : MutableList<Operation> = mutableListOf()
        for(c in CORRECT_OPERATIONS) listOperation.add(Operation(c))
        for(op in listOperation) assert(op.getChar() in CORRECT_OPERATIONS)
    }

    @Test
    fun getCharCommaTest() = assert(Comma().getChar() == ',')

    @Test(expected = NotADigitException::class)
    fun wrongDigitCreation(){
        Digit(15)
    }

    @Test(expected = NotAnOperationException::class)
    fun wrongOperationCreation(){
        Operation('2')
    }

    @Test
    fun toStringTest(){
        val digit1 = Digit(3)
        val comma = Comma()
        val digit2 = Digit(9)
        val operation = Operation('+')
        val digit3 = Digit(4)
        assert(digit1.toString()+comma+digit2+operation+digit3 == "3,9+4")
    }
}