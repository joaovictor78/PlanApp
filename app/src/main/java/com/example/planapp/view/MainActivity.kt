package com.example.planapp.view

import android.R.id.text2
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.planapp.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        val enterButton: Button = findViewById<Button>(R.id.enter_button)
        val registerButton: Button = findViewById<Button>(R.id.register_button)
        val sloganMain: TextView = findViewById<TextView>(R.id.slogan_main)

        val sloganText: String = "O melhor app\n para corretores de saúde !"
        val spannable: Spannable = SpannableString(sloganText)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 1, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sloganMain.setText(spannable)

        enterButton.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}