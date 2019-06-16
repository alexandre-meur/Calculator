package calculator.model

class Comma() : CalculatorChar(){
    override fun getChar() = ','

    override fun equals(other: Any?): Boolean = other is Comma
}