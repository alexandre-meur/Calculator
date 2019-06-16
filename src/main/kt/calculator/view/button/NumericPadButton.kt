package calculator.view.button

import javafx.scene.control.Button

class NumericPadButton : Button() {

    init {
        setNumericSize()
        font = FONT_NUMERIC_BUTTON
    }

    /**
     * Set the size of the button as it is defined in constants
     */
    fun setNumericSize(){
        prefWidth = NUMERIC_BUTTON_WIDTH
        prefHeight = NUMERIC_BUTTON_HEIGHT
    }

}