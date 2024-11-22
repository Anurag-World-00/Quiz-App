package com.codinghits.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codinghits.quizapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    private lateinit var signupBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(signupBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signupBinding.btnSignup.setOnClickListener {
            val userName = signupBinding.editTextEmail1.text.toString()
            val userPassword = signupBinding.editTextPassword1.text.toString()
            SignUp(userName,userPassword)
        }
    }

    private fun SignUp(userName:String ,userPassword : String ) {
        auth.createUserWithEmailAndPassword(userName,userPassword).addOnCompleteListener {
            if(it.isSuccessful){

            Toast.makeText(applicationContext, "Successfully created", Toast.LENGTH_SHORT).show()
            val i = Intent(applicationContext,LoginActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(applicationContext, "Not created", Toast.LENGTH_SHORT).show()
            }
        }

    }
}