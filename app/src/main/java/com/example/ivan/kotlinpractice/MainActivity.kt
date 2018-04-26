package com.example.ivan.kotlinpractice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ivan.kotlinpractice.objects.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isClicked = false
    var person1 = Person("John", "Johnson")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOne.setOnClickListener { buttonClick() }
        buttonNavigate.setOnClickListener { buttonNavigate() }
        buttonShowFullname.setOnClickListener {buttonClickFullName()}
    }

    fun buttonClick(){
        if(!isClicked){
            textViewOne.text = person1.firstName
            isClicked = true
        }
        else{
            textViewOne.text = person1.lastName
            isClicked = false
        }
    }

    fun buttonClickFullName(){
        if(!isClicked){
            var fullName = fullName(person1.firstName, person1.lastName)
            textViewOne.text = fullName
            isClicked = true
        }
        else{
            textViewOne.text = "Click to see full name"
            isClicked = false
        }
    }

    fun buttonNavigate(){
        val intent = Intent(this, NextActivity::class.java)
        intent.putExtra("Tag", person1)
        startActivity(intent)
        //startActivity(Intent(this, NextActivity::class.java))
    }

    fun fullName(x: String, y: String) = "$x $y"

}
