package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar_caretaker.*
import kotlinx.android.synthetic.main.activity_calendar_day.*

/**
 * This class allows for swapping of caretakers for a particular day
 */
class CalendarCaretakerActivity : AppCompatActivity() {
    /**
     * Method used to change caretakers.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_caretaker)

        var curr_date = intent.getStringExtra("scheduled_date")
        schedule_date1.setText(curr_date)

        //back button to the calendar day page
        back_word_cal2.setOnClickListener{
            val cal2BackBtn = Intent(this, CalendarDayActivity::class.java)
            cal2BackBtn.putExtra("scheduled_date",curr_date)
            startActivity(cal2BackBtn)
        }
        // confirm btn
        cal_caretaker_changebtn.setOnClickListener{
            val calConfirmBtn = Intent(this, CalendarCaretakerActivity2::class.java)
            calConfirmBtn.putExtra("scheduled_date",curr_date)
            startActivity(calConfirmBtn)
        }
    }
}