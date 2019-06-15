package calculator.model

import calculator.exception.ReadingListCharException
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse

class ListCharTest{

    private val listChar = ListChar()

    @Before
    fun init(){
        listChar.flush()
    }

    @Test
    fun addTest(){
        //Digits
        for(i in correctDigits) {
            assertFalse(listChar.list.contains(Digit(i)))
            listChar + i
            assert(listChar.list.contains(Digit(i)))
        }
        //Comma
        assertFalse(listChar.list.contains(Comma()))
        listChar + ','
        assert(listChar.list.contains(Comma()))
        //Answer
        assertFalse(listChar.list.contains(Answer()))
        listChar + 'A'
        assert(listChar.list.contains(Answer()))
        //Operations
        for(op in correctOperations) {
            assertFalse(listChar.list.contains(Operation(op)))
            listChar + op
            assert(listChar.list.contains(Operation(op)))
        }
    }

    @Test
    fun flushTest() {
        for(i in 0..9) listChar + i
        listChar.flush()
        assert(listChar.list.isEmpty())
    }

    @Test
    fun nextNumberTest1(){
        listChar + 1
        listChar + 2
        listChar + 3
        listChar + 8
        listChar + 4

        val result = listChar.nextNumber()
        println("result = $result")
        assert(result == 12384.0)
        assert(listChar.list.isEmpty())
    }

    @Test(expected = ReadingListCharException::class)
    fun nextNumberTest2(){
        listChar +'/'
        listChar + 1
        listChar + 2

        listChar.nextNumber()
    }

    @Test
    fun nextNumberAnswerTest(){
        listChar.answer = 4.2
        listChar + 'A'

        val result = listChar.nextNumber()
        println("result = $result")
        assert(result == 4.2)
        assert(listChar.list.isEmpty())
    }

    @Test
    fun nextNumberTest3(){
        listChar + 6
        listChar + 3
        listChar + ','
        listChar + 7
        listChar + 1
        listChar + 2

        val result = listChar.nextNumber()
        println("result = $result")
        assert(result == 63.712)
        assert(listChar.list.isEmpty())
    }

    @Test
    fun nextNumberTest4(){
        listChar + 0
        listChar + ','
        listChar + 1
        listChar + '-'

        val result = listChar.nextNumber()
        println("result = $result")
        assert(result == 0.1)
        assert(listChar.list.contains(Operation('-')))
    }

    @Test
    fun nextNumberTest5(){
        listChar + 9
        listChar + 8
        listChar + 7
        listChar + 6
        listChar + 5
        listChar + 4
        listChar + ','
        listChar + 3
        listChar + 2
        listChar + 1

        val result = listChar.nextNumber()
        println("result = $result")
        assert(result - 987654.321 in -0.001..0.001)
        assert(listChar.list.isEmpty())
    }

    @Test
    fun nextOperationTest1(){
        listChar + '+'
        listChar + 0

        val result = listChar.nextOperation()
        println("result = $result")
        assert(result == Operation('+'))
        assert(listChar.list.contains(Digit(0)))
    }

    @Test
    fun nextOperationTest2(){
        listChar + ':'

        val result = listChar.nextOperation()
        println("result = $result")
        assert(result == Operation(':'))
        assert(listChar.list.isEmpty())
    }

    @Test(expected = ReadingListCharException::class)
    fun nextOperationShouldThrowException(){
        listChar + 4
        listChar + '+'
        listChar + 0

        val result = listChar.nextOperation()
        println("result = $result")
        assert(result == Operation('+'))
    }
}