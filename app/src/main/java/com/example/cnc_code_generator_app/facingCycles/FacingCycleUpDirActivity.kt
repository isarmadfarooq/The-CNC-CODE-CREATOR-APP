package com.example.cnc_code_generator_app.facingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleUpDirBinding

class FacingCycleUpDirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleUpDirBinding

    // Variable to track the selected insert (null if none selected)
    private var selectedInsert: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityFacingCycleUpDirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pre-fill default data in all fields
        prefillDefaultData()

        // Set up insert selection buttons.
        binding.cnmg80Btn.setOnClickListener {
            selectInsert("CCMT 80", binding.cnmg80Btn)
        }
        binding.wnmg80Btn.setOnClickListener {
            selectInsert("WNMG 80", binding.wnmg80Btn)
        }
        binding.tnmgBtn.setOnClickListener {
            selectInsert("TNMG 60", binding.tnmgBtn)
        }
        binding.dcmtBtn.setOnClickListener {
            selectInsert("DCMT 55", binding.dcmtBtn)
        }

        // "SET" button: Validate required fields and, if valid, pass data to next activity.
        binding.setBtn.setOnClickListener {
            if (!areRequiredFieldsValid()) {
                Toast.makeText(
                    this,
                    "Please fill in all required fields and select an insert.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Create an intent and add extras from the UI.
            val intent = Intent(this, FacingCycleUpDirectionG94::class.java).apply {
                putExtra("SELECTED_INSERT", selectedInsert)
                putExtra("NOSE_COMP", binding.noseCompEt.text.toString().trim())
                putExtra("NOSE_RADIUS", binding.noseRadiusEt.text.toString().trim())
                putExtra("FINISHING_ALLOWANCE", binding.finishingAllowanceEt.text.toString().trim())
                putExtra("ZERO_POINT", binding.zeroPointEt.text.toString().trim())
                putExtra("TOOL_NUMBER", binding.toolEt.text.toString().trim())
                putExtra("COOLANT_ON", binding.coolantOnEt.text.toString().trim())
                putExtra("COOLANT_OFF", binding.coolantOffEt.text.toString().trim())
                putExtra("SPINDLE_DIR", binding.spindleDirEt.text.toString().trim())
                putExtra("OPTION_STOP", binding.optionStopEt.text.toString().trim())
                putExtra("TOOL_RETRACTION_X", binding.toolRetractionXEt.text.toString().trim())
                putExtra("TOOL_RETRACTION_Z", binding.toolRetractionZEt.text.toString().trim())
                putExtra("TOOL_COMMENT", binding.toolCommentEt.text.toString().trim())
            }
            startActivity(intent)
        }

        // "DELETE" button: Clear all input fields and reset insert selection.
        binding.deleteBtn.setOnClickListener {
            clearAllFields()
        }
    }

    /**
     * Pre-fill default data into all fields.
     */
    private fun prefillDefaultData() {
        binding.noseCompEt.setText("G42")
        binding.noseRadiusEt.setText("0.8")
        binding.finishingAllowanceEt.setText("0.2")
        binding.zeroPointEt.setText("G54")
        binding.toolEt.setText("0101")
        binding.coolantOnEt.setText("M8")
        binding.coolantOffEt.setText("M9")
        binding.spindleDirEt.setText("M3")
        binding.optionStopEt.setText("M1")
        binding.toolRetractionXEt.setText("X200")
        binding.toolRetractionZEt.setText("Z100")
        binding.toolCommentEt.setText("0001")
    }

    private fun selectInsert(insertName: String, selectedButton: androidx.appcompat.widget.AppCompatButton) {
        selectedInsert = insertName
        resetInsertButtons()
        selectedButton.text = "OK"
        selectedButton.setBackgroundColor(Color.parseColor("#EA509CD3"))
    }

    private fun resetInsertButtons() {
        binding.cnmg80Btn.text = "TAKE"
        binding.cnmg80Btn.setBackgroundColor(Color.parseColor("#ABE4ED"))
        binding.wnmg80Btn.text = "TAKE"
        binding.wnmg80Btn.setBackgroundColor(Color.parseColor("#ABE4ED"))
        binding.tnmgBtn.text = "TAKE"
        binding.tnmgBtn.setBackgroundColor(Color.parseColor("#ABE4ED"))
        binding.dcmtBtn.text = "TAKE"
        binding.dcmtBtn.setBackgroundColor(Color.parseColor("#ABE4ED"))
    }

    private fun areRequiredFieldsValid(): Boolean {
        if (selectedInsert.isNullOrEmpty()) return false

        with(binding) {
            if (noseCompEt.text.isNullOrBlank()) return false
            if (noseRadiusEt.text.isNullOrBlank()) return false
            if (finishingAllowanceEt.text.isNullOrBlank()) return false
            if (zeroPointEt.text.isNullOrBlank()) return false
            if (toolEt.text.isNullOrBlank()) return false
            if (coolantOnEt.text.isNullOrBlank()) return false
            if (coolantOffEt.text.isNullOrBlank()) return false
            if (spindleDirEt.text.isNullOrBlank()) return false
            if (optionStopEt.text.isNullOrBlank()) return false
            if (toolRetractionXEt.text.isNullOrBlank()) return false
            if (toolRetractionZEt.text.isNullOrBlank()) return false
            if (toolCommentEt.text.isNullOrBlank()) return false
        }
        return true
    }

    private fun clearAllFields() {
        selectedInsert = null
        resetInsertButtons()
        with(binding) {
            noseCompEt.setText("")
            noseRadiusEt.setText("")
            finishingAllowanceEt.setText("")
            zeroPointEt.setText("")
            toolEt.setText("")
            coolantOnEt.setText("")
            coolantOffEt.setText("")
            spindleDirEt.setText("")
            optionStopEt.setText("")
            toolRetractionXEt.setText("")
            toolRetractionZEt.setText("")
            toolCommentEt.setText("")
        }
        Toast.makeText(this, "All data cleared!", Toast.LENGTH_SHORT).show()
    }
}