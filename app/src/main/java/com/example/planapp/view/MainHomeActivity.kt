package com.example.planapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.planapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)
        val fab = findViewById<FloatingActionButton>(R.id.fab_calendar)
        fab.setOnClickListener {
            val intent = Intent(applicationContext, ScheduleActivity::class.java)
            startActivity(intent)
        }
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNav.menu.getItem(1).isEnabled = false
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        bottomNav.background = null
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    FragmentHome()
            ).commit()
        }
    }

    private val navListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                var selectedFragment: Fragment? = null
                when (item.itemId) {
                    R.id.home -> selectedFragment = FragmentHome()
                    R.id.employees -> selectedFragment = FragmentEmployees()
                }
                supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        selectedFragment!!
                ).commit()
                true
            }
}