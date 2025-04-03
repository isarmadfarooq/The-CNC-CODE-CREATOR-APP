package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleZdirectionLcApproachBinding
import kotlin.math.sqrt

class EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LC_APPROACH : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityExternalFinishingCycleZdirectionLcApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding and set the root view as the content view
        binding = ActivityExternalFinishingCycleZdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from the previous activity
        retrievePreviousActivityData()

        // Set click listeners for the buttons
        setButtonListeners()
    }

    private fun retrievePreviousActivityData() {
        val bundle = intent.extras
        bundle?.let {
            // No need to set hardcoded values here as they are already in the XML layout, just update if necessary
            updateEditText(binding.SParameterEt, it.getString("S_Parameter"))
            updateEditText(binding.FParameterEt, it.getString("F_Parameter"))
            updateEditText(binding.g0XET, it.getString("G0_X"))
            updateEditText(binding.g0ZEt, it.getString("G0_Z"))
            updateEditText(binding.secondXET, it.getString("Second_X"))
            updateEditText(binding.secondZEt, it.getString("Second_Z"))
            updateEditText(binding.secondREt, it.getString("Second_R"))
            updateEditText(binding.thirdXET, it.getString("Third_X"))
            updateEditText(binding.thirdZEt, it.getString("Third_Z"))
            updateEditText(binding.thirdREt, it.getString("Third_R"))
            updateEditText(binding.fourthXET, it.getString("Fourth_X"))
            updateEditText(binding.fourthZEt, it.getString("Fourth_Z"))
            updateEditText(binding.fourthREt, it.getString("Fourth_R"))
            updateEditText(binding.fifthXET, it.getString("Fifth_X"))
            updateEditText(binding.fifthZEt, it.getString("Fifth_Z"))
            updateEditText(binding.fifthREt, it.getString("Fifth_R"))
            updateEditText(binding.sixthXET, it.getString("Sixth_X"))
            updateEditText(binding.sixthZEt, it.getString("Sixth_Z"))
            updateEditText(binding.sixthREt, it.getString("Sixth_R"))
            updateEditText(binding.seventhXET, it.getString("Seventh_X"))
            updateEditText(binding.seventhZEt, it.getString("Seventh_Z"))
            updateEditText(binding.seventhREt, it.getString("Seventh_R"))
            updateEditText(binding.eighthXET, it.getString("Eighth_X"))
            updateEditText(binding.eighthZEt, it.getString("Eighth_Z"))
            updateEditText(binding.eighthREt, it.getString("Eighth_R"))
            updateEditText(binding.ninthXET, it.getString("Ninth_X"))
            updateEditText(binding.ninthZEt, it.getString("Ninth_Z"))
            updateEditText(binding.ninthREt, it.getString("Ninth_R"))
            updateEditText(binding.tenthXET, it.getString("Tenth_X"))
            updateEditText(binding.tenthZEt, it.getString("Tenth_Z"))
            updateEditText(binding.tenthREt, it.getString("Tenth_R"))
            updateEditText(binding.eleventhXET, it.getString("Eleventh_X"))
            updateEditText(binding.eleventhZEt, it.getString("Eleventh_Z"))
            updateEditText(binding.eleventhREt, it.getString("Eleventh_R"))
            updateEditText(binding.endPositionXEt, it.getString("EndPosition_X"))
            updateEditText(binding.endPositionZEt, it.getString("EndPosition_Z"))
        }
    }

    private fun updateEditText(editText: EditText, value: String?) {
        value?.let {
            editText.setText(it)
        }
    }

    private fun setButtonListeners() {
        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                val gCode = generateGCode()
                showGCodeDialog(gCode)
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun areAllFieldsFilled(): Boolean {
        return binding.SParameterEt.text.toString().isNotEmpty() &&
                binding.FParameterEt.text.toString().isNotEmpty() &&
                binding.g0XET.text.toString().isNotEmpty() &&
                binding.g0ZEt.text.toString().isNotEmpty() &&
                binding.secondXET.text.toString().isNotEmpty() &&
                binding.secondZEt.text.toString().isNotEmpty() &&
                binding.secondREt.text.toString().isNotEmpty() &&
                binding.thirdXET.text.toString().isNotEmpty() &&
                binding.thirdZEt.text.toString().isNotEmpty() &&
                binding.thirdREt.text.toString().isNotEmpty() &&
                binding.fourthXET.text.toString().isNotEmpty() &&
                binding.fourthZEt.text.toString().isNotEmpty() &&
                binding.fourthREt.text.toString().isNotEmpty() &&
                binding.fifthXET.text.toString().isNotEmpty() &&
                binding.fifthZEt.text.toString().isNotEmpty() &&
                binding.fifthREt.text.toString().isNotEmpty() &&
                binding.sixthXET.text.toString().isNotEmpty() &&
                binding.sixthZEt.text.toString().isNotEmpty() &&
                binding.sixthREt.text.toString().isNotEmpty() &&
                binding.seventhXET.text.toString().isNotEmpty() &&
                binding.seventhZEt.text.toString().isNotEmpty() &&
                binding.seventhREt.text.toString().isNotEmpty() &&
                binding.eighthXET.text.toString().isNotEmpty() &&
                binding.eighthZEt.text.toString().isNotEmpty() &&
                binding.eighthREt.text.toString().isNotEmpty() &&
                binding.ninthXET.text.toString().isNotEmpty() &&
                binding.ninthZEt.text.toString().isNotEmpty() &&
                binding.ninthREt.text.toString().isNotEmpty() &&
                binding.tenthXET.text.toString().isNotEmpty() &&
                binding.tenthZEt.text.toString().isNotEmpty() &&
                binding.tenthREt.text.toString().isNotEmpty() &&
                binding.eleventhXET.text.toString().isNotEmpty() &&
                binding.eleventhZEt.text.toString().isNotEmpty() &&
                binding.eleventhREt.text.toString().isNotEmpty() &&
                binding.endPositionXEt.text.toString().isNotEmpty() &&
                binding.endPositionZEt.text.toString().isNotEmpty()
    }

    private fun generateGCode(): String {
        val noseRadius = 0.4 // Example value, replace with actual value if needed
        val Ra = 3.2 // Example value, replace with actual value if needed

        // Calculate F parameter using the formula
        val fValue = sqrt((18 * noseRadius * Ra) / 1000.0)

        // Generate G-Code
        val gCodeBuilder = StringBuilder()
        gCodeBuilder.append("T0303 (choosed insert, but if user put tool comment that comment showing)\n")
        gCodeBuilder.append("G54\n")
        gCodeBuilder.append("// zero point G54\n")
        gCodeBuilder.append("G96 S${binding.SParameterEt.text} M3\n")
        gCodeBuilder.append("G18 G99\n")
        gCodeBuilder.append("M8\n")
        gCodeBuilder.append("G0 G41 X${binding.g0XET.text} Z${binding.g0ZEt.text}\n")
        gCodeBuilder.append("G1 X${binding.secondXET.text} Z${binding.secondZEt.text} F$fValue\n")
        gCodeBuilder.append("G1 X${binding.secondXET.text} Z${binding.secondZEt.text}\n")
        gCodeBuilder.append("G2 X${binding.secondXET.text} Z${binding.secondZEt.text} R${binding.secondREt.text}\n")
        gCodeBuilder.append("G1 X${binding.thirdXET.text} Z${binding.thirdZEt.text}\n")
        gCodeBuilder.append("G3 X${binding.thirdXET.text} Z${binding.thirdZEt.text} R${binding.thirdREt.text}\n")
        gCodeBuilder.append("G1 X${binding.fourthXET.text} Z${binding.fourthZEt.text}\n")
        gCodeBuilder.append("G2 X${binding.fourthXET.text} Z${binding.fourthZEt.text} R${binding.fourthREt.text}\n")
        gCodeBuilder.append("G1 X${binding.fifthXET.text} Z${binding.fifthZEt.text}\n")
        gCodeBuilder.append("G1 X${binding.sixthXET.text}\n")
        gCodeBuilder.append("G0 X${binding.g0XET.text} Z${binding.g0ZEt.text}\n")
        gCodeBuilder.append("M9\n")
        gCodeBuilder.append("G0 G40 X200 Z100\n")
        gCodeBuilder.append("M1\n")
        gCodeBuilder.append("// surface speed 200, and rolling direction M3\n")
        gCodeBuilder.append("// X and Z axis, feed per round\n")
        gCodeBuilder.append("// coolant on\n")
        gCodeBuilder.append("// start P with nose compensation on\n")
        gCodeBuilder.append("// 2nd line from display\n")
        gCodeBuilder.append("// 3rd line start\n")
        gCodeBuilder.append("// 3rd line end with R2. round\n")
        gCodeBuilder.append("// 4th line start\n")
        gCodeBuilder.append("// 4th line end with R1. round\n")
        gCodeBuilder.append("// 5th line start\n")
        gCodeBuilder.append("// 5th line end with R3. round\n")
        gCodeBuilder.append("// 6th line\n")
        gCodeBuilder.append("// end Q\n")
        gCodeBuilder.append("// end of finishing\n")
        gCodeBuilder.append("// coolant off\n")
        gCodeBuilder.append("// nose compensation off and back to tool retract position\n")
        gCodeBuilder.append("// option stop\n")
        gCodeBuilder.append("// empty line\n")

        return gCodeBuilder.toString()
    }

    private fun showGCodeDialog(gCode: String) {
        AlertDialog.Builder(this)
            .setTitle("Generated G-Code")
            .setMessage(gCode)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish() // Finish this activity and go back to the main activity
            }
            .show()
    }
}