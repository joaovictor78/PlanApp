package com.example.planapp.controller
import com.example.planapp.models.EmployeesModel

class EmployeesController {
    fun getEmployees(): MutableList<EmployeesModel>{
        val list : MutableList<EmployeesModel> = arrayListOf()
        list.add(EmployeesModel("joaozin", "0000000", "joaozin@gmail.com"))
        list.add(EmployeesModel("joaozin2", "0000000111", "joaozin1111@gmail.com"))
        return list
    }
}