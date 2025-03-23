package com.example.cnc_code_generator_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityProgramStartingInformationBinding

class ProgramStartingInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramStartingInformationBinding  // auto-generated from activity_program_starting_information.xml
    private val data = ProgramRepository.currentProgramData
    private val dynamicFields = mutableListOf<EditText>()

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
        // List all EditText fields whose value changes should be reflected immediately.
        val fields = listOf(
            binding.zeroPointEt,
            binding.spindleLimitEt,
            binding.spindleDirEt,
            binding.coolantOnEt,
            binding.coolantOffEt,
            binding.optionStopEt,
            binding.blancDiaEt,
            binding.faceAllowEt,
            binding.toolRetractionXEt,
            binding.toolRetractionZEt,
            binding.toolCommentEt
        )

        fields.forEach { field ->
            field.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
                override fun afterTextChanged(s: Editable?) {
                    // Option 2: Rebuild the summary by reading directly from the views.
                    updateSummary()
                }
            })
        }
    }

    // -------------------------
    // Pre-fill input fields with current data.
    // -------------------------
    @SuppressLint("SetTextI18n")
    private fun preFillData() {
        binding.zeroPointEt.setText(data.zeroPoint)
        binding.spindleLimitEt.setText(data.spindleLimit.toString())
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
    // Add a dynamic field
    // -------------------------
    private fun addDynamicField() {
        val dynamicField = EditText(this).apply {
            hint = "Additional Parameter"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        dynamicField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                updateSummary()
            }
        })

    }

    // -------------------------
    // Update the summary TextView by reading current view values.
    // -------------------------
    @SuppressLint("SetTextI18n")
    private fun updateSummary() {
        val summary = StringBuilder().apply {
            append("Control Type: ${data.controlType}\n")
            append("Material: ${data.material}\n")
            append("Zero Point: ${binding.zeroPointEt.text}\n")
            append("Spindle Limit: ${binding.spindleLimitEt.text}\n")
            append("Coolant On: ${binding.coolantOnEt.text}\n")
            append("Coolant Off: ${binding.coolantOffEt.text}\n")
            append("Spindle Direction: ${binding.spindleDirEt.text}\n")
            append("Option Stop: ${binding.optionStopEt.text}\n")
            append("Blank Diameter: ${binding.blancDiaEt.text}\n")
            append("Face Allowance: ${binding.faceAllowEt.text}\n")
            append("Tool Retraction: X ${binding.toolRetractionXEt.text} , Z ${binding.toolRetractionZEt.text}\n")
            append("Program Number: ${binding.toolCommentEt.text}\n")
            append("Final Surface Speed: ${data.finalSurfaceSpeed}\n")
            dynamicFields.forEachIndexed { index, editText ->
                append("Dynamic Field ${index + 1}: ${editText.text}\n")
            }
        }
        binding.summaryTv?.text = summary.toString()
    }

    // -------------------------
    // Validate user inputs before saving data.
    // -------------------------
    private fun validateInputs(): Boolean {
        dynamicFields.forEachIndexed { index, editText ->
            if (editText.text.isNullOrBlank()) {
                Toast.makeText(this, "Dynamic Field ${index + 1} must not be empty", Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }

    // -------------------------
    // Save data from input fields into the shared data model.
    // -------------------------
    private fun saveData() {
        // Here you might update the data model from the view if needed.
        // For fields that require conversion, you can perform error-checking as necessary.
        data.zeroPoint = binding.zeroPointEt.text.toString().ifEmpty { "G54" }
        data.spindleLimit = binding.spindleLimitEt.text.toString().toIntOrNull() ?: 2000
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
