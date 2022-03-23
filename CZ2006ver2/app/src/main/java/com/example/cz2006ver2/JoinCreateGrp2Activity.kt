package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_join_create_grp.*
import kotlinx.android.synthetic.main.activity_join_create_grp2.*

class JoinCreateGrp2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_create_grp2)

        // back btn
        join_create_grp2_BackText.setOnClickListener {
            val intent = Intent(this, JoinCreateGrpActivity::class.java)
            startActivity(intent)
        }
        // enter btn
//        join_create_grp2_enter.setOnClickListener{
//            val intent = Intent(this, ::class.java)
//            startActivity(intent)
//        }
    }
}