package com.example.labexam4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val taskIcon:ImageView = findViewById(R.id.listimage1)
        taskIcon.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val pIcon:ImageView = findViewById(R.id.profileimage1)
        pIcon.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }


    }
}