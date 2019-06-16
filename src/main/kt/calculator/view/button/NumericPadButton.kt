package calculator.view.button

import calculator.view.SCREEN_HEIGHT
import calculator.view.SCREEN_MARGIN
import calculator.view.SCREEN_SEPARATION_WITH_BUTTONS
import javafx.scene.control.Button

class NumericPadButton : Button() {

    init { setNumericSize() }

    /**
     * Set the position of the button on the numerical grid
     * @param x Horizontal position
     * @param y Vertical position
     */
    fun placeOnNumericPad(x: Int, y: Int) {
        layoutX = x*NUMERIC_BUTTON_WIDTH + (x+1)* NUMERIC_BUTTON_MARGIN
        layoutY = SCREEN_HEIGHT + 2*SCREEN_MARGIN + SCREEN_SEPARATION_WITH_BUTTONS +
                y*(NUMERIC_BUTTON_HEIGHT + NUMERIC_BUTTON_MARGIN)
    }

    /**
     * Set the size of the button as it is defined in constants
     */
    fun setNumericSize(){
        prefWidth = NUMERIC_BUTTON_WIDTH
        prefHeight = NUMERIC_BUTTON_HEIGHT
    }

}