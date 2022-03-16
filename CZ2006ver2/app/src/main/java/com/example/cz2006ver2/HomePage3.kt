package com.example.cz2006ver2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home_page3.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

/**
 * Class for HomePage3
 *
 */
class HomePage3 : AppCompatActivity() {

    /**
     * Main function for HomePage3 Logic
     * Page to display and confirm inputs from HomePage2
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page3)
        val elderUID = intent.getStringExtra("key").toString()//get uid from other page

        Log.d(ContentValues.TAG,"home3" + elderUID)

        home3_returnbutton.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            intent.putExtra("key", elderUID) //return back to first page again???
            startActivity(intent)
        }

        displaytext()


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

        val tvdesc: TextView = findViewById(R.id.home3_user_desc_text)
        val tvtime: TextView = findViewById(R.id.home3_user_time_text)
        val tvdate: TextView = findViewById(R.id.home3_user_date_text)
        val tvspin: TextView = findViewById(R.id.home3_user_reccuring_text)

        tvdesc.text = desc
        tvtime.text = time
        tvdate.text = date
        tvspin.text = spin
    }
}