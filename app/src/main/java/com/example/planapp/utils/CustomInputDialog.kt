package com.example.planapp.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import com.example.planapp.R


class CustomInputDialog() {
    fun getDialogAttributes(context: Context, layoutInflater: LayoutInflater, id: Int, title: String, listInput: MutableList<CustomInputData> = mutableListOf()): List<EditText>? {
        var isPositived: Boolean? = null
        val viewAlertDialog: View = layoutInflater.inflate(R.layout.input_dialog_new_employee, null)
        val linearLayout =  viewAlertDialog.findViewById<LinearLayout>(id)
        val titleView = viewAlertDialog.findViewById<TextView>(R.id.dialog_title)
        val buttonConfirm = viewAlertDialog.findViewById<Button>(R.id.add_dialog)
        val buttonCancell = viewAlertDialog.findViewById<Button>(R.id.cancel_dialog)
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

        var dialog = getAlertDialogConfig(context, viewAlertDialog)

        buttonConfirm.setOnClickListener {
            isPositived = true
            Log.i("brodinho",  listEditText[0].text.toString())
            dialog.dismiss()
        }
        buttonCancell.setOnClickListener {
            Log.i("brodinho", "tu cancelaste")
            dialog.dismiss()

        }
        if(isPositived == true){
            return  listEditText
        }
        return null
    }
    private fun getAlertDialogConfig(context: Context, viewAlertDialog: View): AlertDialog{
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
        return dialog
    }
}


