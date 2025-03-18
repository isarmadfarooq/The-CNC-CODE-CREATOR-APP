package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleAdvanceLongCodeBinding

class ExternalRoughingCycleAdvanceLongCode : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleAdvanceLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRoughingCycleAdvanceLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
