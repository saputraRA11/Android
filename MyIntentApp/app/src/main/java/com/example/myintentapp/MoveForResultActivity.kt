package com.example.myintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }

    private lateinit var btnChoose:Button
    private lateinit var valNumber:RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnChoose = findViewById(R.id.btn_choose)
        valNumber = findViewById(R.id.val_numbers)

        btnChoose.setOnClickListener(this)

    }
    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(this, "onStart result Called", Toast.LENGTH_LONG).show();
    }

    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(this, "onRestart result Called", Toast.LENGTH_LONG).show();
    }
    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(applicationContext, "onResume result Called", Toast.LENGTH_LONG).show()
    }
    override fun onPause() {
        super.onPause()
        // It will show a message on the screen
        // then onPause is invoked
        val toast = Toast.makeText(applicationContext, "nPaused result Called", Toast.LENGTH_LONG).show()
    }
    override fun onStop() {
        super.onStop()
        val toast = Toast.makeText(applicationContext, "onStop result Called", Toast.LENGTH_LONG).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        val toast =
            Toast.makeText(applicationContext, "onDestroy result Called", Toast.LENGTH_LONG).show()
    }
        override fun onClick(v: View?) {
        if(v?.id == R.id.btn_choose) {
            if(valNumber.checkedRadioButtonId > 0) {
                var value = 0
                when(valNumber.checkedRadioButtonId) {
                    R.id.val_50 -> value = 50
                    R.id.val_100 -> value = 100
                    R.id.val_150 -> value = 150
                    R.id.val_200 -> value = 200
                }

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE,value)
                setResult(RESULT_CODE,resultIntent)
                finish()
            }
        }
    }
}