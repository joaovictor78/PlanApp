package com.example.planapp.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import com.example.planapp.R


class CustomInputDialog(context: Context, layoutInflater: LayoutInflater, id: Int, title: String, listInput: MutableList<CustomInputData> = mutableListOf()) {
    init {
       val viewAlertDialog: View = layoutInflater.inflate(R.layout.input_dialog_new_employee, null)
       val linearLayout =  viewAlertDialog.findViewById<LinearLayout>(id)
       val titleView = viewAlertDialog.findViewById<TextView>(R.id.dialog_title)
       titleView.setText(title)
       var listEditText: MutableList<EditText> = mutableListOf()
       listInput.forEach {
           val spacing: TextView = TextView(context)
           spacing.textSize = 4f
           val editText: EditText = EditText(context)
            editText.setCompoundDrawablesWithIntrinsicBounds(it.id_icon, 0, 0, 0)
            editText.setPadding(20, 20, 0 , 20)
            editText.setCompoundDrawablePadding(10)
            editText.setBackgroundResource(R.drawable.custom_input)
            editText.setHint(it.title)
            editText.setHintTextColor(Color.parseColor("#838383"))
            listEditText.add(editText)
            linearLayout?.addView(editText)
            linearLayout?.addView(spacing)

        }

        getAlertDialogConfig(context, viewAlertDialog)

    }

    private fun getAlertDialogConfig(context: Context, viewAlertDialog: View){
        val alert: AlertDialog.Builder
        var isPositive: Boolean? = null
        val addButton = viewAlertDialog.findViewById<Button>(R.id.add_dialog)
        val cancelButton = viewAlertDialog.findViewById<Button>(R.id.cancel_dialog)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alert = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            alert = AlertDialog.Builder(context)
        }
        alert.setView(viewAlertDialog)
        alert.setCancelable(false)
        val dialog: AlertDialog = alert.create()
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }
}


