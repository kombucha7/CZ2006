package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_account_main.*
import kotlinx.android.synthetic.main.activity_calendar_main.*

/**
 * This class allows user to View Profile, View Group Details, Create Group and Logout
 */

class AccountMainActivity : AppCompatActivity() {
    /**
     * Method allows users to View Profile, View Group Details, Create Group and Logout
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_main)

        //nav bar
        homeicon_page3.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }
        calendaricon_page3.setOnClickListener{
            val intent = Intent(this, CalendarMainActivity::class.java)
            startActivity(intent)
        }
        transporticon_page3.setOnClickListener{
            val intent = Intent(this, trans1::class.java)
            startActivity(intent)
        }
        accounticon_page3.setOnClickListener{
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }
        view_profile_btn.setOnClickListener{
            val intent = Intent(this, ViewProfileActivity::class.java)
            startActivity(intent)
        }
        grp_details_btn.setOnClickListener{
            val intent = Intent(this, GroupDetailsActivity::class.java)
            startActivity(intent)
        }
        logout_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        create_grp_btn.setOnClickListener{
            val intent = Intent(this, YourCodePageActivity::class.java)
            startActivity(intent)
        }

    }

}