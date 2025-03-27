package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleXdirectionLongCodeBinding

class EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE : AppCompatActivity() {

    // 1) Declare your binding variable
    private lateinit var binding: ActivityExternalFinishingCycleXdirectionLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2) Inflate the binding and set the root as content view
        binding = ActivityExternalFinishingCycleXdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
