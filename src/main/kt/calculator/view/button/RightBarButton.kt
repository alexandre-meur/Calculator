package calculator.view.button

import javafx.scene.control.Button

class RightBarButton : Button(){

    init {
        setRightBarSize()
        font = FONT_RIGHT_BAR_BUTTON
    }

    /**
     * Set the size of the button as it is defined in constants
     */
    fun setRightBarSize(){
        prefWidth = RIGHT_BAR_BUTTON_WIDTH
        prefHeight = RIGHT_BAR_BUTTON_HEIGHT
    }

}