package com.example.myappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myappbar.databinding.ActivityMenuBinding
import android.widget.Toast

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener{
                    textView,actionId,event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@MenuActivity,searchView.text,Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }

}