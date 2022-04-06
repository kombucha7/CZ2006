package com.example.cz2006ver2.ConnectGroup

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cz2006ver2.HomePage.HomePage1
import com.example.cz2006ver2.LoginRegister.MainActivity
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_group_name_page.*


class GroupNamePageActivity : AppCompatActivity() {

    private lateinit var elderName: EditText
    data class elderInfo(
        //data that we are passing in, add on to userInfo if want to ask for more
        val name: String? = null,
    )
    data class userNameInfo(        //data that we are passing in, add on to userInfo if want to ask for more
        val name: String? = null,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_name_page)

        val elderUID = intent.getStringExtra("key").toString()

        //get user's name and add into subcollection for elderly///////
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    println("persons name " + document.get("name").toString())
                    var personname = document.get("name".toString()).toString()
                    addUserToElderSubCol(elderUID,personname)   //saving the caretaker data to elderly subcollection
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        ////////////////////////////.////////////////////////////////////////




        //direct them to homepage after registering
        groupnameNextButton.setOnClickListener{
            elderName = findViewById(R.id.groupNameField)
            val elderNameString = elderName.text.toString()     //convert to string at this stage
            if (elderUID != null) {
                saveElderInfo(elderNameString,elderUID)
                addElderToUser(elderUID)
            }
            val intent = Intent(this, HomePage1::class.java)
            intent.putExtra("key", elderUID)

            startActivity(intent)
        }

    }




    fun saveElderInfo(elderName: String,elderKey : String) {    //adding elder to database
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        val data = elderInfo(elderName)

        db.collection("careRecipient").document(elderKey).set(data)
            .addOnSuccessListener {
                println("Record added successfully")
            }

            .addOnFailureListener{ Toast.makeText(this@GroupNamePageActivity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
                println("Record failed to add")
            }
    }


    fun addElderToUser(elderUID : String) {      // add to the users array of carerecipient
        //get instance of user
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val data = elderInfo(elderUID) //check on top to find data that we add in. need to add more eventually
        val userCareRecipientArray = db.collection("users").document(userID)

        userCareRecipientArray.update("careArray", FieldValue.arrayUnion(elderUID))
            .addOnSuccessListener {
                println("Record added successfully")
            }

            .addOnFailureListener{
                println("Record failed to add")
            }
    }

    fun addUserToElderSubCol(elderUID : String, userName: String?){
        //get instance of user
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val data = userNameInfo(userName)

        db.collection("careRecipient").document(elderUID).collection("caretaker").document(userID).set(data)
            .addOnSuccessListener {
                println("Record added successfully")
            }

            .addOnFailureListener{
                println("Record failed to add")
            }
    }


    fun displayUserName(){        //function for getting stuff
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser    //getting the user id value
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, document.get("name").toString())

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }



}