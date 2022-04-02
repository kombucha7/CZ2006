package com.example.cz2006ver2.Transport

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cz2006ver2.ConnectGroup.GroupNamePageActivity
import com.example.cz2006ver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_transport3.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Class that is used to display information from the Search Page.
 */
class transport3 : AppCompatActivity() {

    data class busStopFav(
        val favourited: Boolean,
        val busStopLocation: String
    )

    private lateinit var busRecyclerView: RecyclerView
    private lateinit var busArrayList: ArrayList<BusInfo>
    lateinit var busNums: ArrayList<String>
    lateinit var firstTiming: ArrayList<String>
    lateinit var secondTiming: ArrayList<String>
    lateinit var thirdTiming: ArrayList<String>
    lateinit var color1: ArrayList<Int>
    lateinit var color2: ArrayList<Int>
    lateinit var color3: ArrayList<Int>
    lateinit var wheelchair1: ArrayList<Int>
    lateinit var wheelchair2: ArrayList<Int>
    lateinit var wheelchair3: ArrayList<Int>
    lateinit var busStopDesc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Method used to start default activity. Link back to Search Page.
         * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport3)
        val elderUID = intent.getStringExtra("key").toString()
        val busStopName: TextView = findViewById(R.id.BusStopName)
        val stopCode: TextView = findViewById(R.id.displayBusStopCode)
        val arrivalBaseURL = "http://datamall2.mytransport.sg/ltaodataservice/BusArrivalv2"
        val busStopURL = "http://datamall2.mytransport.sg/ltaodataservice/BusStops"
        val busStopCode:String = intent.getStringExtra("BusStopCode").toString()
        val codeIndex:String = intent.getStringExtra("BusJSONObjectNum").toString()
//        Toast.makeText(this@transport3, busStopCode + codeIndex, Toast.LENGTH_LONG).show()
        busNums = arrayListOf<String>()
        firstTiming = arrayListOf<String>()
        secondTiming = arrayListOf<String>()
        thirdTiming = arrayListOf<String>()
        color1 = arrayListOf<Int>()
        color2 = arrayListOf<Int>()
        color3 = arrayListOf<Int>()
        wheelchair1 = arrayListOf<Int>()
        wheelchair2 = arrayListOf<Int>()
        wheelchair3 = arrayListOf<Int>()
        busStopDesc = "Description not available"
        ///////checking if favourited based on entered code//////////&*******NEED TO GET ELDERS KEY OVER HERE*********
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()
        db.collection("careRecipient").document(elderUID).collection("favBusStop").document(busStopCode).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if(document != null) {
                    if (document.exists()) {
                        Log.d("TAG", "Document already exists.")
                        favouriteBusStop.isChecked = true;
                    } else {
                        Log.d("TAG", "Document doesn't exist.")
                    }
                }
            } else {
                Log.d("TAG", "Error: ", task.exception)
            }
        }
          //////////////////////////////////////////////////////////////////




        val allStatus = arrayOf(R.drawable.red_rectangle, R.drawable.green_rectangle, R.drawable.orange_rectangle)
        val wheelchairStatus = arrayOf(R.drawable.wheelchair_new, R.drawable.no_wheelchair)
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = object: JsonObjectRequest(
            Method.GET, busStopURL, null,  Response.Listener<JSONObject>
            { response ->

                val busStops: JSONArray = response.getJSONArray("value")
                var check = 0
//                println("First queeu checkpoint")
                for (i in 0 until busStops.length()) {
                    val thisStopCode = busStops.getJSONObject(i).getString("BusStopCode")
//                    Toast.makeText(this@transport3, thisStopCode, Toast.LENGTH_LONG).show()
//                    println(thisStopCode)
                    if (thisStopCode == busStopCode){
                        var description = busStops.getJSONObject(i).getString("Description")
                        busStopName.setText(description)
                        busStopDesc = description
                        check = 1
                        break
                    }
                }
                if (check == 0){
                    busStopName.setText("Bus Stop Details")
                }
                stopCode.setText("Bus stop number: $busStopCode")
            }, Response.ErrorListener { error ->
                // TODO: Handle error
                println(error.message)
                Toast.makeText(this, "Bus Data Not Available", Toast.LENGTH_SHORT).show();

            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("accept", "application/json")
                headers.put("AccountKey", "M8EyGPshTCOa1WvqEjEPQg==")
                return headers
            }}
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

        val arrivalBaseURLComplete = "$arrivalBaseURL?BusStopCode=$busStopCode"
        val queue2 = Volley.newRequestQueue(this)
        val jsonObjectRequest2 = object: JsonObjectRequest(
            Method.GET, arrivalBaseURLComplete, null,  Response.Listener<JSONObject>
            { response ->

                val busServices: JSONArray = response.getJSONArray("Services")
                var check = 0
                for (i in 0 until busServices.length()) {
                    val number = busServices.getJSONObject(i).getString("ServiceNo")
//                    val timing1 = busServices.getJSONObject(i).getJSONObject("NextBus").getString("EstimatedArrival")
//                    val timing2 = busServices.getJSONObject(i).getJSONObject("NextBus2").getString("EstimatedArrival")
//                    val timing3 = busServices.getJSONObject(i).getJSONObject("NextBus3").getString("EstimatedArrival")
//                    Toast.makeText(this@transport3, thisStopCode, Toast.LENGTH_LONG).show()
                    busNums.add(number)

//                    val datetime = LocalDate.parse(timing1, DateTimeFormatter.ISO_OFFSET_TIME)
                    val lis1 = listOf("1 min", "2 min", "3 min")
                    val lis2 = listOf("4 min", "5 min", "6 min", "7 min")
                    val lis3 = listOf("8 min", "9 min", "10 min", "11 min")
                    firstTiming.add(lis1.random())
                    secondTiming.add(lis2.random())
                    thirdTiming.add(lis3.random())
                    color1.add(allStatus.random())
                    color2.add(allStatus.random())
                    color3.add(allStatus.random())
                    wheelchair1.add(wheelchairStatus.random())
                    wheelchair2.add(wheelchairStatus.random())
                    wheelchair3.add(wheelchairStatus.random())
                }
                getUserdata()
            }, Response.ErrorListener { error ->
                // TODO: Handle error
                println(error.message)
                Toast.makeText(this, "Bus Data Not Available", Toast.LENGTH_SHORT).show();

            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("accept", "application/json")
                headers.put("AccountKey", "M8EyGPshTCOa1WvqEjEPQg==")
                return headers
            }}

        queue2.add(jsonObjectRequest2)
