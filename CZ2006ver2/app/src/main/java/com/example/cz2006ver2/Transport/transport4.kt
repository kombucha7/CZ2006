package com.example.cz2006ver2.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_transport2.*

/**
 * Class used for user's favourite bus stop and bus numbers
 */
class transport4 : AppCompatActivity() {
    /**
     * Method used to start default activity. Link back to main Transport Page.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport4)
        val list: MutableList<String> = ArrayList()
        val busStopCode:String = intent.getStringExtra("BusStopCode").toString()
        if (busStopCode != null) {
            list.add(busStopCode)
        }
        //this dropdown is for the bus numbers on that particular busstop
        //if dropdown is to show the available bus stop codes instead, then need another for loop
//        for (i in 0 until services.length()) {
//            val busNum = services.getJSONObject(i).getString("ServiceNo")
//            list.add(busNum)
//        }
        var busStopListView: ListView = findViewById(R.id.busStopList)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        busStopListView.adapter = arrayAdapter

        back_btn_cal2.setOnClickListener {
            val back1 = Intent(this, trans1::class.java)
            startActivity(back1)
        }
        back_word_cal2.setOnClickListener {
            val back2 = Intent(this, trans1::class.java)
            startActivity(back2)
        }
    }

    private fun getFavouriteBusStop(elderKey : String, busStopCode: String){

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        val collectionRef = db.collection("careRecipient").document("un5zqQK0").collection("favBusStop")
        collectionRef.get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    Log.d("222", "document found")
//                    collection.id
                } else {
                    Log.d("222", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("222", "get failed with ", exception)
            }

//        db.collection("careRecipient").document(elderKey).collection("favBusStop").document(busStopCode).set(setFav)
//            .addOnSuccessListener {
//                Toast.makeText(this@transport3, "record added successfully ", Toast.LENGTH_SHORT ).show()
//            }
//
//            .addOnFailureListener{
//                Toast.makeText(this@transport3, "record Failed to add ", Toast.LENGTH_SHORT ).show()
//            }

    }
}