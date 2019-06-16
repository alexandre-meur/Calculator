package calculator.model

class Answer : CalculatorChar() {
    override fun getChar() = 'A'

    override fun equals(other: Any?) = other is Answer
}