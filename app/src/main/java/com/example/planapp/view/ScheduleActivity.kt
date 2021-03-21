package com.example.planapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.planapp.R
import com.example.planapp.utils.CustomInputData
import com.example.planapp.utils.CustomInputDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleActivity : AppCompatActivity() {
    val myEvents: List<EventDay> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        val addNewAppoiment: FloatingActionButton = findViewById(R.id.fab_schedule)
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        addNewAppoiment.setOnClickListener {
            val intent: Intent = Intent(this, AddAppointmentActivity::class.java)
            startActivity(intent)
        }

        calendarView.setOnDayClickListener {
          view ->  Log.i("iahda", view.calendar.toString())
            val intent: Intent = Intent(this, ScheduledAppoimentsActivity::class.java)
            startActivity(intent)
        }
    }
}