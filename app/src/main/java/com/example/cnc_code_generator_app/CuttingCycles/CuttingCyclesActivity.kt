package com.example.cnc_code_generator_app.CuttingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityCuttingCyclesBinding

class CuttingCyclesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCuttingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCuttingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }



