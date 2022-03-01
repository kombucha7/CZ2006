package com.example.cz2006ver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_test1.*

class testActivity1 : AppCompatActivity() {

    private lateinit var testUserName: EditText
    private lateinit var testUserPhoneNum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        testSubmitInfo.setOnClickListener{
            testUserName = findViewById(R.id.testEnterName)     //this is an edittext object?? need to convert to string first
            testUserPhoneNum = findViewById(R.id.testEnterPhoneNum)

            val name = testUserName.toString()
            val num = testUserName.toString()
            saveFireStore(name,num)
        }
    }

    fun saveFireStore(name: String, num: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = name
        user["number"] = num

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@testActivity1, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@testActivity1, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }

}