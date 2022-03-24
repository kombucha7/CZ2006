package com.example.cz2006ver2.ConnectGroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_connect_page.*

/**
 * Class for ConnectPage
 * Includes functionality to join an existing group with a connect code
 * and functionality to create a new group and connect code
 */
class ConnectPageActivity : AppCompatActivity() {

    /**
     * Main Function for ConnectPage class
     * Includes back button to return to previous page
     * Includes Enter button to initiate joining of existing group
     * Includes create button to initiate creating of a new group
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_page)

        //this is for when they do not have an account
        createCode.setOnClickListener{
            val intent = Intent(this, YourCodePageActivity::class.java)
            startActivity(intent)
        }

        connectEnterButton.setOnClickListener{
            val groupCode = connectGroupCodeField.text
            enterGroupCode(groupCode.toString())
            val intent = Intent(this, YourCodePageActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Function to join an existing group
     * Includes functionality to verify authenticity of code
     * Verification is done against saved data on firebase
     *
     * @param groupCode
     */
    //As for people that just registered and have a group code as well
    fun enterGroupCode(groupCode: String) {
        //verify if the code exists
        //add it to the user's careRecipient list
        val TAG = "Test "
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("careRecipient").document(groupCode)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d(TAG, " carerecipient found and adding to user's database")
                    addElderKeyToUser(groupCode)
                } else {
                    Log.d(TAG, "No such document")
                    val intent = Intent(this, ConnectPageActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    /**
     * Function to add care recipient to a group
     * Data is uploaded unto firebase
     *
     * @param elderKey
     */
    fun addElderKeyToUser(elderKey : String) {      //function for posting stuff
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        var userRef = db.collection("users").document(userID)

// Atomically add a new region to the care array array field.
        userRef.update("careArray", FieldValue.arrayUnion(elderKey))

    }
}