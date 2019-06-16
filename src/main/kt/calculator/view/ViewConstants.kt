package calculator.view

import calculator.view.button.NUMERIC_BUTTON_HEIGHT
import calculator.view.button.NUMERIC_BUTTON_WIDTH
import calculator.view.button.RIGHT_BAR_BUTTON_WIDTH
import javafx.scene.text.Font

/**
 * Constants for the view
 */

//Grid
const val GRID_PADDING = 20.0
const val GRID_GAP = 5.0

//Screen
val FONT_SCREEN = Font("Verdana Bold", 22.0)
const val SCREEN_WRAPPING_WIDTH = 225.0
const val SCREEN_HEIGHT = 60.0
const val SCREEN_COLUMN = 0
const val SCREEN_ROW = 0
const val SCREEN_COLSPAN = 4
const val SCREEN_ROWSPAN = 1
const val SCREEN_STYLE = "-fx-border-width: 2;" +
        "-fx-border-color: gray;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-radius: 5;" +
        "-fx-background-color: lightgray;"

//View
const val VIEW_TITLE = "Calculator"
const val VIEW_WIDTH = 3*NUMERIC_BUTTON_WIDTH + 3*GRID_GAP +
        RIGHT_BAR_BUTTON_WIDTH + 2*GRID_PADDING
const val VIEW_HEIGHT = 4* NUMERIC_BUTTON_HEIGHT + 3*GRID_GAP +
        SCREEN_HEIGHT + 2*GRID_PADDING

//Buttons text
const val CANCEL_TEXT = "C"
const val ANSWER_TEXT = "Ans"
const val EQUAL_TEXT = "="
