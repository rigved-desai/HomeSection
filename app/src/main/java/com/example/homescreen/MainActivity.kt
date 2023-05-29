package com.example.homescreen

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.service.autofill.FieldClassification.Match
import android.telephony.mbms.DownloadRequest
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.slider.RangeSlider
import com.yuyakaido.android.cardstackview.*

class MainActivity : AppCompatActivity(), CardClickListener{

    private lateinit var cardStackLayoutManager: CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Implement getData() from database later, using faux data only for representation for now
        val profiles = ArrayList<UserModel>()
//        profiles = getData()
        profiles.add(UserModel("User 1", 25, 4.0, "Model", R.drawable.user_1, "I am Lorem Ipsum", arrayListOf("Singing", "Dancing", "Coding", "Painting", "Hiking", "Cafe-hopping")))
        profiles.add(UserModel("User 2", 23, 2.0, "Singer", R.drawable.user_2,"I am Lorem Ipsum", arrayListOf("Singing", "Dancing")))
        profiles.add(UserModel("User 3", 28, 3.3, "Model", R.drawable.user_1, "I am Lorem Ipsum", arrayListOf("Singing", "Dancing")))
        setUpMatchStack(profiles)
        setUpMatchBtns()

        val filtersBtn : Button = findViewById(R.id.set_filter)
        filtersBtn.setOnClickListener {
            showFiltersDialog();
        }
    }

    private fun setUpMatchBtns() {
        val favBtn: Button = findViewById(R.id.fav_btn)
        val closeBtn: Button = findViewById(R.id.close_btn)
        val starBtn: Button = findViewById(R.id.star_btn)
        val matchStack: CardStackView = findViewById(R.id.stack)

        val leftSwipe = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .build()

        val rightSwipe = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .build()

        //Implement updateSwipeData() later

        favBtn.setOnClickListener {

            //Update swipes

            cardStackLayoutManager.setSwipeAnimationSetting(rightSwipe)
            matchStack.layoutManager = cardStackLayoutManager
            matchStack.swipe()
            //updateSwipeData();
        }

        closeBtn.setOnClickListener {
            cardStackLayoutManager.setSwipeAnimationSetting(leftSwipe)
            matchStack.layoutManager = cardStackLayoutManager
            matchStack.swipe()
            //updateSwipeData();
        }

        starBtn.setOnClickListener {
            cardStackLayoutManager.setSwipeAnimationSetting(rightSwipe)
            matchStack.layoutManager = cardStackLayoutManager
            matchStack.swipe()
            //updateSwipeData();
        }
    }


    private fun setUpMatchStack(profiles : ArrayList<UserModel>) {
        val stack: CardStackView = findViewById(R.id.stack)
        val matchListener = MatchStackListener(this)
        val cardClickListener: CardClickListener

        cardStackLayoutManager = CardStackLayoutManager(this, matchListener)

        //Set up the stack swipe direction
        cardStackLayoutManager.apply {
            setStackFrom(StackFrom.None)
            setVisibleCount(profiles.size)
            setMaxDegree(20.0F)
            setDirections(Direction.FREEDOM)
            setSwipeThreshold(0.5F)
            setCanScrollVertical(false)
            }
        cardStackLayoutManager
        stack.layoutManager = cardStackLayoutManager
        stack.adapter = MatchAdapter(profiles, this, this)
    }

    private fun showFiltersDialog() {
        val dialog =  Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);

        setUpFilterSlider(dialog);
        setUpFilterRadioBtns(dialog);
        setUpFilterBtns(dialog);

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimation;
        dialog.window?.setGravity(Gravity.BOTTOM);

    }

    private fun setUpFilterSlider(dialog: Dialog) {

        // Replace set values from values stored in the device/database

        val ageSlider: RangeSlider = dialog.findViewById(R.id.age_slider)
        ageSlider.setValues(20.0F, 40.0F)
    }

    private fun setUpFilterRadioBtns(dialog: Dialog) {

        // Replace set values from values stored in the device/database

        val radioGrp = dialog.findViewById<RadioGroup>(R.id.gender_group)

        radioGrp.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = dialog.findViewById<RadioButton>(checkedId)
            val defaultColor = radioButton.currentTextColor

            radioButton.setTextColor(Color.WHITE)

            for (i in 0 until radioGrp.childCount) {
                val child = radioGrp.getChildAt(i)
                if (child is RadioButton && child.id != checkedId) {
                    child.setTextColor(defaultColor)
                }
            }
        }
        // Get gender value of user from database and set it as checked, set to male for demonstration

        val maleBtn: RadioButton = dialog.findViewById(R.id.male_btn)
        maleBtn.isChecked = true
    }

    private fun setUpFilterBtns(dialog: Dialog) {
        val resetBtn: Button = dialog.findViewById(R.id.reset_btn)
        val applyBtn : Button  = dialog.findViewById(R.id.apply_btn)

        applyBtn.setOnClickListener {

            //Add code to replace user's filter values

            dialog.dismiss()
        }

        resetBtn.setOnClickListener {

            //Add code to reset to default values

        }
    }

    override fun onItemClicked(userData: UserModel) {
        Intent(this, ViewMatchProfileActivity::class.java).also {
            it.putExtra("USER_NAME", userData.name)
            it.putExtra("USER_AGE", userData.age)
            it.putExtra("USER_OCCUPATION", userData.occupation)
            it.putStringArrayListExtra("USER_INTERESTS", userData.interests)
            it.putExtra("USER_ABOUT", userData.about)

            // Change this to arraylist of URLS when getting data from database
            it.putExtra("USER_IMAGE", userData.image)

            startActivity(it)
        }
    }
}