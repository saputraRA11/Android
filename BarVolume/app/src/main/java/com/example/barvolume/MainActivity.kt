package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.barvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    private lateinit var valWidth:EditText
//    private lateinit var valHeight:EditText
//    private lateinit var valLength:EditText
//    private lateinit var btnCalculate:Button
//    private lateinit var tvResult:TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        valWidth = findViewById(R.id.val_width)
//        valHeight = findViewById(R.id.val_height)
//        valLength = findViewById(R.id.val_length)
//        btnCalculate = findViewById(R.id.btn_generate)
//        tvResult = findViewById(R.id.val_result)
//        btnCalculate.setOnClickListener(this)

        if(savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
//            tvResult.text = result
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGenerate.setOnClickListener(this)
        if(savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.valResult.text = result
        }
    }

//    override fun onClick(v: View?) {
//        if(v?.id == R.id.btn_generate) {
//            var isEmpty:Boolean = false
//            val inLength = valLength.text.toString().trim()
//            val inWidth = valWidth.text.toString().trim()
//            val inHeight = valHeight.text.toString().trim()
//
//            if(inLength.isEmpty()) {
//                valLength.error = "Field ini tidak boleh kosong"
//                isEmpty = true
//            }
//            if(inWidth.isEmpty()) {
//                valWidth.error = "Field ini tidak boleh kosong"
//                isEmpty = true
//            }
//            if(inHeight.isEmpty()) {
//                valHeight.error = "Field ini tidak boleh kosong"
//                isEmpty = true
//            }
//
//            if(!isEmpty) {
//                val volume = inLength.toDouble() * inWidth.toDouble() * inHeight.toDouble()
//                tvResult.text = volume.toString()
//            }
//        }
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(STATE_RESULT,tvResult.text.toString())
//    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_generate) {
            var isEmpty:Boolean = false
            val inLength = binding.valLength.text.toString().trim()
            val inWidth = binding.valWidth.text.toString().trim()
            val inHeight = binding.valHeight.text.toString().trim()

            if(inLength.isEmpty()) {
                binding.valLength.error = "Field ini tidak boleh kosong"
                isEmpty = true
            }
            if(inWidth.isEmpty()) {
                binding.valWidth.error = "Field ini tidak boleh kosong"
                isEmpty = true
            }
            if(inHeight.isEmpty()) {
                binding.valHeight.error = "Field ini tidak boleh kosong"
                isEmpty = true
            }

            if(!isEmpty) {
                val volume = inLength.toDouble() * inWidth.toDouble() * inHeight.toDouble()
                binding.valResult.text = volume.toString()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,binding.valResult.text.toString())
    }
}