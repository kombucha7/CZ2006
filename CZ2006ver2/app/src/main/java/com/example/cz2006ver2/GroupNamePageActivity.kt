package com.example.cz2006ver2

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_group_name_page.*
import kotlinx.android.synthetic.main.activity_test1.*

class GroupNamePageActivity : AppCompatActivity() {

    private lateinit var elderName: EditText
    data class elderInfo(
        //data that we are passing in, add on to userInfo if want to ask for more
        val name: String? = null,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_name_page)

        val elderUID = intent.getStringExtra("key")

        groupnameNextButton.setOnClickListener{
            elderName = findViewById(R.id.groupNameField)
            val elderNameString = elderName.text.toString()     //convert to string at this stage
            if (elderUID != null) {
                saveFireStore(elderNameString,elderUID)
            }
        }
    }




    fun saveFireStore(elderName: String,elderKey : String) {      //function for posting stuff
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        val data = elderInfo(elderName) //check on top to find data that we add in. need to add more eventually

        db.collection("careRecipient").document(elderKey).set(data)
            .addOnSuccessListener {
                Toast.makeText(this@GroupNamePageActivity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }

            .addOnFailureListener{
                Toast.makeText(this@GroupNamePageActivity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
    }




}