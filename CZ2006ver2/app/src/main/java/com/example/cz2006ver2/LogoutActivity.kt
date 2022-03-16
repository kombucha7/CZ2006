package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_logout.*
import kotlinx.android.synthetic.main.activity_your_code_page.*

/**
 * This class allows users to safely logout of their account.
 */
class LogoutActivity : AppCompatActivity() {
    /**
     * Method used to logout after saving any changes made.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        logout_BackButton.setOnClickListener {
            val logoutBack = Intent(this, AccountMainActivity::class.java)
            startActivity(logoutBack)
        }
        logout_ConfirmButton.setOnClickListener{
            val logoutConfirm = Intent(this, MainActivity::class.java)
            startActivity(logoutConfirm)
        }

    }

}