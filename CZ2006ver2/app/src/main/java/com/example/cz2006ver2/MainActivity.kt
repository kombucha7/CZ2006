package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Class for main landing page of App
 *
 */
class MainActivity : AppCompatActivity() {

    /**
     * Main Function for App
     * Includes a button to move to Register Page
     * And another button to move to Login Page
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginbutton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        regbutton.setOnClickListener{
            val regIntent = Intent(this, RegisterActivity::class.java)
            startActivity(regIntent)
        }
    }

    /**
     * a function to test inherited functions
     */
    fun afunction(input: String){

    }
}