package com.example.cnc_code_generator_app.facingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleBinding

class FacingCycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleBinding

    // Enum representing the selected cycle option
    private enum class SelectedCycle {
        NONE, DOWN, UP
    }

    // Current selection; default is NONE
    private var selectedCycle = SelectedCycle.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacingCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the Facing Cycle Down TAKE button
        binding.facingCycleDownBtn.setOnClickListener {
            selectCycle(SelectedCycle.DOWN)
        }

        // Set click listener for the Facing Cycle Up TAKE button
        binding.facingCycleUpBtn.setOnClickListener {
            selectCycle(SelectedCycle.UP)
        }

        // "SET" button launches the corresponding activity based on selection
        binding.btnSet.setOnClickListener {
            proceedWithSelection()
        }

        // "CLOSE" button finishes the current activity
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    /**
     * Resets the UI for both facing cycle options and applies the selection style
     * for the chosen option: sets background color and updates button text to "OK".
     */
    private fun selectCycle(cycle: SelectedCycle) {
        resetCycleSelectionUI()
        selectedCycle = cycle
        when (cycle) {
            SelectedCycle.DOWN -> {
                binding.facingCycleDownLL.setBackgroundColor(Color.parseColor("#EA509CD3"))
                binding.facingCycleDownBtn.text = "OK"
            }
            SelectedCycle.UP -> {
                binding.facingCycleUpLL.setBackgroundColor(Color.parseColor("#EA509CD3"))
                binding.facingCycleUpBtn.text = "OK"
            }
            else -> {} // No action needed for NONE
        }
    }

    /**
     * Resets the UI of both facing cycle options to their default state.
     * Restores the original background color and the "TAKE" button text.
     */
    private fun resetCycleSelectionUI() {
        // Using the app's green color from resources
        val defaultColor = resources.getColor(R.color.green, null)
        binding.facingCycleDownLL.setBackgroundColor(defaultColor)
        binding.facingCycleUpLL.setBackgroundColor(defaultColor)
        binding.facingCycleDownBtn.text = getString(R.string.take)
        binding.facingCycleUpBtn.text = getString(R.string.take)
    }

    /**
     * Checks if a facing cycle has been selected. If yes, launches the corresponding
     * activity (FacingCycleDownDirActivity or FacingCycleUpDirActivity). If not, shows a Toast.
     */
    private fun proceedWithSelection() {
        if (selectedCycle == SelectedCycle.NONE) {
            Toast.makeText(this, "Please select a facing cycle", Toast.LENGTH_SHORT).show()
        } else {
            val intent = when (selectedCycle) {
                SelectedCycle.DOWN -> Intent(this, FacingCycleDownDirActivity::class.java)
                SelectedCycle.UP -> Intent(this, FacingCycleUpDirActivity::class.java)
                else -> null
            }
            intent?.let {
                startActivity(it)
                finish() // Optionally finish this activity to remove it from the back stack
            }
        }
    }
}
