package com.example.cz2006ver2.HomePage

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cz2006ver2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home_page2.*
import java.util.*

/**
 * Class for homepage2
 *
 */
class HomePage2 : AppCompatActivity() {

    /**
     * Class that represents a Task for the app
     *
     * @property datetimeTask date and time of the Task
     * @property description Description of the task
     * @property name Name of the task
     */
    data class taskInfo(
        val datetimeTask: String,
        val description: String,
        val name: String
    )

    /**
     * Main class for HomePage2
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page2)
        val elderUID = intent.getStringExtra("key").toString()      //pass UID to this page
        Log.d(TAG,"home page 2 " + elderUID)


        home2_create_button.setOnClickListener {
            val intent = Intent(this, HomePage3::class.java)
            val descview = findViewById<EditText>(R.id.home2_desc_edit)
            val timeview = findViewById<TextView>(R.id.home2_time_text)
            val dateview = findViewById<TextView>(R.id.home2_date_text)
            intent.putExtra("desc", descview.text.toString())
            intent.putExtra("time", timeview.text)
            intent.putExtra("date", dateview.text)

            //adding data to database//
            val uniqueID = UUID.randomUUID().toString()
            val db = FirebaseFirestore.getInstance()
            val taskUpload = taskInfo(dateview.text.toString() + timeview.text.toString(), descview.text.toString(), descview.text.toString())
            db.collection("careRecipient").document(elderUID).collection("task").document(uniqueID).set(taskUpload)


            intent.putExtra("key", elderUID)   //pass uid to next page
            startActivity(intent)

        }

        home2_back_button_word.setOnClickListener {
            val intent = Intent(this, HomePage1::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)

        }
        timeclick()
        dateclick()
    }

    /**
     * Function to show TimePickerDialog to select time for task creation
     * Time will be added to text view in Hour:Minute format
     *
     */
    private fun timeclick()
    {
        var picker: TimePickerDialog
        val tvw: TextView = findViewById<View>(R.id.home2_time_text) as TextView
        tvw.setOnClickListener(View.OnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cldr.get(Calendar.MINUTE)
            // time picker dialog
            picker = TimePickerDialog(this@HomePage2,
                { tp, sHour, sMinute -> tvw.text = "$sHour:$sMinute" }, hour, minutes, true
            )
            picker.show()
            tvw.text = "Selected Time: " + tvw.text
        })
    }

    /**
     * Function to show DatePickerDialog
     * returns Year, Month and Day as separate values
     *
     */
    private fun dateclick()
    {
        val tvw = findViewById<TextView>(R.id.home2_date_text)
        tvw.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val yy = calendar[Calendar.YEAR]
            val mm = calendar[Calendar.MONTH]
            val dd = calendar[Calendar.DAY_OF_MONTH]

            val datePicker = DatePickerDialog(this@HomePage2,
                { _, year, monthOfYear, dayOfMonth ->
                    val date = "$year-$monthOfYear-$dayOfMonth"
                    tvw.text = date
                }, yy, mm, dd
            )
            datePicker.datePicker.minDate = calendar.timeInMillis

            datePicker.show()  })

    }

}