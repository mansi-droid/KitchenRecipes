package com.dev.kitchenrecipes

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.dev.kitchenrecipes.data.model.RecipeModel
import java.io.IOException

class Utils {

    companion object {
        fun getJsonDataFromAsset(context : Context, fileName : String) : String? {
            val jsonString : String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException : IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun showToast(message : String?, context : Activity?) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        }

        fun contactUs(ctx : Context) {
            val mailTo =
                "mailto:" + Constants.email + "?&subject=" + Uri.encode("Contact Us")
            val emailIntent = Intent(Intent.ACTION_VIEW)
            emailIntent.data = Uri.parse(mailTo)
            ctx.startActivity(emailIntent)
        }

        fun shareEmailApp(ctx : Context) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                "મેં આ વાનગીઓનો આનંદ માણ્યો, તમને પણ તે ગમશે!\uD83D\uDE0A\uD83D\uDE0A \n\nવધુ રસપ્રદ વાનગીઓ શોધો:\n" +
                        "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
            sendIntent.type = "text/plain"
            ctx.startActivity(sendIntent)

        }

        fun playStoreOpen(ctx : Context, packageName : String) {
            try {
                ctx.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName"))
                )
            } catch (a : ActivityNotFoundException) {
                ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://play.google.com/store/apps/details?id=$packageName"))
                )
            }
        }

        fun preferenceStore(ctx : Context, listModel : List<RecipeModel>, position : Int) {
            val sharedPreference =
                ctx.getSharedPreferences(Constants.recipeData, Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(Constants.recipeName, listModel[position].recipe_name)
            editor.putString(Constants.recipeItem, listModel[position].recipe_item)
            editor.putString(Constants.recipeMethod, listModel[position].recipe_method)
            editor.apply()
        }

        fun preferenceClearAll(shared : SharedPreferences) {
            val edit = shared?.edit()
            edit?.clear()
        }
    }
}