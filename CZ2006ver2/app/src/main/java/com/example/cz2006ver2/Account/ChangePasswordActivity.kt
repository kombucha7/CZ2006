package com.example.cz2006ver2.Account

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.cz2006ver2.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_logout.*

/**
 * This activity allows users to change their passwords.
 */
class ChangePasswordActivity : AppCompatActivity() {

    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    //var newPassword:String = ""

    /**
     * Method used to start default activity
     * @param savedInstanceState to get prior version. If no data is supplies, then NULL.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        changePW_BackText.setOnClickListener {
            val passwordChange = Intent(this, AccountMainActivity::class.java)
            startActivity(passwordChange)
        }

        changePW_ConfirmButton.setOnClickListener {
            if(changePass()) {
                val passwordChange = Intent(this, AccountMainActivity::class.java)
                startActivity(passwordChange)
            }
        }
    }

    fun changePass():Boolean {
        var newPassword:String = ""
        var checky = false
        val emailadd: EditText = findViewById(R.id.changePW_CurEmail)
        val curpass: EditText = findViewById(R.id.changePW_CurPW)
        val newPass: EditText = findViewById(R.id.changePW_NewPW)
        val newPassConfirm: EditText = findViewById(R.id.changePW_ReEnterNewPW)
        val creds = EmailAuthProvider.getCredential(emailadd.text.toString(), curpass.text.toString())
        user?.reauthenticate(creds)?.addOnCompleteListener {task1 ->
            if(task1.isSuccessful){
                if(newPass.text.toString() == newPassConfirm.text.toString()) {
                    newPassword = newPass.text.toString()
                    if(newPassword != "") {
                        user!!.updatePassword(newPassword)
                        .addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                Log.d(ContentValues.TAG, "User password updated.")
                                checky = true
                                finish()
                            }
                            else {
                                Toast.makeText(this, "Passwords update fail", Toast.LENGTH_LONG).show()
                                checky = false
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                    checky = false
                }
            }
            else{
                Toast.makeText(this, "Email or Current Password is incorrect", Toast.LENGTH_LONG).show()
            }

        }
        return checky
    }
}