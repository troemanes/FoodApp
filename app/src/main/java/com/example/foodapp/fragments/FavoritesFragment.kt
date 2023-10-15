package com.example.foodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.activity.CategoryMealsActivity
import com.example.foodapp.activity.MainActivity
import com.example.foodapp.activity.MealAktivity
import com.example.foodapp.adapters.MealsAdapter
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.example.foodapp.viewModel.HomeVM
import com.google.android.material.snackbar.Snackbar
import android.content.Intent


class FavoritesFragment : Fragment() {
//Sonradan
    companion object {

        const val MEAL_ID = "com.example.foodapp.fragments.idMeal"
        const val MEAL_NAME = "com.example.foodapp.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.foodapp.fragments.thumbMeal"
    }

private lateinit var binding: FragmentFavoritesBinding
private lateinit var viewModel: HomeVM
private lateinit var favoritesAdapter : MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =  (activity as MainActivity).viewModel

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRV()
        observeFavorites()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position  = viewHolder.adapterPosition
                val deletedMeal = favoritesAdapter.differ.currentList[position]
                viewModel.deleteMeal(deletedMeal)

                Snackbar.make(requireView(), "Meal Deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {println("Geri alındı")
                        viewModel.insertMeal(deletedMeal)
                    }
                ).show()

            }

        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorites)
    }
    private fun prepareRV() {
        favoritesAdapter = MealsAdapter()
         binding.rvFavorites.apply {

        layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        adapter = favoritesAdapter

             favoritesAdapter.onItemClick = { meal ->
                 // Sonradan
                 val intent = Intent(requireActivity(), MealAktivity::class.java)
                 intent.putExtra(MEAL_ID,meal.idMeal)
                 intent.putExtra(MEAL_NAME,meal.strMeal)
                 intent.putExtra(MEAL_THUMB,meal.strMealThumb)
                 startActivity(intent)}
    }}


    private fun observeFavorites() {
        viewModel.observeFavoritesMealsLiveData().observe(requireActivity()) { meals ->
            favoritesAdapter.differ.submitList(meals)

        }
    }
}
