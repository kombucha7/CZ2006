package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_login.*

class GroupselectionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupselection)


        getFirestore()
        //back button to the landing page
        back_button_word.setOnClickListener{
            val groupSelectBackBtn = Intent(this, LoginActivity::class.java)
            startActivity(groupSelectBackBtn)
        }
    }


    fun getFirestore(){
        val cr1 = findViewById<TextView>(R.id.gs_group1)
        val cr2 = findViewById<TextView>(R.id.gs_group2)
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser    //getting the user id value
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("careRecipient").document("testID")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                 //Log.d(TAG, "Our data: ${document.data}")
                    cr1.text = document.getString("careRecipient1")
                    cr2.text = document.getString("careRecipient2")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

}