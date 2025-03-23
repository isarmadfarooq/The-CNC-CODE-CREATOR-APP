package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleZdirectionG71Binding

class ExternalRoughingCycle_Z_DirectionG71 : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleZdirectionG71Binding

    // State variables
    private var selectedInsert: String? = null
    private var depthOfCut: Double = 0.0
    private var noseRadius: Double = 0.0
    private var feed: Double = 0.0

    // Some typical defaults
    private var finishingAllowanceX: Double = 0.3
    private var finishingAllowanceZ: Double = 0.1
    private var retractOfRoughing: Double = 0.5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize View Binding
        binding = ActivityExternalRoughingCycleZdirectionG71Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup the "Take" / "OK" logic for each insert
        setupInsertButtons()

        // Set button -> collect input, do calculations, build G code, and move on
        binding.setBtn.setOnClickListener {
            if (validateInputs()) {
                collectInputs()
                doCalculations()
                val finalCodeFanuc = buildFanucProgram()
                val finalCodeHaas = buildHaasProgram()

                // Pass the final code to another activity
                val intent = Intent(this, ExternalRougingCycle_Z_DirApproach::class.java)
                intent.putExtra("FANUC_CODE", finalCodeFanuc)
                intent.putExtra("HAAS_CODE", finalCodeHaas)
                startActivity(intent)
            }
        }

        // Delete or cancel
        binding.deleteBtn.setOnClickListener {
            finish()
        }
    }

    /**
     * Setup each insert's "TAKE" button:
     * When clicked, it becomes "OK", background color changes to #EA509CD3,
     * and the other insert buttons revert to "TAKE" + default color.
     */
    private fun setupInsertButtons() {
        // CNMG
        binding.cnmg80Btn.setOnClickListener {
            selectedInsert = "CNMG80"
            depthOfCut = 2.2
            setInsertButtonUI(binding.cnmg80Btn)
            revertOtherInsertButtons(binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn)
        }

        // DNMG
        binding.wnmg80Btn.setOnClickListener {
            selectedInsert = "DNMG55"
            depthOfCut = 2.0
            setInsertButtonUI(binding.wnmg80Btn)
            revertOtherInsertButtons(binding.cnmg80Btn, binding.tnmgBtn, binding.dnmgBtn)
        }

        // TNMG
        binding.tnmgBtn.setOnClickListener {
            selectedInsert = "TNMG60"
            depthOfCut = 1.7
            setInsertButtonUI(binding.tnmgBtn)
            revertOtherInsertButtons(binding.cnmg80Btn, binding.wnmg80Btn, binding.dnmgBtn)
        }

        // VBMT
        binding.dnmgBtn.setOnClickListener {
            selectedInsert = "VBMT35"
            depthOfCut = 1.4
            setInsertButtonUI(binding.dnmgBtn)
            revertOtherInsertButtons(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn)
        }
    }

    /**
     * Helper: Set this insert button to "OK" and change background to #EA509CD3.
     */
    private fun setInsertButtonUI(button: androidx.appcompat.widget.AppCompatButton) {
        button.text = "OK"
        button.setBackgroundColor(Color.parseColor("#EA509CD3"))
    }

    /**
     * Helper: Revert other buttons to "TAKE" and reset background color.
     * You can set the background color to any default you wish (e.g., R.color.lite_blue).
     */
    private fun revertOtherInsertButtons(vararg buttons: androidx.appcompat.widget.AppCompatButton) {
        buttons.forEach { btn ->
            btn.text = getString(com.example.cnc_code_generator_app.R.string.take)
            // If you have a drawable or color resource for default, use that instead:
            btn.setBackgroundColor(Color.parseColor("#D8E6FF"))
            // ^ Example: a light blue. Replace with your actual default color/drawable.
        }
    }

    /**
     * Validate user inputs.
     * @return true if all required fields are filled, false otherwise.
     */
    private fun validateInputs(): Boolean {
        if (selectedInsert == null) {
            Toast.makeText(this, "Please select an insert.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.noseRadiusEt.text.toString().isBlank()) {
            Toast.makeText(this, "Please enter the nose radius.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.toolEt.text.toString().isBlank()) {
            Toast.makeText(this, "Please enter the tool number.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.zeroPointEt.text.toString().isBlank()) {
            Toast.makeText(this, "Please enter the zero point.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /**
     * Collect user inputs from EditTexts and store them in variables.
     */
    private fun collectInputs() {
        // Nose radius
        noseRadius = binding.noseRadiusEt.text.toString().toDoubleOrNull() ?: 0.8

        // Optionally read finishing allowances if you allow user to override
        // finishingAllowanceX = ...
        // finishingAllowanceZ = ...

        // (You can read other values from the UI as needed)
    }

    /**
     * Do your feed, speed, etc. calculations.
     * Example from your doc: feed = 0.35 * noseRadius
     */
    private fun doCalculations() {
        feed = 0.35 * noseRadius
    }

    /**
     * Build the final G-code in Fanuc style.
     */
    private fun buildFanucProgram(): String {
        // Retrieve other user inputs from UI or state
        val toolNumber = binding.toolEt.text.toString().ifBlank { "0202" }
        val toolComment = binding.toolCommentEt.text.toString().ifBlank { "" }
        val zeroPoint = binding.zeroPointEt.text.toString().ifBlank { "G54" }
        val spindleDir = binding.spindleDirEt.text.toString().ifBlank { "M3" }
        val coolantOn = binding.coolantOnEt.text.toString().ifBlank { "M8" }
        val coolantOff = binding.coolantOffEt.text.toString().ifBlank { "M9" }
        val optionStop = binding.optionStopEt.text.toString().ifBlank { "M1" }

        return """
            (EXT. ROUGH Z)
            T$toolNumber($toolComment);
            $zeroPoint; // zero point
            G96S200$spindleDir; // surface speed 200, M3
            G18G99; // XZ-plane, feed per revolution
            $coolantOn; // coolant on
            G0G42X52.Z3.; // start with nose comp on
            G71U${depthOfCut}R${retractOfRoughing}; // roughing cycle 1st line
            G71P10Q20U${finishingAllowanceX}W${finishingAllowanceZ}F${String.format("%.3f", feed)}; // roughing cycle 2nd line

            N10G1X30.Z0.F${String.format("%.3f", feed)};
            G1Z-18.;
            G2X34.Z-20.R2.;
            G1X38.;
            G3X40.Z-21.R1.;
            G1Z-30.;
            G1X50.Z-30.;
            N20G1X52.;

            G70P10Q20; // finishing cycle if chosen
            G0X52.Z3.; // end finishing
            $coolantOff; // coolant off
            G0G40X200.Z100.; // nose comp off, retract
            $optionStop; // optional stop
        """.trimIndent()
    }

    /**
     * Build the final G-code in Haas style.
     */
    private fun buildHaasProgram(): String {
        val toolNumber = binding.toolEt.text.toString().ifBlank { "0202" }
        val toolComment = binding.toolCommentEt.text.toString().ifBlank { "" }
        val zeroPoint = binding.zeroPointEt.text.toString().ifBlank { "G54" }
        val spindleDir = binding.spindleDirEt.text.toString().ifBlank { "M3" }
        val coolantOn = binding.coolantOnEt.text.toString().ifBlank { "M8" }
        val coolantOff = binding.coolantOffEt.text.toString().ifBlank { "M9" }
        val optionStop = binding.optionStopEt.text.toString().ifBlank { "M1" }

        return """
            (EXT. ROUGH Z)
            T$toolNumber($toolComment);
            $zeroPoint; // zero point
            G96S200$spindleDir; // surface speed 200, M3
            G18G99; // XZ-plane, feed per revolution
            $coolantOn; // coolant on
            G0G42X52.Z3.; // start with nose comp on
            G71P10Q20U${finishingAllowanceX}W${finishingAllowanceZ}D${depthOfCut} F${String.format("%.3f", feed)}; // rough cycle (Haas style)

            N10G1X30.Z0.F${String.format("%.3f", feed)};
            G1Z-18.;
            G2X34.Z-20.R2.;
            G1X38.;
            G3X40.Z-21.R1.;
            G1Z-30.;
            G1X50.Z-30.;
            N20G1X52.;

            G70P10Q20; // finishing cycle if chosen
            G0X52.Z3.; // end finishing
            $coolantOff; // coolant off
            G0G40X200.Z100.; // nose comp off, retract
            $optionStop; // optional stop
        """.trimIndent()
    }
}