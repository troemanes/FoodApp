package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemsBinding
import com.example.foodapp.pojo.MealsByCategory

class MostPopularAdapter() :RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    lateinit var onItemClick:((MealsByCategory) -> Unit)
    private var  mealsList = ArrayList<MealsByCategory>()
    var onLongItemClick:((MealsByCategory)->Unit)?=null

    fun setMeals(mealsList:ArrayList<MealsByCategory>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }
    class PopularMealViewHolder(var binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }



    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
              Glide.with(holder.itemView)
                  .load(mealsList[position].strMealThumb)
                  .into(holder.binding.imgPopularItem)
     holder.itemView.setOnClickListener {

onItemClick.invoke(mealsList[position])
     }
       holder.itemView.setOnLongClickListener {
           onLongItemClick?.invoke(mealsList[position])
           true
       }
    }
    override fun getItemCount(): Int {
        return mealsList.size
    }

}