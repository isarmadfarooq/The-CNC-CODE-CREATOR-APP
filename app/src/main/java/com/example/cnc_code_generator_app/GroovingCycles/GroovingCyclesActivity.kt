package com.example.cnc_code_generator_app.GroovingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityGroovingCyclesBinding

class GroovingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroovingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGroovingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
