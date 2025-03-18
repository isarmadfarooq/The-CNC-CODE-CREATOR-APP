package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalRouhingCycleXdirApproachBinding

class InternalRoughingCycle_X_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRouhingCycleXdirApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRouhingCycleXdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
