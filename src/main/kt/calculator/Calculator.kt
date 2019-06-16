/**
 * Calculator made to practice in Kotlin
 *
 * @author Alexandre Meur
 */

package calculator


import calculator.controller.CalculatorController
import calculator.view.CalculatorView
import javafx.application.Application
import javafx.stage.Stage

class Calculator : Application() {

    override fun start(primaryStage: Stage) {
        val controller = CalculatorController()
        val stage = CalculatorView(controller)
    }
}


fun main(args: Array<String>) {
    Application.launch(Calculator::class.java, *args)
}