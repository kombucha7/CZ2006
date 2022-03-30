package com.example.cz2006ver2.HomePage

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cz2006ver2.Account.AccountMainActivity
import com.example.cz2006ver2.Calendar.CalendarMainActivity
import com.example.cz2006ver2.Home1Recyclerr
import com.example.cz2006ver2.R
import com.example.cz2006ver2.Transport.trans1
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_home_page1.*
import kotlinx.android.synthetic.main.activity_test_transport_api.*


/**
     * main function for HomePage1
     * includes buttons for navigation bar
     * includes buttons for back button
     *
     * @param savedInstanceState
     */

class HomePage1 : AppCompatActivity() { //must tag user to elderly. when we create their profile then we assign user to them?

        data class taskObject(
            val name: String? = null,
            val description: String? = null,
            val datetimeTask: String? = null

        )

//        private var layoutManager: RecyclerView.LayoutManager? = null
//        private var adapter: RecyclerView.Adapter<Home1Recyclerr.ViewHolder>? = null
        private lateinit var todoAdapter: TodoAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home_page1)

            var objlist: MutableList<taskObject> = ArrayList()
            val elderUID = intent.getStringExtra("key")
            var testList: MutableList<String> = ArrayList()
            val LOG = " "
            Log.d(LOG, "this is the elderly key " + elderUID)

            displayUserName(home1introtext)


            //////////////////get specific task based on ---//////////////////////////////////
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("careRecipient").document(elderUID.toString()).collection("task")
                .whereEqualTo("date", "2022-2-31")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
            //////////////////////////////////////////////////////////////////////////////////////

            ///////////////////testing///////////
            displayDocumentID(elderUID.toString(),testList)
            deleteTasks(elderUID.toString(),"e363e16c-26ba-4161-bafd-7d19467ae999")
            testFirestore(elderUID.toString()) //to see if i can convert into taskobject type
            ////////////////////////////////////


//            ///////////////recycler////////////////////
//            layoutManager = LinearLayoutManager(this)
//            home1scroll.layoutManager = layoutManager
//            adapter = Home1Recyclerr()
//            home1scroll.adapter = adapter
//            //////////////////////////////////////////
            // recyclerView Clarence/////////////////////////////
            todoAdapter = TodoAdapter(arrayListOf())

            rvTodoItems.adapter = todoAdapter
            rvTodoItems.layoutManager = LinearLayoutManager(this)
            ///////////////////////////////////////////////////


            home1_addbutton.setOnClickListener {
                val intent = Intent(this, HomePage2::class.java)
                intent.putExtra("key", elderUID)
                startActivity(intent)
            }

            home1_deletebutton.setOnClickListener {
                val intent = Intent(this, HomePage4::class.java)
                intent.putExtra("key", elderUID)
                startActivity(intent)
            }

            homeicon_page1.setOnClickListener {
                val intent = Intent(this, HomePage1::class.java)
                startActivity(intent)
            }

            calendaricon_page1.setOnClickListener {
                val intent = Intent(this, CalendarMainActivity::class.java)
                startActivity(intent)
            }

            transporticon_page1.setOnClickListener {
                val intent = Intent(this, trans1::class.java)
                startActivity(intent)
            }

            accounticon_page1.setOnClickListener {
                val intent = Intent(this, AccountMainActivity::class.java)
                startActivity(intent)
            }
        }

        /**
         * Function to return name of caretakee from Firebase
         *
         * @param elderUID ID of the caretakee
         * @param setText TextView to output the name in
         * @return A string representing the Caretakee's Name
         */
        fun getElderlyName(
            elderUID: String,
            setText: TextView
        ) {        //function for getting stuff
            val TAG = "myLogTag"
            val test = " "
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("careRecipient").document(elderUID)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val test = document.get("name").toString()
                        Log.d(TAG, ":${test}")
                        if (test != "null") {
                            setText.text = test
                        }
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }

        /**
         * Function to show the UserName of the caretaker
         *
         * @param setText TextView to output the Username in
         */
        fun displayUserName(setText: TextView) {        //function for getting stuff
            val currentFirebaseUser =
                FirebaseAuth.getInstance().currentUser
            val userID = currentFirebaseUser!!.uid
            val TAG = "myLogTag"
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("users").document(userID)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        setText.text = "Hello, " + document.get("name").toString()
                        Log.d(TAG, "Our data: ${document.data}")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }

        /**
         * Displays the List of tasks tagged to a specific caretakee
         *
         * @param elderUID ID of the specific caretakee
         */
        fun displayTaskList(elderUID: String, list: MutableList<taskObject>) {
            val db = FirebaseFirestore.getInstance()
            db.collection("careRecipient").document(elderUID).collection("task")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(TAG, "our task attributes " +"${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }

        fun displayDocumentID(elderUID: String, list: MutableList<String>) { //func to test if can pull names of doc id
            val db = FirebaseFirestore.getInstance()
            db.collection("careRecipient").document(elderUID).collection("task").get()
            //db.collection("careRecipient").document(elderUID).collection("task").get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            list.add(document.id)
                            println(task.result)
                        }
                        Log.d(TAG, "the tasks we have " + list.toString())
                        ///////////start code here to populate the fields after async call//////////
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                    }
                })
        }

        fun displayTasks(elderUID: String, list: MutableList<String>) { //func to test if can pull names of doc id
            val db = FirebaseFirestore.getInstance()
            db.collection("careRecipient").document(elderUID).collection("task").get()
                //db.collection("careRecipient").document(elderUID).collection("task").get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            list.add(document.id)
                        }
                        Log.d(TAG, "the tasks we have " + list.toString())
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                    }
                })
        }

        //func for deleting of ddocuments by ID under task (to be done aft recyclerview finish)
        fun deleteTasks(elderUID: String, taskID: String) { //func to test if can pull names of doc id
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("careRecipient").document(elderUID).collection("task").document(taskID)
            docRef.delete().addOnSuccessListener { task ->
                Log.w(TAG, "Deleted " )
            }
        }


    fun testFirestore(elderUID: String){
        //define taskObject type
        var testList: MutableList<taskObject> = ArrayList()

        val db = FirebaseFirestore.getInstance()
        FirebaseFirestore
            .getInstance()
            .collection("careRecipient").document(elderUID).collection("task")
            .addSnapshotListener(this
            ) { querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->

                if (querySnapshot != null) {
                    for (document in querySnapshot.documents) {
                        val myObject = document.toObject(taskObject::class.java)
                        if (myObject != null) {
                            testList.add(myObject)
                            val todo =  Todo(myObject.description.toString(), myObject.datetimeTask.toString())
                            todoAdapter.addTodo(todo)

                        }
                    }
                    println(testList[1].name)

                }
            }
    }
}