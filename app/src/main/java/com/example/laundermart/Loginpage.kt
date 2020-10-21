package com.example.laundermart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginpage.*
import kotlinx.android.synthetic.main.activity_registering.*

class Loginpage : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var rememberme: CheckBox
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        mAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        sign_up.setOnClickListener {
            val intent = Intent(this@Loginpage, Registering::class.java)
            startActivity(intent)
        }

        loginbutton.setOnClickListener {

//            rememberme = findViewById(R.id.loginbutton)
            val email = loginemail.text.toString()
            val password = loginpassword.text.toString()



            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Fill All The Blanks Please", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                registermail.error = "Valid Email Required"
                registermail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                registermail.error = "Valid Email Required"
                registermail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                registerpassword.error = "Six or more characters Required"
                registerpassword.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
           // checkLoggedInState()

        }


        }

//    override fun onStart() {
//        super.onStart()
//        checkLoggedInState()
//    }

    private fun loginUser(email: String, password: String) {
        val email = loginemail.text.toString()
        val password = loginpassword.text.toString()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Loginpage, Locator::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                } else {
                    task.exception?.message?.let {
                        toast(it)
                    }

                }

            }
    }

//    private fun checkLoggedInState() {
//        if (mAuth.currentUser == null) {
//            val intent = Intent(this, Loginpage::class.java)
//            startActivity(intent)
//        } else {
//            Toast.makeText(this, "Welcome Back!!", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, Locator::class.java)
//            startActivity(intent)
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}




