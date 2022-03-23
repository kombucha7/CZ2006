package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_connect_page.*
import kotlinx.android.synthetic.main.activity_home_page2.*
import kotlinx.android.synthetic.main.activity_join_create_grp.*

class JoinCreateGrpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_create_grp)

        // back btn
        join_create_grp_BackText.setOnClickListener {
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }
        // create code btn
        join_createCode.setOnClickListener{
            val intent = Intent(this, JoinCreateGrp2Activity::class.java)
            startActivity(intent)
        }
    }
}