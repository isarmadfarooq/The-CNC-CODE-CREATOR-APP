package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleZdirectionAdvancedApproachBinding

class INTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED_APPROACH : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleZdirectionAdvancedApproachBinding
    private lateinit var previousData: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalFinishingCycleZdirectionAdvancedApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previousData = intent.extras!!

        setupListeners()
    }

    private fun setupListeners() {
        binding.setBtn.setOnClickListener {
            if (areFieldsFilled()) {
                showGCodeDialog(generateGCode())
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteBtn.setOnClickListener {
            resetFields()
        }
    }

    private fun resetFields() {
        // Reset all fields in the current activity
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

    private fun areFieldsFilled(): Boolean {
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

    private fun generateGCode(): String {
        val noseRadius = previousData.getString("noseRadius")?.toFloat() ?: 0.4f
        val finishingAllowance = previousData.getString("finishingAllowance")?.toFloat() ?: 3.2f
        val feedRate = Math.sqrt((18 * noseRadius * finishingAllowance / 1000).toDouble()).toFloat()

        return """
            (INT. FINISH ALC)
            T${previousData.getString("tool")}(${previousData.getString("toolComment")});
            G54;
            G96S${binding.SParameterEt.text.toString().toInt() * 2}M3;
            G18G99;
            M8;
            G0G41X${binding.g0XET.text}.Z${binding.g0ZEt.text};
            G1X${binding.secondXET.text}.F$feedRate;
            G1Z${binding.secondZEt.text};
            G3X${binding.thirdXET.text}Z${binding.thirdZEt.text}R${binding.thirdREt.text};
            G1X${binding.fourthXET.text}Z${binding.fourthZEt.text};
            G2X${binding.fifthXET.text}Z${binding.fifthZEt.text}R${binding.fifthREt.text};
            G1Z${binding.sixthZEt.text};
            G3X${binding.seventhXET.text}Z${binding.seventhZEt.text}R${binding.seventhREt.text};
            G1X${binding.eighthXET.text}Z${binding.eighthZEt.text};
            G1X${binding.ninthXET.text};
            G0X${binding.tenthXET.text}.Z${binding.tenthZEt.text};
            M9;
            G0G40X${previousData.getString("toolRetractionX")}.Z${previousData.getString("toolRetractionZ")};
            M1;
            ;
        """.trimIndent()
    }

    private fun showGCodeDialog(gCode: String) {
        AlertDialog.Builder(this)
            .setTitle("Generated G-Code")
            .setMessage(gCode)
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .show()
    }
}