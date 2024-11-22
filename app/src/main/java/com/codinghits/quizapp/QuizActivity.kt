package com.codinghits.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codinghits.quizapp.databinding.ActivityQuizBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<QuizModel>
    private var count: Int = 0
    private var score = 0
    private lateinit var quizBinding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizBinding = ActivityQuizBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(quizBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        list = ArrayList<QuizModel>()
        Firebase.firestore.collection("quiz")
            .get().addOnSuccessListener {
                list.clear()
                for (i in it.documents) {
                    var questionModel = i.toObject(QuizModel::class.java)
                    list.add(questionModel!!)
                }
                quizBinding.question.setText(list.get(0).question)
                quizBinding.option1.setText(list.get(0).option1)
                quizBinding.option2.setText(list.get(0).option2)
                quizBinding.option3.setText(list.get(0).option3)
                quizBinding.option4.setText(list.get(0).option4)
            }
//        list.add(QuizModel("Who is My Best Friend","Gaurav","Nitish","Abhilasha","No One","No One"))
//        list.add(QuizModel("Who is My Best Friend","Nitish","Gaurav","Abhilasha","No One","No One"))
//        list.add(QuizModel("Who is My Best Friend","Gaurav","Nitish","Abhishek","No One","No One"))
//        list.add(QuizModel("Who is My Best Friend","Nitish","Gaurav","Abhishek","No One","No One"))


        quizBinding.option1.setOnClickListener {
            nextData(quizBinding.option1.text.toString())
           
        }
        quizBinding.option2.setOnClickListener { nextData(quizBinding.option2.text.toString()) }
        quizBinding.option3.setOnClickListener { nextData(quizBinding.option3.text.toString()) }
        quizBinding.option4.setOnClickListener { nextData(quizBinding.option4.text.toString()) }

    }

    private fun nextData(i: String) {
        if (count < list.size) {
            if (list.get(count).ans.equals(i)) {
                score++
            }
        }
        count++
        if (count >= list.size) {
            val i = Intent(applicationContext, ScoreActivity::class.java)
            i.putExtra("NO", score)
            startActivity(i)
        } else {


            quizBinding.question.setText(list.get(count).question)
            quizBinding.option1.setText(list.get(count).option1)
            quizBinding.option2.setText(list.get(count).option2)
            quizBinding.option3.setText(list.get(count).option3)
            quizBinding.option4.setText(list.get(count).option4)
        }
    }


}

