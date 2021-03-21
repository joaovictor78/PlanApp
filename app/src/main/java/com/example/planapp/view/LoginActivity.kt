package com.example.planapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.planapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val enterButton: Button = findViewById(R.id.login_button)
        val clickableText: TextView = findViewById(R.id.text_clickable_login)
        val backButton: LinearLayout = findViewById(R.id.back_button)
        enterButton.setOnClickListener {
            val intent: Intent = Intent(this, MainHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        clickableText.setOnClickListener {
            val intent: Intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            Log.i("ada", "asa")
            finish()
        }
    }
}