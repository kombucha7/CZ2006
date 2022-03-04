package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page4.*
import kotlinx.android.synthetic.main.activity_home_page6.*

class HomePage6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page6)

        home6_confirmbutton.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }

        home6_back_button_word.setOnClickListener {
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }
    }
}