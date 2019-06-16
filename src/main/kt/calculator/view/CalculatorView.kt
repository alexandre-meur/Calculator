package calculator.view

import calculator.controller.CalculatorController
import calculator.controller.DEFAULT_SCREEN_TEXT
import calculator.model.*
import calculator.view.button.*
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import javafx.stage.Stage

/**
 * View of the calculator. Contains a screen to type and display results,
 * buttons to type digits and comma and a side-bar on the right side to call operations and
 * other functionnalities
 */
class CalculatorView : Stage(){

    private val controller = CalculatorController()
    private val root = Group()

    //Initialize the view
    init{
        title = VIEW_TITLE
        scene = Scene(root, VIEW_WIDTH, VIEW_HEIGHT, Color.WHITESMOKE)

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
        screen.relocate(SCREEN_MARGIN, SCREEN_MARGIN)
        screen.font = FONT_SCREEN
        screen.textAlignment = TextAlignment.LEFT
        root.children.add(screen)
    }

    /**
     * Add buttons form 0 to 9 and the comma to the view
     */
    private fun addNumericButtons(){
        //Button 1 to 9
        for(i in 0..2){
            for(j in 0..2) {
                val button = NumericPadButton()
                button.placeOnNumericPad(i, j)
                button.text = (i+1 + j*3).toString()
                button.onAction = EventHandler { controller.sendInput(Digit(i+1 + j*3)) }
                root.children.add(button)
            }
        }
        //Creating and adding button 0 and comma
        val buttonZero = NumericPadButton()
        val buttonComma = NumericPadButton()

        buttonZero.placeOnNumericPad(ZERO_X_PAD, ZERO_Y_PAD)
        buttonComma.placeOnNumericPad(COMMA_X_PAD, COMMA_Y_PAD)

        buttonZero.text = ZERO_TEXT
        buttonComma.text = COMMA_TEXT

        buttonZero.onAction = EventHandler { controller.sendInput(Digit(0)) }
        buttonComma.onAction = EventHandler { controller.sendInput(Comma()) }

        root.children.add(buttonZero)
        root.children.add(buttonComma)
    }

    /**
     * Add buttons to the right bar
     */
    private fun addRightBarButtons() {
        var rightBarIndex = 0

        //Adding the Cancel button
        val buttonCancel = RightBarButton()
        buttonCancel.placeOnRightBar(rightBarIndex)
        rightBarIndex++
        buttonCancel.text = CANCEL_TEXT
        buttonCancel.onAction = EventHandler { controller.flush() }
        root.children.add(buttonCancel)

        //Adding operations buttons
        for(operationChar in CORRECT_OPERATIONS){
            val buttonOperation = RightBarButton()
            buttonOperation.placeOnRightBar(rightBarIndex)
            rightBarIndex++
            buttonOperation.text = operationChar.toString()
            buttonOperation.onAction = EventHandler { controller.sendInput(Operation(operationChar)) }
            root.children.add(buttonOperation)
        }

        //Adding answer button
        val buttonAnswer = RightBarButton()
        buttonAnswer.placeOnRightBar(rightBarIndex)
        rightBarIndex++
        buttonAnswer.text = ANSWER_TEXT
        buttonAnswer.onAction = EventHandler { controller.sendInput(Answer()) }
        root.children.add(buttonAnswer)

        //Adding equals button
        val buttonEqual = RightBarButton()
        buttonEqual.placeOnRightBar(rightBarIndex)
        buttonEqual.text = EQUAL_TEXT
        buttonEqual.onAction = EventHandler { controller.sendInput(Equal()) }
        buttonEqual.isDefaultButton = true
        root.children.add(buttonEqual)
    }
}