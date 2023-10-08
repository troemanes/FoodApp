package com.example.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.CategoryMealsAdapter
import com.example.foodapp.databinding.ActivityCategoryMealsBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.viewModel.CategoryMealsVM
import com.example.foodapp.viewModel.HomeVM
import com.example.foodapp.viewModel.MealVM

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var categoryMealsVM: CategoryMealsVM
    lateinit var categoryMealsAdapter:CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()


        categoryMealsVM = ViewModelProvider(this).get(CategoryMealsVM::class.java)
        categoryMealsVM.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryMealsVM.observeMealsLiveData().observe(this, Observer { mealsList ->
            binding.tvCategoryCount.text =  mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
        })




    }

     fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }
}