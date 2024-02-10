package com.example.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MoveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)
    }
    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(this, "onStart Move Called", Toast.LENGTH_LONG).show();
    }

    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(this, "onRestart Move Called", Toast.LENGTH_LONG).show();
    }
    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(applicationContext, "onResume Move Called", Toast.LENGTH_LONG).show()
    }
    override fun onPause() {
        super.onPause()
        // It will show a message on the screen
        // then onPause is invoked
        val toast = Toast.makeText(applicationContext, "nPaused Move Called", Toast.LENGTH_LONG).show()
    }
    override fun onStop() {
        super.onStop()
        val toast = Toast.makeText(applicationContext, "onStop Move Called", Toast.LENGTH_LONG).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(applicationContext, "onDestroy Move Called", Toast.LENGTH_LONG).show()
    }
}