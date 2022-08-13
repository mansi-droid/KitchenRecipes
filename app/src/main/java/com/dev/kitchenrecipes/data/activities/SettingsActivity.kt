package com.dev.kitchenrecipes.data.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.kitchenrecipes.Constants
import com.dev.kitchenrecipes.R
import com.dev.kitchenrecipes.data.adapter.SettingsListAdapter
import com.dev.kitchenrecipes.data.model.SettingsModel
import com.dev.kitchenrecipes.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingsBinding
    private var settingsAdapter : SettingsListAdapter? = null
    private var listModel : Array<SettingsModel> = arrayOf(
        SettingsModel(Constants.share_our_app),
        SettingsModel(Constants.contact_us),
        SettingsModel(Constants.rate_this_app),
        SettingsModel(Constants.version)
    )

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        binding.llBack.setOnClickListener {
            onBackPressed()
        }

        val mLayoutManager1 : RecyclerView.LayoutManager =
            LinearLayoutManager(this@SettingsActivity)
        settingsAdapter = SettingsListAdapter(listModel, packageName, this@SettingsActivity)
        binding.rvList.layoutManager = mLayoutManager1
        binding.rvList.itemAnimator = DefaultItemAnimator()
        binding.rvList.adapter = settingsAdapter
    }
}