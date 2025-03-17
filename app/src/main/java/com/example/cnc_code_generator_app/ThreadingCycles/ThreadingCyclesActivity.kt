package com.example.cnc_code_generator_app.ThreadingCycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityThreadingCyclesBinding

class ThreadingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThreadingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThreadingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
