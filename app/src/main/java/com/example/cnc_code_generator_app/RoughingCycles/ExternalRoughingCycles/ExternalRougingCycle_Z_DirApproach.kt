package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalRougingCycleZdirApproachBinding

class ExternalRougingCycle_Z_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRougingCycleZdirApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRougingCycleZdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
