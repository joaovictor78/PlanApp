package com.example.planapp.controllers

import com.example.planapp.models.BrokerData

class HomeController {
    fun getBrokers(): MutableList<BrokerData>{
        val list : MutableList<BrokerData> = arrayListOf()
        list.add(BrokerData("uniron"))
        list.add(BrokerData("bradesco"))
        return list
    }

}