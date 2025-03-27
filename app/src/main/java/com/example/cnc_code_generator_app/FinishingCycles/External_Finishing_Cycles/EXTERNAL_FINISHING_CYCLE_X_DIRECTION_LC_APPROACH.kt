package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleXdirectionLcApproachBinding

class EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LC_APPROACH : AppCompatActivity() {


    private lateinit var binding: ActivityExternalFinishingCycleXdirectionLcApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityExternalFinishingCycleXdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