//        busNums = arrayOf("299", "355")
//        firstTiming = arrayOf("1 min", "2 min")
//        secondTiming = arrayOf("3 min", "4 min")
//        thirdTiming = arrayOf("6 min", "7 min")

        busRecyclerView = findViewById(R.id.busRecyclerView)
        busRecyclerView.layoutManager = LinearLayoutManager(this)
        busRecyclerView.setHasFixedSize(true)

        busArrayList = arrayListOf<BusInfo>()
//        getUserdata()


        back_btn_cal2.setOnClickListener {
            val back1 = Intent(this, trans1::class.java)
            back1.putExtra("key", "un5zqQK0") // <<< elderUID get from intent
            startActivity(back1)
        }

        favouriteBusStop.setOnCheckedChangeListener { checkBox, isChecked ->
            val intent = Intent(this, transport4::class.java)
            if (isChecked){
                Toast.makeText(this, "Bus Stop is in Favourites", Toast.LENGTH_SHORT).show()
                intent.putExtra("BusStopCode", busStopCode)
//                println("OVER HERE THE BUSSTOP CODE IS" + busStopCode)
                saveFavouriteBusStop(elderUID, busStopCode, busStopDesc) //NEED TO CHANGE THIS TO CURRENT UID
            }else {
                Toast.makeText(this, "Bus Stop removed from Favourites", Toast.LENGTH_SHORT).show()

                val db = FirebaseFirestore.getInstance()

                    var docRef = db.collection("careRecipient").document(elderUID)//add in the elderUID!
                        .collection("favBusStop").document(busStopCode)
                        docRef.delete().addOnSuccessListener { task ->
                        Log.w(ContentValues.TAG, "Deleted1111111111")

                }
            }


//                            Toast.makeText(this@transport2, element.toString() + position, Toast.LENGTH_LONG).show()
        }
    }
    private fun saveFavouriteBusStop(elderKey : String, busStopCode: String, busStopDesc:String){

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        val userID = currentFirebaseUser!!.uid
        //Toast.makeText(this, "" + currentFirebaseUser!!.uid, Toast.LENGTH_SHORT).show()   just for testing
        val db = FirebaseFirestore.getInstance()

        val setFav = busStopFav(favourited = true, busStopLocation = busStopDesc)

        db.collection("careRecipient").document(elderKey).collection("favBusStop").document(busStopCode).set(setFav)
            .addOnSuccessListener {
//                Toast.makeText(this@transport3, "record added successfully ", Toast.LENGTH_SHORT ).show()
                println("record added successfully ")
            }

            .addOnFailureListener{
                Toast.makeText(this@transport3, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }

    }

    private fun getUserdata() {
        for (i in 0 until busNums.size){
            val bus = BusInfo(busNums[i], firstTiming[i], secondTiming[i], thirdTiming[i], color1[i], color2[i], color3[i],
            wheelchair1[i], wheelchair2[i],wheelchair3[i])
            busArrayList.add(bus)
//            println(busNums[i])
//            println("hereerrerererere")
        }
        busRecyclerView.adapter = MyAdapter(busArrayList)
    }
}