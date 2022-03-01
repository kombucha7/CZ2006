package com.example.cz2006ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPageBackBtn.setOnClickListener{
            val loginBackBtn = Intent(this, MainActivity::class.java)
            startActivity(loginBackBtn)
        }
    }

    // Configure Google Sign In
//    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(getString(R.string.default_web_client_id))
//        .requestEmail()
//        .build()

//    private lateinit var googleSignInClient: GoogleSignInClient
//    googleSignInClient = GoogleSignIn.getClient(this, gso)
}