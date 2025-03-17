package com.example.cnc_code_generator_app.RoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityRoughingCyclesBinding

class RoughingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoughingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRoughingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }
}
