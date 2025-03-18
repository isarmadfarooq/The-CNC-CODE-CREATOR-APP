package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalRouhingCycleXdirApproachBinding

class ExternalRouhingCycle_X_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRouhingCycleXdirApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRouhingCycleXdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
