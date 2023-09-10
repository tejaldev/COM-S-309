package main.res.layout

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.as1.R
import java.util.Random
import java.util.*

class Trial : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trial)

        val rollButton = findViewById<Button>(R.id.rollbutton)
        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        rollButton.setOnClickListener{
            val rand = Random().nextInt(seekBar.progress)
            resultsTextView.text=rand.toString()
        }


    }
}