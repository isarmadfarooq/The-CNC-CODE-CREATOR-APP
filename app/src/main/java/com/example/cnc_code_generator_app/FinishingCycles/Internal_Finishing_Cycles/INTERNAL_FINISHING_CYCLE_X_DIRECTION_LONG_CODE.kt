package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleXdirectionLongCodeBinding

class INTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityInternalFinishingCycleXdirectionLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalFinishingCycleXdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
