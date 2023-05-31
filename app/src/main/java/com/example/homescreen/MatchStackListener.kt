package com.example.homescreen

import android.content.Context
import android.view.View
import android.widget.Toast
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class MatchStackListener(private val context: Context) : CardStackListener {


    override fun onCardSwiped(direction: Direction?) {

        //Replace the toasts with function which updates swipe list of user

        if(direction == Direction.Left) Toast.makeText(context , "Swiped Left", Toast.LENGTH_SHORT).show()
        else  Toast.makeText(context , "Swiped Right", Toast.LENGTH_SHORT).show()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}
}