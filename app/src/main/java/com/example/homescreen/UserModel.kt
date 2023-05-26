package com.example.homescreen

import android.graphics.drawable.Drawable

data class UserModel(
    val name: String,
    val age: Int,
    val distance: Double,
    val occupation: String,
    val image: Int, //change this to URL when implementing
)
