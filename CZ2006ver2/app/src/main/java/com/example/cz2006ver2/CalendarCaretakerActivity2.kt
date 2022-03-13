package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar_caretaker2.*
import kotlinx.android.synthetic.main.activity_home_page5.*

class CalendarCaretakerActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_caretaker2)

        calpage_caretaker2_btn.setOnClickListener {
            val intent = Intent(this, CalendarDayActivity::class.java)
            startActivity(intent)
        }
    }
}