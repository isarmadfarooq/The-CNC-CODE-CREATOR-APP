package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleZdirectionLongCodeBinding

class EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityExternalFinishingCycleZdirectionLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding and set the root view as the content view
        binding = ActivityExternalFinishingCycleZdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
