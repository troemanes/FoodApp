package com.example.foodapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.MealsItemBinding
import com.example.foodapp.pojo.Meal

class MealsAdapter: RecyclerView.Adapter<MealsAdapter.FavoritesMealsAdapterViewHolder>() {
    var onItemClick: ((Meal) -> Unit)? = null

    inner class FavoritesMealsAdapterViewHolder(val binding: MealsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //Sonradan
        init {
            binding.root.setOnClickListener {
                onItemClick!!.invoke(differ.currentList[adapterPosition])
            }
        } }


        private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this, diffUtil)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FavoritesMealsAdapterViewHolder {
            return FavoritesMealsAdapterViewHolder(
                MealsItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: FavoritesMealsAdapterViewHolder, position: Int) {
            val meal = differ.currentList[position]
            Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imgMeal)
            holder.binding.tvMealName.text = meal.strMeal
        }

        override fun getItemCount(): Int {
            return differ.currentList.size
            val itemCount = differ.currentList.size
            println("its work meal adapter")
            Log.d("FavoritesMealsAdapter", "Item count: $itemCount")
            return itemCount
        }

    }
