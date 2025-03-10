package com.example.cnc_code_generator_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to StartNewProgram screen
        binding.startNewBtn.setOnClickListener {
            startActivity(Intent(this, StartNewProgram::class.java))
        }

        binding.startProgramBtn.setOnClickListener {
            startActivity(Intent(this, StartNewProgram::class.java))
        }
    }
}
