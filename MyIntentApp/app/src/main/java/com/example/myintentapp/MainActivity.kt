package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var valResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
            valResult.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity:Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithData:Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithObject:Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone:Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        valResult = findViewById(R.id.val_result)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity,MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity,MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME,"Saputra Ari Wijaya")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE,20)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val moveWithObjectIntent = Intent(this@MainActivity,MoveWithObjectActivity::class.java)
                val newPerson:Person = Person("Saputra",10,"Realari27@gmail.com","Bali")
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,newPerson)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "082211843505"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity,MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }

}