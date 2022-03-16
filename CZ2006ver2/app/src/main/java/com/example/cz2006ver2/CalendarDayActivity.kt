package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_calendar_day.*
import kotlinx.android.synthetic.main.activity_home_page1.*
import kotlinx.android.synthetic.main.activity_login.*

/**
 * This class displays the tasks and the caretaker in charge for the a certain day. It also provides options to change the caretaker and edit tasks.
 */
class CalendarDayActivity : AppCompatActivity() {
    /**
     * Method used to start default activity. Link back to main Transport Page.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_day)

        // calendar date
        var curr_date = intent.getStringExtra("scheduled_date")
        schedule_date.setText(curr_date)

        //back button to the calendar main page
        back_word_cal1.setOnClickListener{
            val calDayBackBtn = Intent(this, CalendarMainActivity::class.java)
            startActivity(calDayBackBtn)
        }
        // edit caretaker btn
        edit_caretaker_btn.setOnClickListener{
            val calEditCaretakerBtn = Intent(this, CalendarCaretakerActivity::class.java)
            calEditCaretakerBtn.putExtra("scheduled_date",curr_date)
            startActivity(calEditCaretakerBtn)
        }
        // edit task btn
        edit_tasks_btn.setOnClickListener{
            val calEditTaskBtn = Intent(this, CalendarTaskActivity::class.java)
            calEditTaskBtn.putExtra("scheduled_date",curr_date)
            startActivity(calEditTaskBtn)
        }

    }
}