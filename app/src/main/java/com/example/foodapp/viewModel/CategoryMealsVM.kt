package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.MealsByCategory
import com.example.foodapp.pojo.MealsByCategoryList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoryMealsVM : ViewModel() {
  val mealsLiveData = MutableLiveData<List<MealsByCategory>>()
    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : retrofit2.Callback<MealsByCategoryList> {

            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let { mealsList ->
                    mealsLiveData.postValue(mealsList.meals)
                }
            }


                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e("CategoryMealsViewModel", t.message.toString())
                }
        })
    }

    fun observeMealsLiveData(): LiveData<List<MealsByCategory>>{
 return mealsLiveData
    }
}
