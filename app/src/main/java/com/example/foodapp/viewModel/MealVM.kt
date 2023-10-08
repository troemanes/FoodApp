package com.example.foodapp.viewModel

import android.health.connect.ReadRecordsRequestUsingIds
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.pojo.Meal
import com.example.foodapp.pojo.MealList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Callback
import retrofit2.Response

class MealVM:ViewModel() {

    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id:String){
     RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList>{
         override fun onResponse(call: retrofit2.Call<MealList>, response: Response<MealList>) {
             if (response.body()!=null){
                 mealDetailsLiveData.value = response.body()!!.meals[0]
             }
             else
                 return
         }

         override fun onFailure(call: retrofit2.Call<MealList>, t: Throwable) {
             Log.d("MealActivity",t.message.toString())
         }

     })

    }

    fun observeMealDetailsLiveDate():LiveData<Meal>{
        return mealDetailsLiveData
    }
}