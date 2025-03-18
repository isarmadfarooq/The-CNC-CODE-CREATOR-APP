package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleXdirectionG72Binding

class InternalRoughingCycle_X_Direction_G72 : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleXdirectionG72Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleXdirectionG72Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
