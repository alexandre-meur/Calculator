package calculator.view

import calculator.controller.CalculatorController
import calculator.controller.DEFAULT_SCREEN_TEXT
import calculator.model.*
import calculator.view.button.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints
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
        isResizable = false
        scene = Scene(grid, VIEW_WIDTH, VIEW_HEIGHT, Color.WHITESMOKE)

        //Setting up the grid
        setupGrid()

        //Adding screen to the grid
        addScreen()

        //Adding buttons to the grid
        addNumericButtons()
        addRightBarButtons()

        //Show the view
        show()
    }

    /**
     * Setup the grid
     */
    private fun setupGrid() {
        grid.padding = Insets(GRID_PADDING)
        grid.hgap = GRID_GAP
        grid.vgap = GRID_GAP
        grid.rowConstraints.add(0, RowConstraints(SCREEN_HEIGHT))
    }

    /**
     * Add the screen to the view
     */
    private fun addScreen() {
        //Pane is used for formatting the screen
        val pane = Pane()
        pane.style = SCREEN_STYLE
        grid.add(pane, SCREEN_COLUMN, SCREEN_ROW, SCREEN_COLSPAN, SCREEN_ROWSPAN)

        val screen = controller.screen
        screen.text = DEFAULT_SCREEN_TEXT
        screen.wrappingWidth = SCREEN_WRAPPING_WIDTH
        screen.textOrigin = VPos.CENTER
        screen.textAlignment = TextAlignment.RIGHT
        screen.font = FONT_SCREEN
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
                button.text = (j+1 + i*3).toString()
                button.onAction = EventHandler { controller.sendInput(Digit(j+1 + i*3)) }
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
        buttonCancel.onAction = EventHandler { controller::flushMem.also { controller::flushScreen }}
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
        rightBarIndex++
        buttonEqual.text = EQUAL_TEXT
        buttonEqual.onAction = EventHandler { controller.sendInput(Equal()) }
        buttonEqual.isDefaultButton = true
        grid.add(buttonEqual, RIGHT_BAR_Y, rightBarIndex)
    }
}