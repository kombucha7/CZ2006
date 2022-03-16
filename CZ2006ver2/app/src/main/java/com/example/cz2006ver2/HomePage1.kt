package com.example.cz2006ver2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home_page1.*

/**
 * Class for Homepage1
 *
 */
class HomePage1 : AppCompatActivity() {

    /**
     * main function for HomePage1
     * includes buttons for navigation bar
     * includes buttons for back button
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page1)

        val elderUID = intent.getStringExtra("key")
        val LOG = "testies "
        Log.d(LOG, "PENIS " + elderUID)

        displayUserName(home1introtext)
        displayTaskList(elderUID.toString())

        home1_addbutton.setOnClickListener{
            val intent = Intent(this, HomePage2::class.java)
            intent.putExtra("key", elderUID)
            startActivity(intent)
        }

        home1_editbutton.setOnClickListener{
            val intent = Intent(this, HomePage2::class.java)
            startActivity(intent)
        }

        home1_deletebutton.setOnClickListener{
            val intent = Intent(this, HomePage4::class.java)
            startActivity(intent)
        }

        homeicon_page1.setOnClickListener{
            val intent = Intent(this, HomePage1::class.java)
            startActivity(intent)
        }

        calendaricon_page1.setOnClickListener{
            val intent = Intent(this, CalendarMainActivity::class.java)
            startActivity(intent)
        }

        transporticon_page1.setOnClickListener{
            val intent = Intent(this, trans1::class.java)
            startActivity(intent)
        }

        accounticon_page1.setOnClickListener{
            val intent = Intent(this, AccountMainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Function to return name of caretakee from Firebase
     *
     * @param elderUID ID of the caretakee
     * @param setText TextView to output the name in
     * @return A string representing the Caretakee's Name
     */
    fun getElderlyName(elderUID: String, setText: TextView) {        //function for getting stuff
        val TAG = "myLogTag"
        val test = " "
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("careRecipient").document(elderUID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val test  = document.get("name").toString()
                    Log.d(TAG,":${test}")
                    if (test != "null"){
                        setText.text = test}
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    /**
     * Function to show the UserName of the caretaker
     *
     * @param setText TextView to output the Username in
     */
    fun displayUserName(setText: TextView) {        //function for getting stuff
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser    //getting the user id value
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    setText.text = "Hello, " + document.get("name").toString()
                    Log.d(TAG, "Our data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    /**
     * Displays the List of tasks tagged to a specific caretakee
     *
     * @param elderUID ID of the specific caretakee
     */
    fun displayTaskList(elderUID: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("careRecipient").document(elderUID).collection("task")
            .whereEqualTo("name", "hotomi1")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

}