package com.example.mytestingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    private var btnSetValue:Button? = null
    private lateinit var btnSetValue:Button
    private lateinit var valText:TextView

    private var names = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        valText = findViewById(R.id.val_text)
        btnSetValue = findViewById(R.id.btn_set_value)
        btnSetValue.setOnClickListener(this)
//        btnSetValue!!.setOnClickListener(this)

        names.add("Saputra")
        names.add("Ari")
        names.add("Wijaya")
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_set_value) {
            Log.d("MainActivity",names.toString())
            val name = StringBuilder()
            for(i in 0..2){
                name.append(names[i]).append('\n')
            }

//            valText.text = "19"
            valText.text = name.toString()
        }
    }
}