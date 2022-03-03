package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_connect_page.*
import kotlinx.android.synthetic.main.activity_your_code_page.*

class YourCodePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_code_page)

        yourCodeNextButton.setOnClickListener{
            val backBtnIntent = Intent(this, GroupNamePageActivity::class.java)
            startActivity(backBtnIntent)
        }
    }
}