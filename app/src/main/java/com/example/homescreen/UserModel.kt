package com.example.homescreen

data class UserModel(
    val name: String,
    val age: Int,
    val distance: Double,
    val occupation: String,
    val image: Int, //change this to URL when implementing
    val about: String,
    val interests : ArrayList<String>
)
