package com.example.planapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.planapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val enterButton: Button = findViewById(R.id.login_button)
        val clickableText: TextView = findViewById(R.id.text_clickable_login)
        enterButton.setOnClickListener {
            val intent: Intent = Intent(this, MainHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        clickableText.setOnClickListener {
            val intent: Intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}