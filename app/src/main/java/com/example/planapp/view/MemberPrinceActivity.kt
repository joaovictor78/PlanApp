package com.example.planapp.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.planapp.R
import com.example.planapp.utils.CustomInputData
import com.example.planapp.utils.CustomInputDialog

class MemberPrinceActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_prince)
        val calculate_total = findViewById<Button>(R.id.calculate_total)
        val addNewMember = findViewById<Button>(R.id.new_member)
        val inflater: LayoutInflater = layoutInflater
        val listInput: MutableList<CustomInputData> = mutableListOf(
            CustomInputData("Informe o nome", R.drawable.ic_people),
            CustomInputData("Informe o telefone", R.drawable.ic_phone), CustomInputData("Informe o email", R.drawable.ic_email)
        )

        addNewMember.setOnClickListener {
            Log.i("toquei", "uhuulll")
            val customInput = CustomInputDialog()
            customInput.getDialogAttributes(this, inflater, R.id.input, "Novo membro",   listInput)
        }
        calculate_total.setOnClickListener {
            val intent: Intent = Intent(this, TotalQuotationActivity::class.java)
            startActivity(intent)
        }

    }

}