package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleZdirApproachBinding

class InternalRoughingCycle_Z_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleZdirApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleZdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
