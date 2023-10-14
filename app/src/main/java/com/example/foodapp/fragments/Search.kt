package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.LinearEasing
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapters.MealsAdapter
import com.example.foodapp.databinding.FragmentSearchBinding
import com.example.foodapp.viewModel.HomeVM
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Search : Fragment() {
      private lateinit var binding: FragmentSearchBinding
private lateinit var viewModel :HomeVM
private lateinit var searchRva: MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



            viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRv()
        binding.imgSearch.setOnClickListener{ searchMeals() }

        observeSearchMealsLiveData()


        //search auto click - find
        var searchJob:Job?=null
        binding.searchbox.addTextChangedListener {searchQuery->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
           viewModel.searchMeals(searchQuery.toString())
            }
        }
    }

    private fun observeSearchMealsLiveData() {
        viewModel.observeSearchedMealsLiveData().observe(viewLifecycleOwner, Observer {
            mealsList->
            searchRva.differ.submitList(mealsList)
        })
    }

    private fun searchMeals() {
        val searchQuery = binding.searchbox.text.toString()
        if(searchQuery.isNotEmpty()){
            viewModel.searchMeals(searchQuery)
        }
    }

    private fun prepareRv() {
searchRva = MealsAdapter()
        binding.rvSearchMeals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchRva
        }
    }
}