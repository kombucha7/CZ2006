package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_calendar_main.*
import android.app.DatePickerDialog
import java.util.*
import kotlinx.android.synthetic.main.activity_home_page1.*
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import java.text.DateFormat

class CalendarMainActivity : AppCompatActivity() {

//    private lateinit var calendarView: CalendarView
//    private lateinit var myDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)

        //nav bar
        homeicon_page2.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }
        calendaricon_page2.setOnClickListener{
            val intent = Intent(this, CalendarMainActivity::class.java)
            startActivity(intent)
        }
        transporticon_page2.setOnClickListener{
            val intent = Intent(this, trans1::class.java)
            startActivity(intent)
        }
        accounticon_page2.setOnClickListener{
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }

        // calendar
        getDate()

    }

    fun getDate() {
        val calendar1 = Calendar.getInstance()
        val year = calendar1.get(Calendar.YEAR)
        val month = calendar1.get(Calendar.MONTH)
        val day = calendar1.get(Calendar.DAY_OF_MONTH)

        var curr_date: String

        calendarView.setOnDateChangeListener { view, day, month, year ->
            calendar1.set(year,month,day)
            curr_date = "" + year.toString() + "/" + (month.toInt()+1).toString() + "/" + day.toString()

            val intent = Intent(this, CalendarDayActivity::class.java)
            intent.putExtra("scheduled_date",curr_date)
            startActivity(intent)
        }
    }
}