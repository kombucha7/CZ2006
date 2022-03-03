package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_login.*

class GroupselectionActivity : AppCompatActivity() {

    data class careRecipientInfo(        //data that we are passing in, add on to userInfo if want to ask for more
        val cr1: String? = null,
        val cr2: String? = null,
        val cr3: String? = null,
        val cr4: String? = null,
        val cr5: String? = null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupselection)

        val careRecipient_Info : careRecipientInfo

        getFirestore()

        //back button to the landing page
        back_button_word.setOnClickListener{
            val groupSelectBackBtn = Intent(this, LoginActivity::class.java)
            startActivity(groupSelectBackBtn)
        }
    }


    fun getFirestore(){
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser    //getting the user id value
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("careRecipient").document("testID")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                 Log.d(TAG, "Our data: ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

}