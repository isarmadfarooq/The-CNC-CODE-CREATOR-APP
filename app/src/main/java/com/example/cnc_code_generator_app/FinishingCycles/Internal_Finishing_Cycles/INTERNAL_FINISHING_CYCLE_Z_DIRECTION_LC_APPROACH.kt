package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleZdirectionLcApproachBinding

class INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LC_APPROACH : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleZdirectionLcApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalFinishingCycleZdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
