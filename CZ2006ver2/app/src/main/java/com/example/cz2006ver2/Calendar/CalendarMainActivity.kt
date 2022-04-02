package com.example.cz2006ver2.Calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar_main.*
import java.util.*
import com.example.cz2006ver2.Account.AccountMainActivity
import com.example.cz2006ver2.HomePage.HomePage1
import com.example.cz2006ver2.R
import com.example.cz2006ver2.Transport.trans1

/**
 * This class allows users to view the calendar along with which caretakers are on duty for certain dates. User can click on every date for more information.
 */
class CalendarMainActivity : AppCompatActivity() {

//    private lateinit var calendarView: CalendarView
//    private lateinit var myDate: TextView
    /**
     * Method used to display an interactive calendar. Users choose a particular date to see more details
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)
        val elderUID = intent.getStringExtra("key")

        //nav bar
        homeicon_page2.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)
        }
        calendaricon_page2.setOnClickListener{
            val intent = Intent(this, CalendarMainActivity::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)
        }
        transporticon_page2.setOnClickListener{
            val intent = Intent(this, trans1::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)
        }
        accounticon_page2.setOnClickListener{
            val intent = Intent(this, AccountMainActivity::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)
        }

        // calendar
        getDate()

    }
    /**
     * Method used to transition to the CalendarDayActivity page for any particular date that user chose.
     */
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