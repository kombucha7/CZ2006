package com.example.cz2006ver2.Calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.cz2006ver2.HomePage.HomePage1
import com.example.cz2006ver2.HomePage.HomePage2
import com.example.cz2006ver2.HomePage.Todo
import com.example.cz2006ver2.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_calendar_caretaker.*
import org.w3c.dom.Text

/**
 * This class allows for swapping of caretakers for a particular day
 */
class CalendarCaretakerActivity : AppCompatActivity() {
    /**
     * Method used to change caretakers.
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    data class dateName(
        val name: String
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_caretaker)
        var curr_date = intent.getStringExtra("scheduled_date")
        schedule_date1.setText(curr_date)
        testFirestore("un5zqQK0") // we need to change this to get the elderUID later

        val elderUID = "un5zqQK0"//pass the elderUID as intent now is only hardcoded. Pass the date as intent too
        val date = "2022-04-03"

        cal_caretaker1.setOnClickListener{
            updateName(elderUID,date,cal_caretaker1)
            val caretaker1 = Intent(this, CalendarCaretakerActivity2::class.java)
            caretaker1.putExtra("name",cal_caretaker1.text.toString())
            startActivity(caretaker1)
        }
        cal_caretaker2.setOnClickListener{
            updateName(elderUID,date,cal_caretaker2)
            val caretaker1 = Intent(this, CalendarCaretakerActivity2::class.java)
            caretaker1.putExtra("name",cal_caretaker2.text.toString())
            startActivity(caretaker1)
        }
        cal_caretaker3.setOnClickListener{
            updateName(elderUID,date,cal_caretaker3)
            val caretaker1 = Intent(this, CalendarCaretakerActivity2::class.java)
            caretaker1.putExtra("name",cal_caretaker3.text.toString())
            startActivity(caretaker1)
        }
        cal_caretaker4.setOnClickListener{
            updateName(elderUID,date,cal_caretaker4)
            val caretaker1 = Intent(this, CalendarCaretakerActivity2::class.java)
            caretaker1.putExtra("name",cal_caretaker4.text.toString())
            startActivity(caretaker1)
        }
        cal_caretaker5.setOnClickListener{
            updateName(elderUID,date,cal_caretaker5)
            val caretaker1 = Intent(this, CalendarCaretakerActivity2::class.java)
            caretaker1.putExtra("name",cal_caretaker5.text.toString())
            startActivity(caretaker1)
        }

        back_word_cal2.setOnClickListener{
            val cal2BackBtn = Intent(this, CalendarDayActivity::class.java)
            cal2BackBtn.putExtra("scheduled_date",curr_date)
            startActivity(cal2BackBtn)
        }
        // confirm btn
        cal_caretaker_changebtn.setOnClickListener{
            val calConfirmBtn = Intent(this, CalendarCaretakerActivity2::class.java)
            calConfirmBtn.putExtra("scheduled_date",curr_date)
            startActivity(calConfirmBtn)
        }
    }
    fun testFirestore(elderUID: String){
        //define taskObject type
        var nameList: MutableList<String> = ArrayList()

        val db = FirebaseFirestore.getInstance()
        FirebaseFirestore
            .getInstance()
            .collection("careRecipient").document(elderUID).collection("caretaker")
            .addSnapshotListener(this
            ) { querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                if (querySnapshot != null) {
                    for (document in querySnapshot.documents) {
                        nameList.add(document.get("name").toString())
                    }
                    println("Caretaker names : " + nameList[1])
                    assignToEditTask(nameList)
                }
            }
    }

    fun updateName(elderUID: String, date: String, pos: TextView) {
        val caretaker = dateName(pos.text.toString())
        val db = FirebaseFirestore.getInstance()
        db.collection("careRecipient").document(elderUID).collection("caretakerDay")
            .document(date).set(caretaker)
    }

    fun assignToEditTask(nameList: MutableList<String>){
        var cr1 = findViewById<TextView>(R.id.cal_caretaker1)
        val cr2 = findViewById<TextView>(R.id.cal_caretaker2)
        val cr3 = findViewById<TextView>(R.id.cal_caretaker3)
        val cr4 = findViewById<TextView>(R.id.cal_caretaker4)
        val cr5 = findViewById<TextView>(R.id.cal_caretaker5)

        val lsize = nameList.size

        when (lsize) {       //convert this into a function
            1 -> {
                cr1.text = nameList[0]
            }
            2 -> {
                cr1.text = nameList[0]
                cr2.text = nameList[1]
            }
            3 -> {
                cr1.text = nameList[0]
                cr2.text = nameList[1]
                cr3.text = nameList[2]
            }
            4 -> {
                cr1.text = nameList[0]
                cr2.text = nameList[1]
                cr3.text = nameList[2]
                cr4.text = nameList[3]
            }
            5 -> {
                cr1.text = nameList[0]
                cr2.text = nameList[1]
                cr3.text = nameList[2]
                cr4.text = nameList[3]
                cr5.text = nameList[4]
            }
        }
    }
}