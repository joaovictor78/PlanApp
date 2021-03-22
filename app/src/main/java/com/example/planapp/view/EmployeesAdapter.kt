package com.example.planapp.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.util.isEmpty
import androidx.core.util.isNotEmpty
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import com.example.planapp.models.EmployeesModel


class EmployeesAdapter(private var brokers: MutableList<EmployeesModel> = mutableListOf()): RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {
    val selectedItens: SparseBooleanArray = SparseBooleanArray()
    private var curredSelectPosition: Int = -1
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
        holder.itemView.setOnClickListener {
            if(selectedItens.isNotEmpty()){
               onItemClick?.invoke(position)
            }
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(position)
            return@setOnLongClickListener true
        }
        if(curredSelectPosition == position) curredSelectPosition = -1
    }
    override fun getItemCount(): Int {
        return brokers.size
    }

    fun toggleSelection(position: Int) {
        curredSelectPosition = position
        if(selectedItens[position, false]){
            selectedItens.delete(position)
            brokers[position].selected = false
        }else{
            selectedItens.put(position, true)
            brokers[position].selected = true
        }
        notifyItemChanged(position)
    }

    var onItemClick: ((Int) ->  Unit)? = null
    var onItemLongClick: ((Int) -> Unit)? = null
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
            if(broker.selected == true){
                itemView.background = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 32f
                    setColor(Color.CYAN)
                }
            }
        }
    }
}

