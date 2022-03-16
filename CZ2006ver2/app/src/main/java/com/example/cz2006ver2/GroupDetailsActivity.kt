package com.example.cz2006ver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * This class allows users to display the care recipient's group details, caretakers and group code. The user can switch to another care recipient
 */
class GroupDetailsActivity : AppCompatActivity() {
    /**
     * Method used to start default activity. Link back to main Transport Page.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)
    }
}