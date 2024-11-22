package com.codinghits.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codinghits.quizapp.databinding.ActivityScoreBinding
import com.google.firebase.auth.FirebaseAuth

class ScoreActivity : AppCompatActivity() {
    private lateinit var scoreBinding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreBinding = ActivityScoreBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(scoreBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dd : String = "Your Score is"
        scoreBinding.textView4.text = buildString {
            append(dd)
            append(" ")
            append(intent.getIntExtra("NO",0))
        }
        scoreBinding.textView5.setOnClickListener {
            if (it.id == R.id.textView5){
                FirebaseAuth.getInstance().signOut()
                val i = Intent(applicationContext,LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext,QuizActivity::class.java)
        startActivity(intent)
        finish()
    }

}