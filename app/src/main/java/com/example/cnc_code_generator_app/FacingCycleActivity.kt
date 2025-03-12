package com.example.cnc_code_generator_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleBinding
import com.example.cnc_code_generator_app.databinding.ActivityMainBinding

class FacingCycleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFacingCycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFacingCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }
    }
