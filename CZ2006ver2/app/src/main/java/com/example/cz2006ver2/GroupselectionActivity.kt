package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class GroupselectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupselection)

        //back button to the landing page
        back_button_word.setOnClickListener{
            val groupSelectBackBtn = Intent(this, LoginActivity::class.java)
            startActivity(groupSelectBackBtn)
        }
    }
}