package com.example.cz2006ver2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home_page2.*
import java.util.*


class HomePage2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page2)

        home2_login_button.setOnClickListener {
            val intent = Intent(this, HomePage3::class.java)
            startActivity(intent)
        }

        home2_back_button_word.setOnClickListener {
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }

        spinclick()
        timeclick()
    }

    fun spinclick()
    {
        val languages = resources.getStringArray(R.array.yes_no)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.home2_event_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@HomePage2,
                        getString(R.string.select) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    fun timeclick()
    {
        var picker: TimePickerDialog
        val tvw: TextView
        tvw = findViewById<View>(R.id.home2_time_text) as TextView
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
/*
    fun dateclick()
    {
        var picker : DatePickerDialog
        val tvw: TextView
        tvw = findViewById(R.id.home2_date_text) as TextView
        tvw.setOnClickListener(View.OnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day:
        })
    }

 */
}