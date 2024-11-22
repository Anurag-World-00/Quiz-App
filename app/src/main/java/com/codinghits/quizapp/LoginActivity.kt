package com.codinghits.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codinghits.quizapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    private lateinit var mainBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityLoginBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding.textSignup.setOnClickListener {
            val intent = Intent(applicationContext,SignupActivity::class.java)
            startActivity(intent)
        }
        mainBinding.btnSignin.setOnClickListener{
        val userName = mainBinding.editTextEmail.text.toString()
            val userPassword = mainBinding.editTextPassword.text.toString()
            signIn(userName,userPassword)
        }
    }

    private fun signIn(userName : String , userPassword : String) {
        auth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener {
            if(it.isSuccessful){
                val i = Intent(applicationContext,QuizActivity::class.java)
                startActivity(i)
                finish()
                Toast.makeText(applicationContext, "Successfully login", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null){
            val i = Intent(applicationContext,QuizActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}