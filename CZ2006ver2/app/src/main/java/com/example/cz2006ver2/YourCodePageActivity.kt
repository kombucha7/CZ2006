package com.example.cz2006ver2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_connect_page.*
import kotlinx.android.synthetic.main.activity_your_code_page.*
import kotlin.random.Random

class YourCodePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_code_page)
        val TAG = "myLogTag"

        yourCodeNextButton.setOnClickListener{
            val elderKey = getRandomString(8)   //pass this to the next page to add to firestore as UID for elderly
            Log.d(TAG, "Key" + elderKey)    //for testing if match DB UID
            val backBtnIntent = Intent(this, GroupNamePageActivity::class.java)
            backBtnIntent.putExtra("key", elderKey)
            startActivity(backBtnIntent)
        }


    }

    fun getRandomString(length: Int): String? {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST1234567890-=!@#$%^&*()_+".toCharArray()
        val sb = StringBuilder()
        val random = Random
        for (i in 0 until length) {
            val c = chars[random.nextInt(chars.size)]
            sb.append(c)
        }
        return sb.toString()
    }
}