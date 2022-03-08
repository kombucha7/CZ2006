package com.example.cz2006ver2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        var cr1 = findViewById<TextView>(R.id.gs_group1)
        val cr2 = findViewById<TextView>(R.id.gs_group2)
        val cr3 = findViewById<TextView>(R.id.gs_group3)
        val cr4 = findViewById<TextView>(R.id.gs_group4)
        val cr5 = findViewById<TextView>(R.id.gs_group5)


        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser    //getting the user id value
        val userID = currentFirebaseUser!!.uid
        val TAG = "myLogTag"
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val list = documentSnapshot.get("careArray") as ArrayList<String>

                    var s1: String = ""
                    var s2: String = ""
                    var s3: String = ""
                    var s4: String = ""
                    var s5: String = ""
                    assignVal(list,s1,s2,s3,s4,s5)

                    cr1.text = s1
                    cr2.text = s2
                    cr3.text = s3
                    cr4.text = s4
                    cr5.text = s5

                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun assignVal(list: ArrayList<String>, s1: String, s2: String, s3: String, s4: String, s5: String) {
        val size = list.size

        when (size) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            3 -> print("x == 2")
            4 -> print("x == 2")
            5 -> print("x == 2")
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }

}


//                    val arrayElderlyUID = document.get("careArray").toString()
//                    Log.d(TAG, "Our data: ${arrayElderlyUID}")    //check code
//
//                    //clean up code (can help to figure out how to get values from array so we can skip this step)
//                    val arrayElderUIDRemove = removeUnnecessary(arrayElderlyUID)
//                    Log.d(TAG, "Our data: ${arrayElderUIDRemove}")    //check code
//Log.d(TAG, "Our data: ${test}")