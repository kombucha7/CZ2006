package com.example.cz2006ver2.ConnectGroup

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.cz2006ver2.HomePage.HomePage1
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_connect_page.*
import kotlinx.coroutines.delay
import kotlin.random.Random

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
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()


        //this is for when they do not have an account
        createCode.setOnClickListener{
            val intent = Intent(this, YourCodePageActivity::class.java)
            startActivity(intent)
        }

        connectEnterButton.setOnClickListener{
            val groupCode = connectGroupCodeField.text
            if (TextUtils.isEmpty(groupCode)){
                Toast.makeText(this@ConnectPageActivity, "Field cannot be empty.", Toast.LENGTH_SHORT ).show()
            }else {
                enterGroupCode(groupCode.toString()) //in this func, we direct to same page if unsuccessful. look at func below
                ///////////////////async function so we cant implement as normal func///////////////////////////////////////

            }
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
                    Log.d(TAG, " carerecipient found and adding to user's database")        //if it is successful, add elderKey to user's array
                    addElderKeyToUser(groupCode)
                } else {
                    Log.d(TAG, "No such document")                                      //if unsuccessful, just start intent at this page again
                    Toast.makeText(this@ConnectPageActivity, "Group does not exist ", Toast.LENGTH_SHORT ).show()
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
        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    println("persons name " + document.get("name").toString())
                    var personName = document.get("name").toString()
                    addUserToElderSubCol(elderKey.toString(), personName)   //saving the caretaker data to elderly subcollection
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val intent = Intent(this,HomePage1::class.java)
        intent.putExtra("key", elderKey.toString())
        startActivity(intent)

    }

    fun addUserToElderSubCol(elderUID : String, userName: String?){  //not done yet!!!!
        //get instance of user
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val data = GroupNamePageActivity.userNameInfo(userName)

        db.collection("careRecipient").document(elderUID).collection("caretaker").document(userID).set(data)
            .addOnSuccessListener {
//                Toast.makeText(this@ConnectPageActivity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }

            .addOnFailureListener{
                Toast.makeText(this@ConnectPageActivity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }

//    fun addElderToUser(elderUID : String) {      //function for posting stuff
//        //get instance of user
//        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
//        val userID = currentFirebaseUser!!.uid
//        val db = FirebaseFirestore.getInstance()
//
//        val data =
//            GroupNamePageActivity.elderInfo(elderUID) //check on top to find data that we add in. need to add more eventually
//        val userCareRecipientArray = db.collection("users").document(userID)
//
//        userCareRecipientArray.update("careArray", FieldValue.arrayUnion(elderUID))
//            .addOnSuccessListener {
////                Toast.makeText(this@ConnectPageActivity, "record added successfully ", Toast.LENGTH_SHORT ).show()
//            }
//
//            .addOnFailureListener{
//                Toast.makeText(this@ConnectPageActivity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
//            }
//    }
}