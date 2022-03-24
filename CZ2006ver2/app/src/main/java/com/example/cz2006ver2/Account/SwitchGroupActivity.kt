package com.example.cz2006ver2.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cz2006ver2.R
import kotlinx.android.synthetic.main.activity_switch_group.*

/**
 * This class allows for change of care recipient of the user
 */
class SwitchGroupActivity : AppCompatActivity() {
    /**
     * Method used to start default activity. Link back to main Transport Page.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_group)

        // back btn
        switchGroup_BackText.setOnClickListener {
            val intent = Intent(this, GroupDetailsActivity::class.java)
            startActivity(intent)
        }
        switchGroup_ConfirmButton.setOnClickListener {
            val intent = Intent(this, GroupDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}