package com.example.cz2006ver2.ConnectGroup

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.cz2006ver2.Account.AccountMainActivity
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_join_create_grp2.*
import kotlin.random.Random

class JoinCreateGrp2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_create_grp2)

        var cr1 = findViewById<TextView>(R.id.join_create_grp2_code)
        val elderUID = getRandomString(8).toString()

        cr1.text = elderUID

        // back btn
        join_create_grp2_BackText.setOnClickListener {
            val intent = Intent(this, JoinCreateGrpActivity::class.java)
            startActivity(intent)
        }
         //enter btn
        join_create_grp2_enter.setOnClickListener{
            val intent = Intent(this, AccountMainActivity::class.java)
            saveElderInfo(join_create_grp2_input.text.toString(),elderUID)
            addElderToUser(elderUID)

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
                        Log.d(ContentValues.TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                }

            startActivity(intent)
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

    fun saveElderInfo(elderName: String,elderKey : String) {    //adding elder to database
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        val data = GroupNamePageActivity.elderInfo(elderName)

        db.collection("careRecipient").document(elderKey).set(data)
            .addOnSuccessListener {
                Toast.makeText(this@JoinCreateGrp2Activity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }

            .addOnFailureListener{
                Toast.makeText(this@JoinCreateGrp2Activity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }


    fun addElderToUser(elderUID : String) {      // add to the users array of carerecipient
        //get instance of user
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val data =
            GroupNamePageActivity.elderInfo(elderUID) //check on top to find data that we add in. need to add more eventually
        val userCareRecipientArray = db.collection("users").document(userID)

        userCareRecipientArray.update("careArray", FieldValue.arrayUnion(elderUID))
            .addOnSuccessListener {
                Toast.makeText(this@JoinCreateGrp2Activity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }

            .addOnFailureListener{
                Toast.makeText(this@JoinCreateGrp2Activity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }

    fun addUserToElderSubCol(elderUID : String, userName: String?){
        //get instance of user
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val data = GroupNamePageActivity.userNameInfo(userName)

        db.collection("careRecipient").document(elderUID).collection("caretaker").document(userID).set(data)
            .addOnSuccessListener {
                Toast.makeText(this@JoinCreateGrp2Activity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }

            .addOnFailureListener{
                Toast.makeText(this@JoinCreateGrp2Activity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }
}