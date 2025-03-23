package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalRouhingCycleXdirApproachBinding

class ExternalRouhingCycle_X_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRouhingCycleXdirApproachBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRouhingCycleXdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("GCodePreferences", MODE_PRIVATE)

        // Retrieve data passed from ExternalRoughingCycle_X_DirectionG72
        val noseComp = intent.getStringExtra("noseComp")
        val noseRadius = intent.getStringExtra("noseRadius")
        val finishingAllowance = intent.getStringExtra("finishingAllowance")
        val zeroPoint = intent.getStringExtra("zeroPoint")
        val tool = intent.getStringExtra("tool")
        val coolantOn = intent.getStringExtra("coolantOn")
        val coolantOff = intent.getStringExtra("coolantOff")
        val spindleDir = intent.getStringExtra("spindleDir")
        val optionStop = intent.getStringExtra("optionStop")
        val toolRetractionX = intent.getStringExtra("toolRetractionX")
        val toolRetractionZ = intent.getStringExtra("toolRetractionZ")
        val toolComment = intent.getStringExtra("toolComment")

        // Display retrieved data
        binding.SParameterEt.setText(calculateFeed(noseRadius))
        binding.FParameterEt.setText(calculateSpeed(tool))
        binding.RParameterEt.setText("0.5")
        binding.DParameterEt.setText("2")
        binding.UParameterEt.setText("0.3")
        binding.WParameterEt.setText("0.1")
        // Set other fields if needed
        // Example for start position fields:
        binding.g0XET.setText("52")
        binding.g0ZEt.setText("3")
        // More fields...

        // Set button logic
        binding.setBtn.setOnClickListener {
            val gCodeFanuc = generateGCodeFanuc()
            val gCodeHaas = generateGCodeHaas()
            showGCodeDialog(gCodeFanuc, gCodeHaas)
        }
    }

    private fun calculateFeed(noseRadius: String?): String {
        return if (noseRadius != null) {
            val radius = noseRadius.toDoubleOrNull() ?: 0.8
            String.format("%.2f", 0.35 * radius)
        } else {
            "0.24"
        }
    }

    private fun calculateSpeed(tool: String?): String {
        return when (tool) {
            "Steel" -> "200"
            "Aluminium" -> "300"
            "AISI 303" -> "160"
            "AISI 316" -> "130"
            else -> "200"
        }
    }

    private fun saveGCodeToPreferences(gCode: String) {
        val editor = sharedPreferences.edit()
        editor.putString("GCode", gCode)
        editor.apply()
    }

    private fun generateGCodeFanuc(): String {
        val noseComp = binding.SParameterEt.text.toString()
        val noseRadius = binding.FParameterEt.text.toString()
        val finishingAllowance = binding.RParameterEt.text.toString()
        val zeroPoint = binding.DParameterEt.text.toString()
        val tool = binding.UParameterEt.text.toString()
        val coolantOn = binding.WParameterEt.text.toString()
        val coolantOff = binding.SParameterEt.text.toString()
        val spindleDir = binding.FParameterEt.text.toString()
        val optionStop = binding.RParameterEt.text.toString()
        val toolRetractionX = binding.DParameterEt.text.toString()
        val toolRetractionZ = binding.UParameterEt.text.toString()
        val toolComment = binding.WParameterEt.text.toString()

        // Generate Fanuc-style G-Code string
        return """
            T0202($toolComment);
            G54;
            G96S$noseRadius M3;
            G18G99;
            M8;
            G0G41X52.Z3.;
            G72U$noseComp.R0.5;
            G72P10Q20U$finishingAllowance.W$zeroPoint.F$tool;
            N10 G1X50.Z-30.F0.12;
            G1X40.;
            G1Z-21.;
            G2X38.Z-20.R1.;
            G1X34.;
            G3X30.Z-18.R2.;
            G1Z0.;
            N20 G1Z3.;
            G70P10Q20;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()
    }

    private fun generateGCodeHaas(): String {
        val noseComp = binding.SParameterEt.text.toString()
        val noseRadius = binding.FParameterEt.text.toString()
        val finishingAllowance = binding.RParameterEt.text.toString()
        val zeroPoint = binding.DParameterEt.text.toString()
        val tool = binding.UParameterEt.text.toString()
        val coolantOn = binding.WParameterEt.text.toString()
        val coolantOff = binding.SParameterEt.text.toString()
        val spindleDir = binding.FParameterEt.text.toString()
        val optionStop = binding.RParameterEt.text.toString()
        val toolRetractionX = binding.DParameterEt.text.toString()
        val toolRetractionZ = binding.UParameterEt.text.toString()
        val toolComment = binding.WParameterEt.text.toString()

        // Generate Haas-style G-Code string
        return """
            T0202($toolComment);
            G54;
            G96S$noseRadius M3;
            G18G99;
            M8;
            G0G41X52.Z3.;
            //surface speed 200, and rolling direction M3
            //X and Z axis, feed per round
            //coolant on
            //start P with nose compensation on
            G72P10Q20U$finishingAllowance.W$zeroPoint.D$noseComp. F$tool;
            N10 G1X50.Z-30.F0.12;
            G1X40.;
            G1Z-21.;
            G2X38.Z-20.R1.;
            G1X34.;
            G3X30.Z-18.R2.;
            G1Z0.;
            N20 G1Z3.;
            G70P10Q20;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()
    }

    private fun showGCodeDialog(gCodeFanuc: String, gCodeHaas: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Generated G-Code")
        builder.setMessage("Fanuc Style:\n$gCodeFanuc\n\nHaas Style:\n$gCodeHaas")
        builder.setPositiveButton("OK") { dialog, _ ->
            saveGCodeToPreferences(gCodeFanuc)
            saveGCodeToPreferences(gCodeHaas)
            moveToMainActivity()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}