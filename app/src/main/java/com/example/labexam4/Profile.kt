package com.example.labexam4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val taskIcon1: ImageView = findViewById(R.id.listimage4)
        taskIcon1.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val hIcon: ImageView = findViewById(R.id.homeimage4)
        hIcon.setOnClickListener {
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }

    }
}