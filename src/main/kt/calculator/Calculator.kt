/**
 * Calculator made to practice in Kotlin
 *
 * @author Alexandre Meur
 */

package calculator


import calculator.view.CalculatorView
import javafx.application.Application
import javafx.stage.Stage

class Calculator : Application() {

    override fun start(primaryStage: Stage) {
        CalculatorView()
    }
}


fun main(args: Array<String>) {
    Application.launch(Calculator::class.java, *args)
}