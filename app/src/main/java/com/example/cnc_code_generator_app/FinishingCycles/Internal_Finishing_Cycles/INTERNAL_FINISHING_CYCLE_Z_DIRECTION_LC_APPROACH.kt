package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleZdirectionLcApproachBinding
import java.util.Locale

class INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LC_APPROACH : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleZdirectionLcApproachBinding
    private lateinit var previousData: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInternalFinishingCycleZdirectionLcApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        previousData = intent.getBundleExtra("data")!!

        setupListeners()
    }

    private fun setupListeners() {
        binding.setBtn.setOnClickListener { onSetButtonClick() }
        binding.deleteBtn.setOnClickListener { onDeleteButtonClick() }
    }

    private fun onSetButtonClick() {
        if (areAllFieldsFilled()) {
            val gCode = generateGCode()
            showGCodeDialog(gCode)
        } else {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onDeleteButtonClick() {
        clearAllFields()
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

    private fun showGCodeDialog(gCode: String) {
        AlertDialog.Builder(this)
            .setTitle("Generated G-Code")
            .setMessage(gCode)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .show()
    }

    private fun generateGCode(): String {
        val gCode = StringBuilder()
        gCode.append("(INT. FINISH Z)\n")
        gCode.append(String.format(Locale.US, "T%s(%s);\n", previousData.getString("tool"), previousData.getString("toolComment", "")))
        gCode.append("G54;\n") // zero point G54
        gCode.append("G96S200M3;\n") // surface speed 200, and rolling direction M3
        gCode.append("G18G99;\n")
        gCode.append("M8;\n") // coolant on
        gCode.append("G0G42X20.Z3.;\n") // start P with nose compensation on
        gCode.append("G1X50.Z0.F0.152;\n") // 2nd line from display
        gCode.append("G1Z-20.;\n") // 3rd line
        gCode.append("G2X36.;\n") // 4th line
        gCode.append("G1X32.Z-22.R2.;\n") // 5th line with R2.
        gCode.append("G3Z-30.;\n") // 6th line
        gCode.append("G1X22.;\n") // 7th line
        gCode.append("G1X20.;\n") // 8th line
        gCode.append("G0X20.Z3.;\n") // end Q
        gCode.append("M9;\n") // coolant off
        gCode.append("G0G40X200.Z100.;\n") // nose compensation off and back to tool retract position
        gCode.append("M1;\n") // option stop

        return gCode.toString()
    }

    private fun clearAllFields() {
        binding.SParameterEt.setText("")
        binding.FParameterEt.setText("")
        binding.g0XET.setText("")
        binding.g0ZEt.setText("")
        binding.secondXET.setText("")
        binding.secondZEt.setText("")
        binding.secondREt.setText("")
        binding.thirdXET.setText("")
        binding.thirdZEt.setText("")
        binding.thirdREt.setText("")
        binding.fourthXET.setText("")
        binding.fourthZEt.setText("")
        binding.fourthREt.setText("")
        binding.fifthXET.setText("")
        binding.fifthZEt.setText("")
        binding.fifthREt.setText("")
        binding.sixthXET.setText("")
        binding.sixthZEt.setText("")
        binding.sixthREt.setText("")
        binding.seventhXET.setText("")
        binding.seventhZEt.setText("")
        binding.seventhREt.setText("")
        binding.eighthXET.setText("")
        binding.eighthZEt.setText("")
        binding.eighthREt.setText("")
        binding.ninthXET.setText("")
        binding.ninthZEt.setText("")
        binding.ninthREt.setText("")
        binding.tenthXET.setText("")
        binding.tenthZEt.setText("")
        binding.tenthREt.setText("")
        binding.eleventhXET.setText("")
        binding.eleventhZEt.setText("")
        binding.eleventhREt.setText("")
        binding.endPositionXEt.setText("")
        binding.endPositionZEt.setText("")
    }
}