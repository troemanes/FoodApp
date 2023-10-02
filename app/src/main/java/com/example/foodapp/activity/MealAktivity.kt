package com.example.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealAktivityBinding
import com.example.foodapp.fragments.HomeFragment

class MealAktivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String

    private lateinit var binding : ActivityMealAktivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealAktivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

     //   getMealInformationFromIntent()

        // setInformationInView()
    }

    /* private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
         binding.collapsingtb.title = mealName
        binding.collapsingtb.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingtb.setExpandedTitleColor(resources.getColor(R.color.white))

    }   */



  /*  private fun getMealInformationFromIntent(){
            val intent = intent
        mealId=intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName=intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb=intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }  */
}