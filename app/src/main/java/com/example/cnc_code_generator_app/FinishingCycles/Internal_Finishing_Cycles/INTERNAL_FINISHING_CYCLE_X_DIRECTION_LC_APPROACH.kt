package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleXdirectionLcApproachBinding

class INTERNAL_FINISHING_CYCLE_X_DIRECTION_LC_APPROACH : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleXdirectionLcApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInternalFinishingCycleXdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrieveData()
        setupListeners()
    }

    private fun retrieveData() {
        val cnmg80 = intent.getStringExtra("CNMG80")
        val wnmg80 = intent.getStringExtra("WNMG80")
        val tnmg = intent.getStringExtra("TNMG")
        val dnmg = intent.getStringExtra("DNMG")
        val noseComp = intent.getStringExtra("NoseComp")
        // These values were passed from the previous activity, but not used directly in XML
        val noseRadius = intent.getStringExtra("NoseRadius")
        val surfaceRoughness = intent.getStringExtra("SurfaceRoughness")
        val zeroPoint = intent.getStringExtra("ZeroPoint")
        val tool = intent.getStringExtra("Tool")
        val coolantOn = intent.getStringExtra("CoolantOn")
        val coolantOff = intent.getStringExtra("CoolantOff")
        val spindleDir = intent.getStringExtra("SpindleDir")
        val optionStop = intent.getStringExtra("OptionStop")
        val toolRetractionX = intent.getStringExtra("ToolRetractionX")
        val toolRetractionZ = intent.getStringExtra("ToolRetractionZ")
        val toolComment = intent.getStringExtra("ToolComment")

        // Use the retrieved data as needed
    }

    private fun setupListeners() {
        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                generateGCodes()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteBtn.setOnClickListener {
            clearAllFields()
        }
    }

    private fun areAllFieldsFilled(): Boolean {
        // Check if all required fields are filled
        return binding.SParameterEt.text?.isNotEmpty() == true &&
                binding.FParameterEt.text?.isNotEmpty() == true &&
                binding.g0XET.text?.isNotEmpty() == true &&
                binding.g0ZEt.text?.isNotEmpty() == true &&
                binding.secondXET.text?.isNotEmpty() == true &&
                binding.secondZEt.text?.isNotEmpty() == true &&
                binding.secondREt.text?.isNotEmpty() == true &&
                binding.thirdXET.text?.isNotEmpty() == true &&
                binding.thirdZEt.text?.isNotEmpty() == true &&
                binding.thirdREt.text?.isNotEmpty() == true &&
                binding.fourthXET.text?.isNotEmpty() == true &&
                binding.fourthZEt.text?.isNotEmpty() == true &&
                binding.fourthREt.text?.isNotEmpty() == true &&
                binding.fifthXET.text?.isNotEmpty() == true &&
                binding.fifthZEt.text?.isNotEmpty() == true &&
                binding.fifthREt.text?.isNotEmpty() == true &&
                binding.sixthXET.text?.isNotEmpty() == true &&
                binding.sixthZEt.text?.isNotEmpty() == true &&
                binding.sixthREt.text?.isNotEmpty() == true &&
                binding.seventhXET.text?.isNotEmpty() == true &&
                binding.seventhZEt.text?.isNotEmpty() == true &&
                binding.seventhREt.text?.isNotEmpty() == true &&
                binding.eighthXET.text?.isNotEmpty() == true &&
                binding.eighthZEt.text?.isNotEmpty() == true &&
                binding.eighthREt.text?.isNotEmpty() == true &&
                binding.ninthXET.text?.isNotEmpty() == true &&
                binding.ninthZEt.text?.isNotEmpty() == true &&
                binding.ninthREt.text?.isNotEmpty() == true &&
                binding.tenthXET.text?.isNotEmpty() == true &&
                binding.tenthZEt.text?.isNotEmpty() == true &&
                binding.tenthREt.text?.isNotEmpty() == true &&
                binding.eleventhXET.text?.isNotEmpty() == true &&
                binding.eleventhZEt.text?.isNotEmpty() == true &&
                binding.eleventhREt.text?.isNotEmpty() == true &&
                binding.endPositionXEt.text?.isNotEmpty() == true &&
                binding.endPositionZEt.text?.isNotEmpty() == true
    }

    private fun generateGCodes() {
        val noseRadius = intent.getStringExtra("NoseRadius")?.toDoubleOrNull() ?: 0.0
        val surfaceRoughness = intent.getStringExtra("SurfaceRoughness")?.toDoubleOrNull() ?: 0.0
        val feed = String.format("%.3f", Math.sqrt((18 * noseRadius * surfaceRoughness) / 1000))
        val spindleSpeed = 200 // Assume 100% nominal speed for simplicity

        val gCodes = """
            (INT. FINISH X)
            T0303(choosed insert, but if user put tool comment that comment showing);
            G54;
            G96S$spindleSpeed M3;
            G18G99;
            M8;
            G0G41X20.Z3.;
            G1Z-30.F$feed;
            G1X32.;
            G1Z-22.;
            G3X36.Z-20.R2.;
            G1X50.;
            G1Z0.;
            G1Z3.;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()

        // Show dialog with G-Codes
        showGCodeDialog(gCodes)
    }

    private fun showGCodeDialog(gCodes: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Generated G-Codes")
            .setMessage(gCodes)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .create()
        dialog.show()
    }

    private fun clearAllFields() {
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