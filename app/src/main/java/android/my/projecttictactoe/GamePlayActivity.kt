package android.my.projecttictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_game_play.*
import kotlin.math.sin
import kotlin.system.exitProcess


var playerTurn = true

class GamePlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)
        button10.setOnClickListener {
            reset()
        }
    }
    var player1Count = 0
    var player2Count = 0

    fun clickfun(view:View)
    {
        if(playerTurn) {
            val but = view as Button
            var cellID = 0
            //Toast.makeText(this,but.id.toString() , Toast.LENGTH_SHORT).show();
            when (but.id) {
                R.id.button -> cellID = 1
                R.id.button2 -> cellID = 2
                R.id.button3 -> cellID = 3
                R.id.button4 -> cellID = 4
                R.id.button5 -> cellID = 5
                R.id.button6 -> cellID = 6
                R.id.button7 -> cellID = 7
                R.id.button8 -> cellID = 8
                R.id.button9 -> cellID = 9

            }
            playerTurn = false;
            Handler().postDelayed(Runnable { playerTurn = true } , 600)
            playnow(but, cellID)

        }
    }
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1
    fun playnow(buttonSelected:Button , currCell:Int) {

        if(activeUser == 1)
        {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)

            buttonSelected.isEnabled = false
            val checkWinner = playerwinner()
            if(checkWinner == 1){
                Handler().postDelayed(Runnable { reset() } , 2000)
            } else if (singleUser){
                Handler().postDelayed(Runnable{Tictactoebot()},500)
            }

            //activeUser = 2
            activeUser = 1

        } else {
            buttonSelected.text = "O"

            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))

            activeUser = 2
            player2.add(currCell)
            emptyCells.add(currCell)

            buttonSelected.isEnabled = false
            val checkWinner  = playerwinner()
            if(checkWinner == 1)
                Handler().postDelayed(Runnable { reset() } , 4000)
        }

    }
    fun playerwinner():Int
    {

        if((player1.contains(1) && player1.contains(2) && player1.contains(3) ) || (player1.contains(1) && player1.contains(4) && player1.contains(7))||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) || (player1.contains(7) && player1.contains(8) && player1.contains(9))||
            (player1.contains(4)&&player1.contains(5)&&player1.contains(6)) || (player1.contains(1)&&player1.contains(5) && player1.contains(9))||
            player1.contains(3)&&player1.contains(5)&&player1.contains(7) || (player1.contains(2)&&player1.contains(5) && player1.contains(8))) {
            player1Count+=1
            buttonDisable()

            disableReset()

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()

            }
            build.setNegativeButton("Exit") { dialog, which ->

                exitProcess(1)

            }
            Handler().postDelayed(Runnable { build.show() } , 2000)
            return 1

        }
        else if((player2.contains(1) && player2.contains(2) && player2.contains(3) ) || (player2.contains(1) && player2.contains(4) && player2.contains(7))||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(7) && player2.contains(8) && player2.contains(9))||
            (player2.contains(4)&&player2.contains(5)&&player2.contains(6)) || (player2.contains(1)&&player2.contains(5) && player2.contains(9))||
            player2.contains(3)&&player2.contains(5)&&player2.contains(7) || (player2.contains(2)&&player2.contains(5) && player2.contains(8))){
            player2Count+=1

            buttonDisable()
            disableReset()

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 Wins!!" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok"){dialog, which ->
                reset()

            }
            build.setNegativeButton("Exit"){dialog, which ->

                exitProcess(1)
            }
            Handler().postDelayed(Runnable { build.show() } , 2000)
            return 1
        }
        else if(emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3) && emptyCells.contains(4) && emptyCells.contains(5) && emptyCells.contains(6) && emptyCells.contains(7) &&
            emptyCells.contains(8) && emptyCells.contains(9) ) {

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok"){dialog, which ->
                reset()
            }
            build.setNegativeButton("Exit"){dialog, which ->
                exitProcess(1)
            }
            build.show()
            return 1

        }
        return 0
    }

    fun reset()
    {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1;
        for(i in 1..9)
        {
            var buttonselected : Button?
            buttonselected = when(i){
                1 -> button
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button}
            }
            buttonselected.isEnabled = true
            buttonselected.text = ""
            textView.text = "Player1 : $player1Count"
            textView2.text = "Player2 : $player2Count"
        }
    }

    fun Tictactoebot() {
        val rnd = (1..9).random()
        if(emptyCells.contains(rnd))
            Tictactoebot()
        else {
            val buttonselected : Button?
            buttonselected = when(rnd) {
                1 -> button
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button}
            }
            emptyCells.add(rnd);

            buttonselected.text = "O"
            buttonselected.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(rnd)
            buttonselected.isEnabled = false
            var checkWinner = playerwinner()
            if(checkWinner == 1)
                Handler().postDelayed(Runnable { reset() } , 2000)

        }
    }

    fun buttonDisable()
    {
        for(i in 1..9)
        {
            val buttonSelected = when(i)
            {
                1 -> button
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {button}

            }
            if(buttonSelected.isEnabled == true)
                buttonSelected.isEnabled = false
        }
    }

    fun disableReset()
    {
        button10.isEnabled = false
        Handler().postDelayed(Runnable { button10.isEnabled = true } , 2200)
    }
}

