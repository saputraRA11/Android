package com.example.myflexiblefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // supportFragmentManager -> default dari activity , lempar host activity ke fragment host
        // dengan homeFragment as parentManager
        val fragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val fragment = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        // kalau fragment bukan HomeFragment
        if(fragment !is HomeFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container,homeFragment,HomeFragment::class.java.simpleName)
                .commit()
        }
    }
}