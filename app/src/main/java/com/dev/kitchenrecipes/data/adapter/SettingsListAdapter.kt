package com.dev.kitchenrecipes.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.BuildConfig
import com.dev.kitchenrecipes.Constants
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.Utils
import com.dev.kitchenrecipes.data.model.SettingsModel
import com.dev.kitchenrecipes.databinding.SettingsItemLayoutBinding

class SettingsListAdapter(
    private val listModel : Array<SettingsModel>, private val packageName : String,
    private val ctx : Context
) : RecyclerView.Adapter<SettingsListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val v : SettingsItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.settings_item_layout, parent,
                false
            )
        return MyViewHolder(v)
    }

    inner class MyViewHolder(var binding : SettingsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("StringFormatMatches")
    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.binding.tvTitle.text = listModel[position].title

        if (listModel[position].title == Constants.version) {
            holder.binding.tvSubTitle.visibility = View.VISIBLE
            holder.binding.ivArrow.visibility = View.INVISIBLE
            holder.binding.tvSubTitle.text = "વરજંન ${BuildConfig.VERSION_NAME}"
        } else {
            holder.binding.tvSubTitle.visibility = View.GONE
        }

        holder.binding.llMainLayout.setOnClickListener {
            when (listModel[position].title) {
                Constants.share_our_app -> {
                    Utils.playStoreOpen(ctx, packageName)
                }
                Constants.rate_this_app -> {
                    Utils.playStoreOpen(ctx, packageName)
                }
                Constants.contact_us -> {
                    Utils.contactUs(ctx)
                }
            }
        }

    }

    override fun getItemCount() : Int {
        return listModel.size
    }
}
