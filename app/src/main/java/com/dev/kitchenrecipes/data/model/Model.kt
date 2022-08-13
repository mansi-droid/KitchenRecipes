package com.dev.kitchenrecipes.data.model

data class RecipeModel(
    val recipe_id : String,
    val recipe_name : String,
    val recipe_item : String,
    val recipe_method : String,
    val images : String,
    val fav : String,
    val ingredient : String
)

data class SettingsModel(val title:String)