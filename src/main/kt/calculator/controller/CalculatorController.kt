package calculator.controller

import calculator.model.CalculatorChar
import calculator.model.Equal
import calculator.model.ListChar
import javafx.scene.text.Text

class CalculatorController(){

    private val listChar = ListChar()
    val screen = Text(DEFAULT_SCREEN_TEXT)


    /**
     * Called when a button is pressed on the view
     */
    fun sendInput(buttonPress : CalculatorChar) : Unit {
        if(buttonPress is Equal){
            try {
                screen.text = listChar.calcul()
            }catch(e : Exception){
                screen.text = ERROR_MSG
            }finally{
                flushMem()
            }
        }else{
            listChar.add(buttonPress)
            screen.text = listChar.toString()
        }
    }

    /**
     * Empty all memorized inputs
     */
    fun flushMem() {
        listChar.flush()
    }

    fun flushScreen() {
        screen.text = ""
    }


}