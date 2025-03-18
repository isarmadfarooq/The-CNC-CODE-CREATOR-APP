package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleZdirectionG71Binding

class ExternalRoughingCycle_Z_DirectionG71 : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleZdirectionG71Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRoughingCycleZdirectionG71Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
