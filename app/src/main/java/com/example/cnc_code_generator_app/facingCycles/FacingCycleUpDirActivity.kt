package com.example.cnc_code_generator_app.facingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleUpDirBinding

class FacingCycleUpDirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleUpDirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityFacingCycleUpDirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }

