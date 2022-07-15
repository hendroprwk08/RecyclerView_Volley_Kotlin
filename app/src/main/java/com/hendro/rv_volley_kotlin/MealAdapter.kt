package com.hendro.rv_volley_kotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hendro.rv_volley_kotlin.databinding.RowItemLayoutBinding

class MealAdapter (private var mealList: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MyViewHolder>(){

    //binding layout: 1. ganti "binding: ItemRowLayoutBinding" dan "binding.root"
    class MyViewHolder(val binding: RowItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //binding layout: 2. tarik layout
        val binding = RowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //binding layout: 3. letakkan nilai pada layout
        holder.binding.tvMeal.text = mealList[position].getMeal()

        Glide.with(holder.itemView.context)
            .load(mealList[position].getImage())
            .into(holder.binding.imgMeal)

        holder.itemView.setOnClickListener(){

        }

    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}