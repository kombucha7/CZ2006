package com.example.cz2006ver2.Account

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cz2006ver2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_calendar_day.*
import kotlinx.android.synthetic.main.activity_group_details.*

/**
 * This class allows users to display the care recipient's group details, caretakers and group code. The user can switch to another care recipient
 */
class GroupDetailsActivity : AppCompatActivity() {
    /**
     * Method used to start default activity
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        val elderUID = intent.getStringExtra("key").toString()
        findCaretakerName(elderUID)
        groupDetails_GroupCode.text = elderUID.toString()

        // back btn
        groupDetails_BackText.setOnClickListener {
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }
        // switch groups btn
        groupDetails_EditProfileButton.setOnClickListener {
            val intent = Intent(this, SwitchGroupActivity::class.java)
            startActivity(intent)
        }
    }


    fun findCaretakerName(elderUID: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("careRecipient").document(elderUID)
            .get()
            .addOnSuccessListener { documents ->
                println(documents.get("name").toString() + " THIS IS THE NAME")
                groupDetails_CareRecipientName.text = documents.get("name").toString()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}