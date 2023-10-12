package com.example.foodapp.fragments.bottomsheet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.activity.MealAktivity
import com.example.foodapp.databinding.FragmentMealBottomSheetBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.viewModel.HomeVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val Meal_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private var mealId: String? = null
    private lateinit var binding: FragmentMealBottomSheetBinding
    private lateinit var viewModel: HomeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(Meal_ID)
        }
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
binding = FragmentMealBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealId?.let {
            viewModel.getMealById(it) }

        observeBottomSheetMeal()
        onBSDialogClick()
    }

    private fun onBSDialogClick() {
        binding.bottomSheet.setOnClickListener{
            if(mealName !=null && mealThumb !=null ){
                val intent = Intent(activity,MealAktivity::class.java)
               intent.apply {
                    putExtra(HomeFragment.MEAL_ID,mealId)
                    putExtra(HomeFragment.MEAL_NAME,mealName)
                    putExtra(HomeFragment.MEAL_THUMB,mealThumb)
                }
                startActivity(intent)
            }
        }
    }
       private var mealName:String? = null
    private var mealThumb:String? = null

    private fun observeBottomSheetMeal() {
        viewModel.observeBS().observe(viewLifecycleOwner, Observer {  meal->
Glide.with(this).load(meal.strMealThumb).into(binding.imgbottomSheet)
            binding.bottomSheetArea.text = meal.strArea
            binding.bottomSheetCategory.text = meal.strCategory
            binding.tvName.text= meal.strMeal

            mealName = meal.strMeal
            mealThumb = meal.strMealThumb
        })
    }


    companion object {

        @JvmStatic fun newInstance(param1: String) =
                MealBottomSheetFragment().apply {
                    arguments = Bundle().apply {
                        putString(Meal_ID, param1)

                    }
                }
    }
}