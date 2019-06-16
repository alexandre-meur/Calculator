package calculator.view

import calculator.controller.CalculatorController
import calculator.controller.DEFAULT_SCREEN_TEXT
import calculator.model.*
import calculator.view.button.*
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import javafx.stage.Stage

/**
 * View of the calculator. Contains a screen to type and display results,
 * buttons to type digits and comma and a side-bar on the right side to call operations and
 * other functionnalities
 */
class CalculatorView : Stage(){

    private val controller = CalculatorController()
    private val grid = GridPane()

    //Initialize the view
    init{
        title = VIEW_TITLE
        scene = Scene(grid, VIEW_WIDTH, VIEW_HEIGHT, Color.WHITESMOKE)

        //Adding screen
        addScreen()

        //Adding buttons
        addNumericButtons()
        addRightBarButtons()

        //Show the view
        show()
    }

    /**
     * Add the screen to the view
     */
    private fun addScreen() {
        val screen = controller.screen
        screen.text = DEFAULT_SCREEN_TEXT
        screen.prefWidth(SCREEN_WIDTH)
        screen.prefHeight(SCREEN_HEIGHT)
        screen.font = FONT_SCREEN
        screen.textAlignment = TextAlignment.RIGHT
        grid.add(screen, SCREEN_COLUMN, SCREEN_ROW, SCREEN_COLSPAN, SCREEN_ROWSPAN)
    }

    /**
     * Add buttons form 0 to 9 and the comma to the view
     */
    private fun addNumericButtons(){
        //Button 1 to 9
        for(i in 0..2){
            for(j in 0..2) {
                val button = NumericPadButton()
                button.text = (i+1 + j*3).toString()
                button.onAction = EventHandler { controller.sendInput(Digit(i+1 + j*3)) }
                grid.add(button, j, 2*i+1, NUMERIC_BUTTON_COLSPAN, NUMERIC_BUTTON_ROWSPAN)
            }
        }
        //Creating and adding button 0 and comma
        val buttonZero = NumericPadButton()
        val buttonComma = NumericPadButton()
        buttonZero.text = ZERO_TEXT
        buttonComma.text = COMMA_TEXT
        buttonZero.onAction = EventHandler { controller.sendInput(Digit(0)) }
        buttonComma.onAction = EventHandler { controller.sendInput(Comma()) }
        buttonZero.prefWidth = ZERO_WIDTH
        grid.add(buttonZero, ZERO_Y , ZERO_X, ZERO_COLSPAN, NUMERIC_BUTTON_ROWSPAN)
        grid.add(buttonComma, COMMA_Y, COMMA_X, NUMERIC_BUTTON_COLSPAN, NUMERIC_BUTTON_ROWSPAN)
    }

    /**
     * Add buttons to the right bar
     */
    private fun addRightBarButtons() {
        var rightBarIndex = 0

        //Adding the Cancel button
        val buttonCancel = RightBarButton()
        rightBarIndex++
        buttonCancel.text = CANCEL_TEXT
        buttonCancel.onAction = EventHandler { controller.flush() }
        grid.add(buttonCancel, RIGHT_BAR_Y, rightBarIndex)

        //Adding operations buttons
        for(operationChar in CORRECT_OPERATIONS){
            val buttonOperation = RightBarButton()
            rightBarIndex++
            buttonOperation.text = operationChar.toString()
            buttonOperation.onAction = EventHandler { controller.sendInput(Operation(operationChar)) }
            grid.add(buttonOperation, RIGHT_BAR_Y, rightBarIndex)
        }

        //Adding answer button
        val buttonAnswer = RightBarButton()
        rightBarIndex++
        buttonAnswer.text = ANSWER_TEXT
        buttonAnswer.onAction = EventHandler { controller.sendInput(Answer()) }
        grid.add(buttonAnswer, RIGHT_BAR_Y, rightBarIndex)

        //Adding equals button
        val buttonEqual = RightBarButton()
        buttonEqual.text = EQUAL_TEXT
        buttonEqual.onAction = EventHandler { controller.sendInput(Equal()) }
        buttonEqual.isDefaultButton = true
        grid.add(buttonEqual, RIGHT_BAR_Y, rightBarIndex)
    }
}