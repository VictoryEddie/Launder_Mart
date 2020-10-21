package com.example.laundermart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registering.*

class Registering : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var termsAccept: CheckBox
    private lateinit var email: EditText
    private lateinit var password: EditText
  private  lateinit var names: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registering)
        mAuth = FirebaseAuth.getInstance()
        supportActionBar?.hide()


        textView5.setOnClickListener {
            val intent = Intent(this@Registering, Loginpage::class.java)
            startActivity(intent)
        }

        sign_in_button.setOnClickListener {
            termsAccept = findViewById(R.id.registercheckBox)
             val email = registermail.text.toString()
             val password = registerpassword.text.toString()
             val name = registername.text.toString()


            if (email.isEmpty() && password.isEmpty() && name.isEmpty()) {
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

            if (name.isEmpty() || name.length < 3) {
                registername.error = "Valid Name Required"
                registername.requestFocus()
                return@setOnClickListener
            }
            registerUser()
//            checkLoggedInState()

        }

    }

    private fun registerUser() {
         val email = registermail.text.toString()
         val password = registerpassword.text.toString()

        mAuth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registration Successful!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Registering, Locator::class.java).apply {
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
//            Toast.makeText(this, "YOU ARE NOT LOOGED IN", Toast.LENGTH_LONG).show()
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

