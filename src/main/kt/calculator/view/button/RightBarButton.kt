package calculator.view.button

import calculator.view.SCREEN_HEIGHT
import calculator.view.SCREEN_MARGIN
import calculator.view.SCREEN_SEPARATION_WITH_BUTTONS
import javafx.scene.control.Button

class RightBarButton : Button(){

    init { setRightBarSize() }

    /**
     * Set the position of the button on the right bar
     * @param y Vertical position
     */
    fun placeOnRightBar( y: Int) {
        layoutX = 3*NUMERIC_BUTTON_WIDTH + 4*NUMERIC_BUTTON_MARGIN + SEPARATION_NUMERIC_PAD_RIGHT_BAR
        layoutY = SCREEN_HEIGHT + 2* SCREEN_MARGIN + SCREEN_SEPARATION_WITH_BUTTONS +
                y*(RIGHT_BAR_BUTTON_HEIGHT + RIGHT_BAR_BUTTON_MARGIN)
    }

    /**
     * Set the size of the button as it is defined in constants
     */
    fun setRightBarSize(){
        prefWidth = RIGHT_BAR_BUTTON_WIDTH
        prefHeight = RIGHT_BAR_BUTTON_HEIGHT
    }

}