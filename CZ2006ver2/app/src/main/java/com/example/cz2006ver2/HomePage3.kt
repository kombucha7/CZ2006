package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page3.*
import kotlinx.android.synthetic.main.activity_main.*

class HomePage3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page3)

        home3_returnbutton.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }
    }


}