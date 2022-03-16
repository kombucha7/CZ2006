package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page5.*

/**
 * Class for HomePage5
 * Page to display task deletion success message
 */
class HomePage5 : AppCompatActivity() {

    /**
     * Main Function for HomePage5
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page5)

        home5_confirmbutton.setOnClickListener {
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }
    }
}