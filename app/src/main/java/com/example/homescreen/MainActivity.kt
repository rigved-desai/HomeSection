package com.example.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragContainer = findViewById<FrameLayout>(R.id.filter_settings)
        val filtersBtn : FrameLayout = findViewById(R.id.set_filter)
        val filterSettings = FilterFragment()
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_bot)
        fragContainer.startAnimation(slideInAnimation)


        filtersBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.filter_settings, filterSettings )
                addToBackStack(null)
                commit()
            }
        }

    }
}