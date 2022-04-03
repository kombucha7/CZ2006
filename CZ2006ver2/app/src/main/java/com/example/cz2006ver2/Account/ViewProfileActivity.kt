package com.example.cz2006ver2.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.back_button_word
import kotlinx.android.synthetic.main.activity_view_profile.*

/**
 * This page allows users to check their profile details
 */
class ViewProfileActivity : AppCompatActivity() {
    /**
     * Default method used to go to view profile details
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        //set the user's name and email to show
        val currentFirebaseUser =
            FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    user_name.text = document.get("name").toString()
                    user_email.text = document.get("email").toString()
                    Log.d(TAG, "Our data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }



        //back button to the landing page
        back_button_word.setOnClickListener{
            val viewProfileBackBtn = Intent(this, AccountMainActivity::class.java)
            startActivity(viewProfileBackBtn)
        }

        change_pw_btn.setOnClickListener{
            val viewProfileChangeBtn = Intent(this, ChangePasswordActivity::class.java)
            startActivity(viewProfileChangeBtn)
        }
    }
}