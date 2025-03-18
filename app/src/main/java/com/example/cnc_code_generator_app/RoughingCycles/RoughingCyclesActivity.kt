package com.example.cnc_code_generator_app.RoughingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles.ExternalRoughingCycleAdvanceLongCode
import com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles.ExternalRoughingCycle_X_DirectionG72
import com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles.ExternalRoughingCycle_Z_DirectionG71
import com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles.InternalRoughingCycle_Advance_LCApproach
import com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles.InternalRoughingCycle_X_Direction_G72
import com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles.InternalRoughingCycle_Z_Direction_G71
import com.example.cnc_code_generator_app.databinding.ActivityRoughingCyclesBinding

class RoughingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoughingCyclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRoughingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toggling for each "TAKE" button
        setupToggleButton(binding.externalRCZDirBtn)
        setupToggleButton(binding.externalRCXDirBtn)
        setupToggleButton(binding.externalRCALCBtn)
        setupToggleButton(binding.internalRCZDirBtn)
        setupToggleButton(binding.internalRCXDirBtn)
        setupToggleButton(binding.internalRCALCBtn)

        // When SET is pressed:
        binding.btnSet.setOnClickListener {
            // 1) Gather which cycles are selected
            val selectedCycles = getSelectedCycles()

            // 2) Close this window
            finish()

            // 3) Open the chosen window(s)
            //    This is up to you. For example, if only one can be chosen at a time:
            if (selectedCycles.size == 1) {
                // Example: start an Activity for external Z direction G71 if that was chosen
                when (selectedCycles.first()) {
                    "EXTERNAL_Z_DIR" -> {
                        startActivity(Intent(this, ExternalRoughingCycle_Z_DirectionG71::class.java))
                    }
                    "EXTERNAL_X_DIR" -> {
                        startActivity(Intent(this, ExternalRoughingCycle_X_DirectionG72::class.java))
                    }
                    "EXTERNAL_ALC" -> {
                        startActivity(Intent(this, ExternalRoughingCycleAdvanceLongCode::class.java))
                    }
                    "INTERNAL_Z_DIR" -> {
                        startActivity(Intent(this, InternalRoughingCycle_Z_Direction_G71::class.java))
                    }
                    "INTERNAL_X_DIR" -> {
                        startActivity(Intent(this, InternalRoughingCycle_X_Direction_G72::class.java))
                    }
                    "INTERNAL_ALC" -> {
                        startActivity(Intent(this, InternalRoughingCycle_Advance_LCApproach::class.java))
                    }
                }
            } else {
                // If multiple cycles can be chosen, handle that scenario
                // e.g., open a multi-cycle summary screen
            }
        }

        // When CLOSE is pressed, simply finish this Activity
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    /**
     * Toggle the button text between TAKE and OK,
     * and change background color accordingly.
     */
    private fun setupToggleButton(button: androidx.appcompat.widget.AppCompatButton) {
        button.setOnClickListener {
            val currentText = button.text.toString()
            if (currentText == getString(R.string.take)) {
                // Switch to "OK" and set the background color to #EA509CD3
                button.text = getString(R.string.ok)
                button.setBackgroundColor(Color.parseColor("#EA509CD3"))
            } else {
                // Switch back to "TAKE" and restore original color
                button.text = getString(R.string.take)
                // In your XML, original color is `@color/lite_blue`.
                // We'll restore that color here:
                button.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.lite_blue)
                )
            }
        }
    }

    /**
     * Check which buttons are in the "OK" state and return a list of
     * internal identifiers for the selected cycles.
     */
    private fun getSelectedCycles(): List<String> {
        val selectedCycles = mutableListOf<String>()

        // If the button text is "OK", it is selected. We'll add an internal tag/ID.
        if (binding.externalRCZDirBtn.text == getString(R.string.ok)) {
            selectedCycles.add("EXTERNAL_Z_DIR")
        }
        if (binding.externalRCXDirBtn.text == getString(R.string.ok)) {
            selectedCycles.add("EXTERNAL_X_DIR")
        }
        if (binding.externalRCALCBtn.text == getString(R.string.ok)) {
            selectedCycles.add("EXTERNAL_ALC")
        }
        if (binding.internalRCZDirBtn.text == getString(R.string.ok)) {
            selectedCycles.add("INTERNAL_Z_DIR")
        }
        if (binding.internalRCXDirBtn.text == getString(R.string.ok)) {
            selectedCycles.add("INTERNAL_X_DIR")
        }
        if (binding.internalRCALCBtn.text == getString(R.string.ok)) {
            selectedCycles.add("INTERNAL_ALC")
        }

        return selectedCycles
    }
}
