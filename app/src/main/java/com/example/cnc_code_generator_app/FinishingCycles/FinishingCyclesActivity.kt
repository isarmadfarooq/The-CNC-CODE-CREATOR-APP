package com.example.cnc_code_generator_app.FinishingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityFinishingCyclesBinding

class FinishingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFinishingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }

