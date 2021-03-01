package com.personal.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_finished)
    }
    fun returnToMenu(view: View) {
        startActivity(
            Intent(applicationContext, MainActivity::class.java)
        )
    }
}