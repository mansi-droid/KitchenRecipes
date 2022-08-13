package com.dev.kitchenrecipes.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.data.activities.RecipeDetailActivity
import com.dev.kitchenrecipes.Utils
import com.dev.kitchenrecipes.data.model.RecipeModel
import com.dev.kitchenrecipes.databinding.RecipesListLayoutBinding

class RecipesListAdapter(
    private val listModel : List<RecipeModel>, private val ctx : Context
) : RecyclerView.Adapter<RecipesListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val v : RecipesListLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.recipes_list_layout, parent,
                false
            )
        return MyViewHolder(v)
    }

    inner class MyViewHolder(var binding : RecipesListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.binding.tvRecipeName.text = listModel[position].recipe_name

        holder.binding.llMainLayout.setOnClickListener {
            Utils.preferenceStore(ctx, listModel, position)
            val i = Intent(ctx, RecipeDetailActivity::class.java)
            ctx.startActivity(i)
        }
    }

    override fun getItemCount() : Int {
        return listModel.size
    }
}