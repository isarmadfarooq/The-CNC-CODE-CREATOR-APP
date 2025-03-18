package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleZdirectionG71Binding

class InternalRoughingCycle_Z_Direction_G71 : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleZdirectionG71Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleZdirectionG71Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
