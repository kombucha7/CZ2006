package com.example.cz2006ver2.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cz2006ver2.R
import kotlinx.android.synthetic.main.activity_transport3.*

/**
 * Class that is used to display information from the Search Page.
 */
class transport3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Method used to start default activity. Link back to Search Page.
         * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport3)
        back_btn_cal2.setOnClickListener {
            val back1 = Intent(this, trans1::class.java)
            startActivity(back1)
        }
        back_word_cal2.setOnClickListener {
            val back2 = Intent(this, trans1::class.java)
            startActivity(back2)
        }
    }
}