package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home_page1.*
import kotlinx.android.synthetic.main.activity_login.*

class CalendarDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        //back button to the calendar main page
        back_button_word.setOnClickListener{
            val calDayBackBtn = Intent(this, CalendarMainActivity::class.java)
            startActivity(calDayBackBtn)
        }
    }
}