package com.example.planapp.repositories

import android.content.Context
import com.example.planapp.utils.Utils.Companion.getJsonDataFromAsset

class BrokersRepository(val applicationContext: Context): IBrokersRepository {
    override fun getBrokers(): String {
        val jsonFileString: String = getJsonDataFromAsset(applicationContext, "bezkoder.json").toString()
        return jsonFileString
    }
}