package com.example.foodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.pojo.Meal
import com.example.foodapp.pojo.MealList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
     private lateinit var binding:FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

          RetrofitInstance.api.getRandomMeal().enqueue(object:Callback<MealList>{
              override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                  if(response.body() != null) {
                      val randomMeal:Meal =  response.body()!!.meals[0]

                 Glide.with(this@HomeFragment)
                   .load(randomMeal.strMealThumb)
                     .into(binding.imageRandomMeal)
                  }else  {
                      return
                  }
              }

              override fun onFailure(call: Call<MealList>, t: Throwable) {

                  Log.d("Home Fragment",t.message.toString())
              }

          })


    }


}

