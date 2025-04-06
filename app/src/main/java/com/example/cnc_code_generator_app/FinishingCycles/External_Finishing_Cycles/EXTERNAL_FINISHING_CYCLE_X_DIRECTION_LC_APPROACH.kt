package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleXdirectionLcApproachBinding
import kotlin.math.sqrt

class EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LC_APPROACH : AppCompatActivity() {

    private lateinit var binding: ActivityExternalFinishingCycleXdirectionLcApproachBinding
    private lateinit var previousData: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityExternalFinishingCycleXdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previousData = intent.extras?.keySet()?.associateWith { intent.getStringExtra(it) ?: "" } ?: emptyMap()

        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                val gCodes = generateGCodes()
                showDialog(gCodes)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun areAllFieldsFilled(): Boolean {
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

    private fun generateGCodes(): String {
        val noseRadius = previousData["noseRadius"]?.toDoubleOrNull() ?: 0.0
        val ra = previousData["finishingAllowance"]?.toDoubleOrNull() ?: 0.0
        val feed = sqrt((18 * noseRadius * ra) / 1000)
        val speed = 200 * (previousData["material"]?.toDoubleOrNull() ?: 1.0)
        val toolComment = previousData["toolComment"] ?: ""
        val zeroPoint = previousData["zeroPoint"] ?: ""
        val coolantOn = previousData["coolantOn"] ?: ""
        val coolantOff = previousData["coolantOff"] ?: ""
        val spindleDir = previousData["spindleDir"] ?: ""
        val optionStop = previousData["optionStop"] ?: ""
        val toolRetractionX = previousData["toolRetractionX"] ?: ""
        val toolRetractionZ = previousData["toolRetractionZ"] ?: ""

        return """
            (EXT. FINISH X)
            T0303($toolComment);
            G54;
            G96S${speed}M3;
            G18G99;
            M8;
            G0G41X52.Z3.;
            G1X50.Z-30.F${feed};
            G1X40.;
            G1Z-21.;
            G2X38.Z-20.R1.;
            G1X34.;
            G3X30.Z-18.R2.;
            G1Z0.;
            G1Z3.;
            G0X52.Z3.;
            M9;
            G0G40X${toolRetractionX}.Z${toolRetractionZ}.;
            M1;
            ;
        """.trimIndent()
    }

    private fun showDialog(gCodes: String) {
        AlertDialog.Builder(this)
            .setTitle("Generated G-Codes")
            .setMessage(gCodes)
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.show()
    }
}