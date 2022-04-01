package com.example.cz2006ver2.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cz2006ver2.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.back_button_word
import kotlinx.android.synthetic.main.activity_view_profile.*

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

        change_pw_btn.setOnClickListener{
            val viewProfileChangeBtn = Intent(this, ChangePasswordActivity::class.java)
            startActivity(viewProfileChangeBtn)
        }
    }
}