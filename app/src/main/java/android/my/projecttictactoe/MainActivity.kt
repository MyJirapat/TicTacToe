package android.my.projecttictactoe

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

var singleUser = false
//var multiUser = false
class MainActivity : AppCompatActivity() {
    lateinit var singlePlayerBtn: Button
    lateinit var multiPlayerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // singlePlayerBtn = findViewById(R.id.idBtnSinglePlayer)
        //multiPlayerBtn = findViewById(R.id.idBtnMultiPlayer)
        idBtnSinglePlayer.setOnClickListener{
            singleUser = true
            startActivity(Intent(this, GamePlayActivity::class.java))
        }

        idBtnMultiPlayer.setOnClickListener{
            singleUser = false
           // multiUser = true
           // startActivity(Intent(this, GamePlayActivity::class.java))
            startActivity(Intent(this, MultiPlayActivity::class.java))
        }
        /*
        idOnline.setOnClickListener{
            single = false
            startActivity(Intent(this, OnlinePlayActivity::class.java))
        }
        */
    }
    override fun onBackPressed() {
        ActivityCompat.finishAffinity(this)
    }
}
