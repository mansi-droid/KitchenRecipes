package com.dev.kitchenrecipes.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.Constants
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.data.activities.RecipeDetailActivity
import com.dev.kitchenrecipes.data.model.RecipeModel
import com.dev.kitchenrecipes.databinding.RecipesSquareListLayoutBinding

class RecipesListHorizontalAdapter(
    private val listModel : List<RecipeModel>, private val ctx : Context
) : RecyclerView.Adapter<RecipesListHorizontalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val v : RecipesSquareListLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.recipes_square_list_layout,
                parent,
                false
            )
        return MyViewHolder(v)
    }

    inner class MyViewHolder(var binding : RecipesSquareListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.binding.tvRecipeName.text = listModel[position].recipe_name

        holder.binding.llMainLayout.setOnClickListener {
            val sharedPreference =
                ctx.getSharedPreferences(Constants.recipeData, Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(Constants.recipeName, listModel[position].recipe_name)
            editor.putString(Constants.recipeItem, listModel[position].recipe_item)
            editor.putString(Constants.recipeMethod, listModel[position].recipe_method)
            editor.apply()
            val i = Intent(ctx, RecipeDetailActivity::class.java)
            ctx.startActivity(i)
        }
    }

    override fun getItemCount() : Int {
        return listModel.size
    }
}
