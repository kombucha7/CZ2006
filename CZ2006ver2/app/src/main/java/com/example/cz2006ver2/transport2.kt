package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_trans1.*
import kotlinx.android.synthetic.main.activity_transport2.*

class transport2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport2)
        back_btn_cal2.setOnClickListener {
            val back1 = Intent(this, trans1::class.java)
            startActivity(back1)
        }
        back_word_cal2.setOnClickListener {
            val back2 = Intent(this, trans1::class.java)
            startActivity(back2)
        }
    }
}