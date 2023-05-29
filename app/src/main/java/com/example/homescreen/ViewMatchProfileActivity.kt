package com.example.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nex3z.flowlayout.FlowLayout

class ViewMatchProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_match_profile)

        val userName = intent.getStringExtra("USER_NAME")
        val userAge = intent.getIntExtra("USER_AGE", 0)
        val userOccupation = intent.getStringExtra("USER_OCCUPATION")
        val userImage = intent.getIntExtra("USER_IMAGE", R.drawable.user_1)
        val userAbout = intent.getStringExtra("USER_ABOUT")
        val userInterests = intent.getStringArrayListExtra("USER_INTERESTS")

        findViewById<TextView>(R.id.profile_name).text = "${userName}, "
        findViewById<TextView>(R.id.profile_occupation).text = userOccupation
        findViewById<TextView>(R.id.profile_age).text = userAge.toString()
        findViewById<TextView>(R.id.profile_about).text = userAbout

        val bottomSheetLayout = findViewById<ConstraintLayout>(R.id.profile_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        bottomSheetBehavior.peekHeight = 100
        bottomSheetBehavior.maxHeight = ViewGroup.LayoutParams.WRAP_CONTENT
        val interests : FlowLayout = findViewById(R.id.user_interests)

        if (userInterests != null) {
            for (i in userInterests) {
                val interest  = TextView(ContextThemeWrapper(this, R.style.customTextView), null, 0)
                interest.text = i
                interests.addView(interest)
            }

        }
    }
}