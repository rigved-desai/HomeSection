package com.example.homescreen

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filtersBtn : FrameLayout = findViewById(R.id.set_filter)
        filtersBtn.setOnClickListener {
            showFiltersDialog();
        }
    }

    private fun showFiltersDialog() {
        val dialog =  Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);

        setUpSlider(dialog);
        setUpRadioBtns(dialog);
        setUpBtns(dialog);

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimation;
        dialog.window?.setGravity(Gravity.BOTTOM);

    }

    private fun setUpSlider(dialog: Dialog) {

        // Replace set values from values stored in the device/database

        val ageSlider: RangeSlider = dialog.findViewById(R.id.age_slider)
        ageSlider.setValues(20.0F, 40.0F)
    }

    private fun setUpRadioBtns(dialog: Dialog) {

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

    private fun setUpBtns(dialog: Dialog) {
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

}