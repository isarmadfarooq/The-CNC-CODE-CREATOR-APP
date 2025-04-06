package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleZdirectionAdvanceApproachBinding
import kotlin.math.sqrt

class EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCE_APPROACH : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityExternalFinishingCycleZdirectionAdvanceApproachBinding
    private lateinit var selectedData: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding and set the root view
        binding = ActivityExternalFinishingCycleZdirectionAdvanceApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedData = intent.getSerializableExtra("selectedData") as HashMap<String, String>

        binding.setBtn.setOnClickListener {
            if (validateInputs()) {
                val gCodes = generateGCodes()
                showGCodeDialog(gCodes)
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteBtn.setOnClickListener {
            clearAllInputs()
            Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(): Boolean {
        return binding.SParameterEt.text?.isNotEmpty() ?: false &&
                binding.FParameterEt.text?.isNotEmpty() ?: false &&
                binding.g0XET.text?.isNotEmpty() ?: false &&
                binding.g0ZEt.text?.isNotEmpty() ?: false &&
                binding.secondXET.text?.isNotEmpty() ?: false &&
                binding.secondZEt.text?.isNotEmpty() ?: false &&
                binding.secondREt.text?.isNotEmpty() ?: false &&
                binding.thirdXET.text?.isNotEmpty() ?: false &&
                binding.thirdZEt.text?.isNotEmpty() ?: false &&
                binding.thirdREt.text?.isNotEmpty() ?: false &&
                binding.fourthXET.text?.isNotEmpty() ?: false &&
                binding.fourthZEt.text?.isNotEmpty() ?: false &&
                binding.fourthREt.text?.isNotEmpty() ?: false &&
                binding.fifthXET.text?.isNotEmpty() ?: false &&
                binding.fifthZEt.text?.isNotEmpty() ?: false &&
                binding.fifthREt.text?.isNotEmpty() ?: false &&
                binding.sixthXET.text?.isNotEmpty() ?: false &&
                binding.sixthZEt.text?.isNotEmpty() ?: false &&
                binding.sixthREt.text?.isNotEmpty() ?: false &&
                binding.seventhXET.text?.isNotEmpty() ?: false &&
                binding.seventhZEt.text?.isNotEmpty() ?: false &&
                binding.seventhREt.text?.isNotEmpty() ?: false &&
                binding.eighthXET.text?.isNotEmpty() ?: false &&
                binding.eighthZEt.text?.isNotEmpty() ?: false &&
                binding.eighthREt.text?.isNotEmpty() ?: false &&
                binding.ninthXET.text?.isNotEmpty() ?: false &&
                binding.ninthZEt.text?.isNotEmpty() ?: false &&
                binding.ninthREt.text?.isNotEmpty() ?: false &&
                binding.tenthXET.text?.isNotEmpty() ?: false &&
                binding.tenthZEt.text?.isNotEmpty() ?: false &&
                binding.tenthREt.text?.isNotEmpty() ?: false &&
                binding.eleventhXET.text?.isNotEmpty() ?: false &&
                binding.eleventhZEt.text?.isNotEmpty() ?: false &&
                binding.eleventhREt.text?.isNotEmpty() ?: false &&
                binding.endPositionXEt.text?.isNotEmpty() ?: false &&
                binding.endPositionZEt.text?.isNotEmpty() ?: false
    }

    private fun generateGCodes(): String {
        // Hardcoded values for nose radius and finishing allowance
        val noseRadius = 0.4
        val surfaceRoughness = 3.2
        val feed = sqrt((18 * noseRadius * surfaceRoughness) / 1000)
        val feedFormatted = String.format("%.3f", feed)

        // Use a hardcoded value for material speed multiplier
        val materialSpeedMultiplier = 1.0
        val nominalSpeed = 200.0
        val speed = (nominalSpeed * materialSpeedMultiplier).toInt().let { "($it)" } ?: ""

        val sb = StringBuilder()
        sb.append("(EXT. FINISH ALC)\n")
        sb.append("T0303$\n")
        sb.append("G54\n")
        sb.append("G96S${speed}M3\n")
        sb.append("G18G99\n")
        sb.append("M8\n")
        sb.append("G0G41X${binding.g0XET.text}Z${binding.g0ZEt.text}\n")
        sb.append("G1X${binding.secondXET.text}Z${binding.secondZEt.text}F$feedFormatted\n")
        sb.append("G1X${binding.secondXET.text}Z-14.172\n")
        sb.append("G2X31.172Z-15.586R2.\n")
        sb.append("G1X39.414Z-19.707\n")
        sb.append("G3X40.Z-20.414R1.\n")
        sb.append("G1X40.Z-28.757\n")
        sb.append("G2X41.757Z-30.879R3.\n")
        sb.append("G1X50.Z-35.\n")
        sb.append("G1X52.\n")
        sb.append("G0X52.Z3.\n")
        sb.append("M9\n")
        sb.append("G0G40X200.Z100.\n")
        sb.append("M1\n")
        sb.append(";\n")
        sb.append("//surface speed 200, and rolling direction M3\n")
        sb.append("//X and Z axis, feed per round\n")
        sb.append("//coolant on\n")
        sb.append("//start P with nose compensation on\n")
        sb.append("//2nd line from display\n")
        sb.append("//3rd line start\n")
        sb.append("//3rd line end with R2. round\n")
        sb.append("//4th line start\n")
        sb.append("//4th line end with R1. round\n")
        sb.append("//5th line start\n")
        sb.append("//5th line end with R3. round\n")
        sb.append("//6th line\n")
        sb.append("//end Q\n")
        sb.append("//end of finishing\n")
        sb.append("//coolant off\n")
        sb.append("//nose compensation off and back to tool retract position\n")
        sb.append("//option stop\n")
        sb.append("//empty line\n")

        return sb.toString()
    }

    private fun showGCodeDialog(gCodes: String) {
        AlertDialog.Builder(this)
            .setTitle("Generated G-Codes")
            .setMessage(gCodes)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }

    private fun clearAllInputs() {
        binding.SParameterEt.text?.clear()
        binding.FParameterEt.text?.clear()
        binding.g0XET.text?.clear()
        binding.g0ZEt.text?.clear()
        binding.secondXET.text?.clear()
        binding.secondZEt.text?.clear()
        binding.secondREt.text?.clear()
        binding.thirdXET.text?.clear()
        binding.thirdZEt.text?.clear()
        binding.thirdREt.text?.clear()
        binding.fourthXET.text?.clear()
        binding.fourthZEt.text?.clear()
        binding.fourthREt.text?.clear()
        binding.fifthXET.text?.clear()
        binding.fifthZEt.text?.clear()
        binding.fifthREt.text?.clear()
        binding.sixthXET.text?.clear()
        binding.sixthZEt.text?.clear()
        binding.sixthREt.text?.clear()
        binding.seventhXET.text?.clear()
        binding.seventhZEt.text?.clear()
        binding.seventhREt.text?.clear()
        binding.eighthXET.text?.clear()
        binding.eighthZEt.text?.clear()
        binding.eighthREt.text?.clear()
        binding.ninthXET.text?.clear()
        binding.ninthZEt.text?.clear()
        binding.ninthREt.text?.clear()
        binding.tenthXET.text?.clear()
        binding.tenthZEt.text?.clear()
        binding.tenthREt.text?.clear()
        binding.eleventhXET.text?.clear()
        binding.eleventhZEt.text?.clear()
        binding.eleventhREt.text?.clear()
        binding.endPositionXEt.text?.clear()
        binding.endPositionZEt.text?.clear()
    }
}