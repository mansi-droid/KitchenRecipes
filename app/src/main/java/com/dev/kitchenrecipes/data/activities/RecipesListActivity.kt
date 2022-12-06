package com.dev.kitchenrecipes.data.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.Utils
import com.dev.kitchenrecipes.Utils.Companion.getJsonDataFromAsset
import com.dev.kitchenrecipes.data.adapter.RecipesListAdapter
import com.dev.kitchenrecipes.data.model.RecipeModel
import com.dev.kitchenrecipes.databinding.ActivityRecipesListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesListActivity : AppCompatActivity() {
    lateinit var binding : ActivityRecipesListBinding
    private var mAdapter : RecipesListAdapter? = null
    private var jsonFileString : String? = null
    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes_list)

        //json parsing
        jsonFileString = getJsonDataFromAsset(applicationContext, "recipes.json")
        Log.i("data", jsonFileString!!)
        val gson = Gson()
        val listPersonType = object : TypeToken<List<RecipeModel>>() {}.type
        val persons : List<RecipeModel> = gson.fromJson(jsonFileString, listPersonType)

        binding.llBack.setOnClickListener {
            onBackPressed()
        }

        mAdapter = RecipesListAdapter(persons, this@RecipesListActivity)

        val mLayoutManager : RecyclerView.LayoutManager =
            LinearLayoutManager(this@RecipesListActivity)

        binding.rvRecipesList.layoutManager = mLayoutManager
        binding.rvRecipesList.itemAnimator = DefaultItemAnimator()
        binding.rvRecipesList.adapter = mAdapter

        binding.llMenu.setOnClickListener {
            val i = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
            return
        }
        doubleBackToExitPressedOnce = true
        Utils.showToast("Press again to exit", this@RecipesListActivity)
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false },
            2000)
    }
}