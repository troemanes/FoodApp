package com.example.foodapp.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealAktivityBinding
import com.example.foodapp.db.MealDatabase
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.pojo.Meal
import com.example.foodapp.viewModel.MealVM
import com.example.foodapp.viewModel.MealViewModelFactory

class MealAktivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealAktivityBinding
    private lateinit var youtubeLink : String
    private lateinit var MealMvvm: MealVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealAktivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        MealMvvm = ViewModelProvider(this,viewModelFactory)[MealVM::class.java]

        getMealInformationFromIntent()
        setInformationInView()
        loadingCase()
        MealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()

        onYoutubeImgClick()
        onFavoriteClick()
    }

    private fun onFavoriteClick() {

        binding.addtoFav.setOnClickListener{
            mealtoSave?.let {
                MealMvvm.insertMeal(it)
                Toast.makeText(this, "Meal Saved", Toast.LENGTH_SHORT).show()
                println("MealSaved , Saved Meal: ${it.strMeal}")
            } ?: run {
                println("No Food")
            }
        }
    }


    private fun onYoutubeImgClick() {
        binding.imageYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private var  mealtoSave:Meal?=null
    private fun observerMealDetailsLiveData() {
        MealMvvm.observeMealDetailsLiveDate().observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal ) {
                onResponseCase()
                val meal = value
                mealtoSave =  meal

                binding.tvCategory.text = "Category: ${meal!!.strCategory}"
                binding.tvArea.text = "Area: ${meal.strArea}"
                binding.instructionTv.text = meal.strInstructions

                youtubeLink = meal.strYoutube
            }

        })
    }

    private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingtb.title = mealName
        binding.collapsingtb.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingtb.setExpandedTitleColor(resources.getColor(R.color.white))

    }


    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.addtoFav.visibility = View.INVISIBLE
        binding.instructionTv.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imageYoutube.visibility = View.INVISIBLE

    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.addtoFav.visibility = View.VISIBLE
        binding.instructionTv.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imageYoutube.visibility = View.VISIBLE
    }

}