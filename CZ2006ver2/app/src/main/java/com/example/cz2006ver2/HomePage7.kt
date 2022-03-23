package com.example.cz2006ver2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home_page7.*

class HomePage7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page7)
        val elderUID = intent.getStringExtra("key").toString()//get uid from other page

        Log.d(ContentValues.TAG,"home7" + elderUID)
        displaytext()

        home7_returnbutton.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            intent.putExtra("key", elderUID) //return back to first page again???
            startActivity(intent)
        }
    }


    /**
     * Function to display text added to intent from HomePage2
     * Text strings will be updated in respective TextViews
     *
     */
    fun displaytext()
    {
        val desc = intent.getStringExtra("desc")
        val time = intent.getStringExtra("time")
        val date = intent.getStringExtra("date")
        val spin = intent.getStringExtra("spin")

        val tvdesc: TextView = findViewById(R.id.home7_user_desc_text)
        val tvtime: TextView = findViewById(R.id.home7_user_time_text)
        val tvdate: TextView = findViewById(R.id.home7_user_date_text)
        val tvspin: TextView = findViewById(R.id.home7_user_reccuring_text)

        tvdesc.text = desc
        tvtime.text = time
        tvdate.text = date
        tvspin.text = spin
    }
}