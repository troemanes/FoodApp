package com.example.foodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.adapters.CategoriesAdapter
import com.example.foodapp.databinding.FragmentCategoriesBinding
import com.example.foodapp.viewModel.HomeVM


class CategoriesFragment : Fragment() {
 private lateinit var binding:FragmentCategoriesBinding
private lateinit var categoriesAdapter:CategoriesAdapter
private lateinit var viewModel:HomeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)

        prepareRCV()

        observeCategories()
    }

    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) { categories ->

            categoriesAdapter.setCategoryList(categories)
        }
    }

    private fun prepareRCV() {
        categoriesAdapter = CategoriesAdapter()
binding.rvCategories.apply {
    layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)

adapter = categoriesAdapter
}
    }
}