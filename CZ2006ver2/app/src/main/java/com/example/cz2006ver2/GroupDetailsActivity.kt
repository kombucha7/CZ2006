package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group_details.*
import kotlinx.android.synthetic.main.activity_join_create_grp2.*

/**
 * This class allows users to display the care recipient's group details, caretakers and group code. The user can switch to another care recipient
 */
class GroupDetailsActivity : AppCompatActivity() {
    /**
     * Method used to start default activity
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        // back btn
        groupDetails_BackText.setOnClickListener {
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }
        // switch groups btn
        groupDetails_EditProfileButton.setOnClickListener {
            val intent = Intent(this, SwitchGroupActivity::class.java)
            startActivity(intent)
        }
    }
}