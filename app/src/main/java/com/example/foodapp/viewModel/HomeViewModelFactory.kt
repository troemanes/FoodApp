package com.example.foodapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.db.MealDatabase

class HomeViewModelFactory(
    private val mealDatabase: MealDatabase
) : ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>):T {
       return HomeVM(mealDatabase) as T
   }
}