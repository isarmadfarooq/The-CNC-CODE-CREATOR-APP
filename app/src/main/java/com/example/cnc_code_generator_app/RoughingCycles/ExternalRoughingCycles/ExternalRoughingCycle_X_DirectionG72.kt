package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleXdirectionG72Binding

class ExternalRoughingCycle_X_DirectionG72 : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleXdirectionG72Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRoughingCycleXdirectionG72Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
