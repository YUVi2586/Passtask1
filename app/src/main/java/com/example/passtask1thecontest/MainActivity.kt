package com.example.passtask1thecontest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private var results: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            results = it.getInt("MyInt", 0)
        }


        //Buttons connections
        val score = findViewById<Button>(R.id.Button_SCORE)
        val Steal = findViewById<Button>(R.id.Button_Steal)
        val Reset = findViewById<Button>(R.id.Button_reset)
        val Result = findViewById<TextView>(R.id.textView)
        Result.text = results.toString()


        val mediabeep = MediaPlayer.create(this, R.raw.beep_short)
        val mediaBugle = MediaPlayer.create(this, R.raw.bugle_tune)


        savedInstanceState?.let {
            results = it.getInt("MyInt", 0)
        }



        fun checkBounds() {
            score.setEnabled(results < 15)
            Steal.setEnabled(results > 0)
        }

        score.setOnClickListener{

            mediaBugle.start()
            results++
            if (results <= 15) {
                Result.text = "$results"
                updateResults()

            }
            checkBounds()


        }

        Steal.setOnClickListener{
            if (results > 0){
                results --
                Result.text = "$results"
                updateResults()
                checkBounds()
                mediaBugle.pause()
                mediabeep.start()


            }

        }

        Reset.setOnClickListener {
            results = 0
            Result.text = "$results"
            updateResults()
            mediaBugle.stop()
            mediabeep.stop()



        }



    }






    private fun updateResults(){
        val resulting = findViewById<TextView>(R.id.textView)
        resulting.text = "$results"


        when{
            results <= 5 -> resulting.setTextColor(Color.RED)
            results >= 10 -> resulting.setTextColor(Color.BLUE)
            else -> resulting.setTextColor(Color.BLACK)

        }


    }
    //onRestoreInstanceState
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {


        results = savedInstanceState.getInt("MyInt")
        super.onRestoreInstanceState(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("MyInt", results)
        super.onSaveInstanceState(outState)
    }


    }


