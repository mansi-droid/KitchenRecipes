package com.dev.kitchenrecipes.data.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.Constants
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.Utils
import com.dev.kitchenrecipes.data.adapter.RecipesListHorizontalAdapter
import com.dev.kitchenrecipes.data.model.RecipeModel
import com.dev.kitchenrecipes.databinding.ActivityRecipeDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RecipeDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityRecipeDetailBinding
    private var recipeName : String? = null
    private var recipeItem : String? = null
    private var recipeMethod : String? = null
    private var shared : SharedPreferences? = null
    private var mAdapter : RecipesListHorizontalAdapter? = null
    private var jsonFileString : String? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail)

        shared = getSharedPreferences(Constants.recipeData, Context.MODE_PRIVATE)
        recipeName = shared?.getString(Constants.recipeName, "")
        recipeItem = shared?.getString(Constants.recipeItem, "")
        recipeMethod = shared?.getString(Constants.recipeMethod, "")

        //json parsing
        jsonFileString = Utils.getJsonDataFromAsset(applicationContext, "recipes.json")
        Log.i("data", jsonFileString!!)
        val gson = Gson()
        val listPersonType = object : TypeToken<List<RecipeModel>>() {}.type
        val persons : List<RecipeModel> = gson.fromJson(jsonFileString, listPersonType)

        binding.llBack.setOnClickListener {
            onBackPressed()
        }

        binding.tvRecipeName.text = recipeName
        binding.tvRecipeItem.text = recipeItem
        binding.tvRecipeMethod.text = recipeMethod

        binding.llShare.setOnClickListener {
            Utils.shareEmailApp(this@RecipeDetailActivity)
        }

        Collections.shuffle(persons)

        mAdapter = RecipesListHorizontalAdapter(persons, this@RecipeDetailActivity)

        val mLayoutManager : RecyclerView.LayoutManager =
            LinearLayoutManager(this@RecipeDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecipesList.layoutManager = mLayoutManager
        binding.rvRecipesList.itemAnimator = DefaultItemAnimator()
        binding.rvRecipesList.adapter = mAdapter
    }

    override fun onBackPressed() {
        shared?.let { Utils.preferenceClearAll(it) }
        finish()
    }
}