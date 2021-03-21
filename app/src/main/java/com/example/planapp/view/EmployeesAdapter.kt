package com.example.planapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import com.example.planapp.models.EmployeesModel


class EmployeesAdapter(private var brokers: MutableList<EmployeesModel> = mutableListOf()): RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_employees,
            parent,
            false
        )
        return EmployeesViewHolder(view)
    }
    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        holder.bind(brokers[position])
    }
    override fun getItemCount(): Int {
        return brokers.size
    }
    class EmployeesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView? = null
        var phone: TextView? = null
        var email: TextView? = null
        init {
            name = itemView.findViewById<EditText>(R.id.employees_name)
            phone = itemView.findViewById<EditText>(R.id.employees_phone)
            email = itemView.findViewById<EditText>(R.id.employees_email)
        }

        fun bind(broker: EmployeesModel){
            name?.setText(broker.name)
            phone?.setText(broker.phone)
            email?.setText(broker.email)
        }
    }
}

