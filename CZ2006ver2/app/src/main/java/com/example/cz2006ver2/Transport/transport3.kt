package com.example.cz2006ver2.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cz2006ver2.R
import kotlinx.android.synthetic.main.activity_transport3.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Class that is used to display information from the Search Page.
 */
class transport3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Method used to start default activity. Link back to Search Page.
         * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport3)
        val busStopName: TextView = findViewById(R.id.BusStopName)
        val stopCode: TextView = findViewById(R.id.displayBusStopCode)
        val arrivalBaseURL = "http://datamall2.mytransport.sg/ltaodataservice/BusArrivalv2"
        val busStopURL = "http://datamall2.mytransport.sg/ltaodataservice/BusStops"
        val busStopCode:String = intent.getStringExtra("BusStopCode").toString()
        val codeIndex:String = intent.getStringExtra("BusJSONObjectNum").toString()
//        Toast.makeText(this@transport3, busStopCode + codeIndex, Toast.LENGTH_LONG).show()
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = object: JsonObjectRequest(
            Method.GET, busStopURL, null,  Response.Listener<JSONObject>
            { response ->

                val busStops: JSONArray = response.getJSONArray("value")
                var check = 0
                for (i in 0 until busStops.length()) {
                    val thisStopCode = busStops.getJSONObject(i).getString("BusStopCode")
//                    Toast.makeText(this@transport3, thisStopCode, Toast.LENGTH_LONG).show()
                    println(thisStopCode)
                    if (thisStopCode == busStopCode){
                        var description = busStops.getJSONObject(i).getString("Description")
                        busStopName.setText(description)
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


        back_btn_cal2.setOnClickListener {
            val back1 = Intent(this, trans1::class.java)
            startActivity(back1)
        }
        back_word_cal2.setOnClickListener {
            val back2 = Intent(this, trans1::class.java)
            startActivity(back2)
        }
    }
}