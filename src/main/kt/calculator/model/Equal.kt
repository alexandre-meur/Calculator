package calculator.model

class Equal : CalculatorChar() {
    override fun getChar() = '='

    override fun equals(other: Any?) = other is Equal
}