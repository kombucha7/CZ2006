package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_logout.*

/**
 * This activity allows users to change their passwords.
 */
class ChangePasswordActivity : AppCompatActivity() {
    /**
     * Method used to start default activity
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        logout_BackButton.setOnClickListener {
            val passwordChange = Intent(this, AccountMainActivity::class.java)
            startActivity(passwordChange)
        }

    }
}