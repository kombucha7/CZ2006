package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

/**
 * This page allows users to check their profile details
 */
class ViewProfileActivity : AppCompatActivity() {
    /**
     * Default method used to go to view profile details
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        //back button to the landing page
        back_button_word.setOnClickListener{
            val viewProfileBackBtn = Intent(this, AccountMainActivity::class.java)
            startActivity(viewProfileBackBtn)
        }
    }
}