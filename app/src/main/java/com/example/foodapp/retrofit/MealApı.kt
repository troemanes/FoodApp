package com.example.foodapp.retrofit

import com.example.foodapp.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApı {
    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}