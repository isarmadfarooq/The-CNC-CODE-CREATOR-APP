package com.example.cnc_code_generator_app.facingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleDonwDirectionG94Binding

class FacingCycleDonwDirectionG94 : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleDonwDirectionG94Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityFacingCycleDonwDirectionG94Binding.inflate(layoutInflater)
        setContentView(binding.root)

        }
}
