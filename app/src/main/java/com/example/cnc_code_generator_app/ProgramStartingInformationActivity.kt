package com.example.cnc_code_generator_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityProgramStartingInformationBinding

class ProgramStartingInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramStartingInformationBinding  // auto-generated from activity_program_starting_information.xml
    private val data = ProgramRepository.currentProgramData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramStartingInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupControlButtons()
        setupMaterialButtons()
        setupInputListeners()
        preFillData()

        binding.setBtn.setOnClickListener {
            if (validateInputs()) {
                saveData()
                // If not already added, add "1. START PROGRAM" to the program structure
                if (!ProgramRepository.programStructure.contains("1. START PROGRAM")) {
                    ProgramRepository.programStructure.add("1. START PROGRAM")
                }
                showSummaryDialog()
            }
        }

        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "Start program canceled.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // -------------------------
    // Setup Control Type Buttons
    // -------------------------
    private fun setupControlButtons() {
        binding.selectFauncTv.setOnClickListener {
            data.controlType = "FANUC"
            binding.selectFauncTv.text = "OK"
            binding.selectFauncTv.setBackgroundResource(R.color.blue)
            binding.selectHassTv.text = getString(R.string.take)
            binding.selectHassTv.setBackgroundResource(R.color.lite_blue)
            updateSummary()
        }

        binding.selectHassTv.setOnClickListener {
            data.controlType = "HAAS"
            binding.selectHassTv.text = "OK"
            binding.selectHassTv.setBackgroundResource(R.color.blue)
            binding.selectFauncTv.text = getString(R.string.take)
            binding.selectFauncTv.setBackgroundResource(R.color.lite_blue)
            updateSummary()
        }
    }

    // -------------------------
    // Setup Material Buttons and update final surface speed
    // -------------------------
    private fun setupMaterialButtons() {
        binding.selectSteelTv.setOnClickListener {
            data.material = "STEEL"
            binding.selectSteelTv.text = "OK"
            binding.selectSteelTv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectSteelTv)
            updateFinalSurfaceSpeed(1.0)
        }

        binding.selectAluminiumTv.setOnClickListener {
            data.material = "ALUMINIUM"
            binding.selectAluminiumTv.text = "OK"
            binding.selectAluminiumTv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAluminiumTv)
            updateFinalSurfaceSpeed(1.5)
        }

        binding.selectAisi303Tv.setOnClickListener {
            data.material = "AISI_303"
            binding.selectAisi303Tv.text = "OK"
            binding.selectAisi303Tv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAisi303Tv)
            updateFinalSurfaceSpeed(0.8)
        }

        binding.selectAisi316Tv.setOnClickListener {
            data.material = "AISI_316"
            binding.selectAisi316Tv.text = "OK"
            binding.selectAisi316Tv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAisi316Tv)
            updateFinalSurfaceSpeed(0.65)
        }
    }

    // Helper function to reset other material buttons to default "Take" state.
    private fun resetMaterialButtonsExcept(selected: Button) {
        val buttons = listOf(
            binding.selectSteelTv,
            binding.selectAluminiumTv,
            binding.selectAisi303Tv,
            binding.selectAisi316Tv
        )
        buttons.filter { it != selected }.forEach { btn ->
            btn.text = getString(R.string.take)
            btn.setBackgroundResource(R.color.lite_blue)
        }
    }

    // Update final surface speed using multiplier factor and update summary.
    private fun updateFinalSurfaceSpeed(factor: Double) {
        data.finalSurfaceSpeed = (data.defaultSurfaceSpeed * factor).toInt()
        Log.d("ProgramInfo", "Final surface speed updated to: ${data.finalSurfaceSpeed}")
        updateSummary()
    }

    // -------------------------
    // Listen to input changes to update summary dynamically.
    // -------------------------
    private fun setupInputListeners() {
        // Example: Update summary when spindle limit loses focus.
        binding.spindleDirEt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                updateSummary()
            }
        }
        // You can add similar listeners for other fields as needed.
    }

    // -------------------------
    // Pre-fill input fields with current data.
    // -------------------------
    @SuppressLint("SetTextI18n")
    private fun preFillData() {
        binding.zeroPointEt.setText(data.zeroPoint)
        binding.spindleDirEt.setText(data.spindleLimit.toString())
        binding.coolantOnEt.setText(data.coolantOn)
        binding.coolantOffEt.setText(data.coolantOff)
        binding.spindleDirEt.setText(data.spindleDir)
        binding.optionStopEt.setText(data.optionStop)
        binding.blancDiaEt.setText(data.blankDia.toString())
        binding.faceAllowEt.setText(data.faceAllowance.toString())
        binding.toolRetractionXEt.setText(data.toolRetractionX.toString())
        binding.toolRetractionZEt.setText(data.toolRetractionZ.toString())
        binding.toolCommentEt.setText(data.programNumber.toString())

        // Reflect pre-selected control type
        if (data.controlType == "FANUC") {
            binding.selectFauncTv.text = "OK"
            binding.selectFauncTv.setBackgroundResource(R.color.blue)
        } else {
            binding.selectHassTv.text = "OK"
            binding.selectHassTv.setBackgroundResource(R.color.blue)
        }

        // Reflect pre-selected material
        when (data.material) {
            "STEEL" -> {
                binding.selectSteelTv.text = "OK"
                binding.selectSteelTv.setBackgroundResource(R.color.blue)
            }
            "ALUMINIUM" -> {
                binding.selectAluminiumTv.text = "OK"
                binding.selectAluminiumTv.setBackgroundResource(R.color.blue)
            }
            "AISI_303" -> {
                binding.selectAisi303Tv.text = "OK"
                binding.selectAisi303Tv.setBackgroundResource(R.color.blue)
            }
            "AISI_316" -> {
                binding.selectAisi316Tv.text = "OK"
                binding.selectAisi316Tv.setBackgroundResource(R.color.blue)
            }
        }
        updateSummary()
    }

    // -------------------------
    // Update the summary TextView (assuming a summaryTv exists in your layout)
    // -------------------------
    @SuppressLint("SetTextI18n")
    private fun updateSummary() {
        val summary = """
            Control Type: ${data.controlType}
            Material: ${data.material}
            Zero Point: ${data.zeroPoint}
            Spindle Limit: ${data.spindleLimit}
            Coolant On: ${data.coolantOn}
            Coolant Off: ${data.coolantOff}
            Spindle Direction: ${data.spindleDir}
            Option Stop: ${data.optionStop}
            Blank Diameter: ${data.blankDia}
            Face Allowance: ${data.faceAllowance}
            Tool Retraction: X ${data.toolRetractionX} , Z ${data.toolRetractionZ}
            Program Number: ${data.programNumber}
            Final Surface Speed: ${data.finalSurfaceSpeed}
        """.trimIndent()
        // Update summary TextView if it exists; otherwise, you may log it or ignore.
        binding.summaryTv?.text = summary
    }

    // -------------------------
    // Validate user inputs before saving data.
    // -------------------------
    private fun validateInputs(): Boolean {
        // Example: Validate spindle limit is between 500 and 4000.
        val spindleLimitStr = binding.spindleDirEt.text.toString()
        val spindleLimit = spindleLimitStr.toIntOrNull()
        if (spindleLimit == null || spindleLimit < 500 || spindleLimit > 4000) {
            Toast.makeText(this, "Spindle limit must be between 500 and 4000", Toast.LENGTH_LONG).show()
            return false
        }
        // You can add more validations for other fields if needed.
        return true
    }

    // -------------------------
    // Save data from input fields into the shared data model.
    // -------------------------
    private fun saveData() {
        data.zeroPoint = binding.zeroPointEt.text.toString().ifEmpty { "G54" }
        data.spindleLimit = binding.spindleDirEt.text.toString().toIntOrNull()?.coerceIn(500, 4000) ?: 2000
        data.coolantOn = binding.coolantOnEt.text.toString().ifEmpty { "M8" }
        data.coolantOff = binding.coolantOffEt.text.toString().ifEmpty { "M9" }
        data.spindleDir = binding.spindleDirEt.text.toString().ifEmpty { "M3" }
        data.optionStop = binding.optionStopEt.text.toString().ifEmpty { "M1" }
        data.blankDia = binding.blancDiaEt.text.toString().toDoubleOrNull()?.coerceIn(5.0, 500.0) ?: 50.0
        data.faceAllowance = binding.faceAllowEt.text.toString().toDoubleOrNull()?.coerceIn(1.0, 50.0) ?: 1.5
        data.toolRetractionX = binding.toolRetractionXEt.text.toString().toDoubleOrNull()?.coerceIn(50.0, 500.0) ?: 200.0
        data.toolRetractionZ = binding.toolRetractionZEt.text.toString().toDoubleOrNull()?.coerceIn(50.0, 500.0) ?: 100.0
        data.programNumber = binding.toolCommentEt.text.toString().toIntOrNull() ?: 1

        updateSummary()
    }

    // -------------------------
    // Show a summary dialog before finishing the activity.
    // -------------------------
    private fun showSummaryDialog() {
        val summary = binding.summaryTv?.text?.toString() ?: "No summary available."
        AlertDialog.Builder(this)
            .setTitle("Confirm Program Settings")
            .setMessage(summary)
            .setPositiveButton("Confirm") { _, _ ->
                Toast.makeText(this, "Program info set!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Edit", null)
            .show()
    }
}
