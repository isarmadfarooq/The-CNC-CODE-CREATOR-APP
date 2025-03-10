package com.example.cnc_code_generator_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityStartNewProgramBinding

class StartNewProgram : AppCompatActivity() {

    private lateinit var binding: ActivityStartNewProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartNewProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this,ProgramStartingInformation::class.java))
        }

        // Close button functionality
        binding.closeBtn.setOnClickListener {
            finish() // Closes the activity and returns to MainActivity
        }
    }
}
