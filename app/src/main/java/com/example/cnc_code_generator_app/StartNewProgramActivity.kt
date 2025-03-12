package com.example.cnc_code_generator_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityStartNewProgramBinding

class StartNewProgramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartNewProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartNewProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            // Clear previous program structure/data
            ProgramRepository.programStructure.clear()
            ProgramRepository.currentProgramData = ProgramData()  // reset to defaults

            startActivity(Intent(this,ProgramStartingInformationActivity::class.java))
            finish()
        }

        // Close button functionality
        binding.closeBtn.setOnClickListener {
            finish() // Closes the activity and returns to MainActivity
        }
    }
}
