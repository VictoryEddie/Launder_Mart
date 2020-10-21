package com.example.laundermart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

   private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Loginpage::class.java)
            startActivity(intent)
            checkLoggedInState()
            finish()
        }, 3500)

        mAuth = FirebaseAuth.getInstance()
//        checkLoggedInState()

    }

    private fun checkLoggedInState() {
        if (mAuth.currentUser == null) {
            Toast.makeText(this, "You Are Not Logged In", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Welcome Back!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Locator::class.java)
            startActivity(intent)
        }
    }
//
//    override fun onStart() {
//        super.onStart()
//        checkLoggedInState()
//    }


}
