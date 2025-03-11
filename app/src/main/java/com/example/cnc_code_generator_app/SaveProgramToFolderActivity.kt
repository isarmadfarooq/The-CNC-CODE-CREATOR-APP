package com.example.cnc_code_generator_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivitySaveProgramToFolderBinding

class SaveProgramToFolderActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveProgramToFolderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveProgramToFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }
    }
