package com.example.cz2006ver2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class TestTransportAPI : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var et: EditText
    private lateinit var lv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_transport_api)

        btn1 = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        et = findViewById(R.id.testInput)
        lv = findViewById(R.id.testlv)

        btn1.setOnClickListener(View.OnClickListener { view ->
                    // Do some work here
                    // Instantiate the RequestQueue.
                    val queue = Volley.newRequestQueue(this)
                    val url = "https://api.data.gov.sg/v1/transport/carpark-availability"
//                    val url = "https://www.metaweather.com/api/api/location/search/?query=london"
// Request a string response from the provided URL.
                    val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject>
            { response ->

//                val cityInfo: JSONObject = response.getJSONObject(0);
//                val cityID: String = cityInfo.getString("carpark_info");

                //displays the first the carpark json objects
                val test: JSONObject = response.getJSONArray("items").getJSONObject(0).getJSONArray("carpark_data").getJSONObject(0)
                val carplate: String = test.getString("carpark_number")
//                val test1: JSONObject = test.getJSONObject()
//                val arr: JSONArray = obj.getJSONArray("posts")
//                val obj = JSONObject(" .... ")

                Toast.makeText(this, "CityID = " + carplate.toString(), Toast.LENGTH_SHORT).show();

             },Response.ErrorListener { error ->
            // TODO: Handle error
                        Toast.makeText(this, "WRONG LA", Toast.LENGTH_SHORT).show();

             })
//                    val stringRequest = StringRequest(Request.Method.GET, url,
//                        Response.Listener<String> { response ->
//                            // Display the first 500 characters of the response string.
//                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
//                        },
//                        Response.ErrorListener {  Toast.makeText(this, "That didn't work, ERROR!", Toast.LENGTH_SHORT).show(); })

// Add the request to the RequestQueue.
                    queue.add(jsonObjectRequest)
//                    Toast.makeText(this, "You clicked on 1.", Toast.LENGTH_SHORT).show();

        })

//        btn1.setOnClickListener{
//            // Instantiate the RequestQueue.
////                    val queue = Volley.newRequestQueue(this)
//////                    val url = "https://api.data.gov.sg/v1/transport/carpark-availability"
////                    val url = "https://www.metaweather.com/api/api/location/search/?query=london"
////// Request a string response from the provided URL.
////                    val stringRequest = StringRequest(Request.Method.GET, url,
////                        Response.Listener<String> { response ->
////                            // Display the first 500 characters of the response string.
////                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
////                        },
////                        Response.ErrorListener {  Toast.makeText(this, "That didn't work, ERROR!", Toast.LENGTH_SHORT).show(); })
////
////// Add the request to the RequestQueue.
////                    queue.add(stringRequest)
//        }

        btn2.setOnClickListener{
            Toast.makeText(this, "You clicked on 2.", Toast.LENGTH_SHORT).show()
        }
        btn3.setOnClickListener{
            Toast.makeText(this, "You clicked on 3.", Toast.LENGTH_SHORT).show()
        }
    }



//    val url = "http://datamall2.mytransport.sg/ltaodataservice/BusArrivalv2"

}