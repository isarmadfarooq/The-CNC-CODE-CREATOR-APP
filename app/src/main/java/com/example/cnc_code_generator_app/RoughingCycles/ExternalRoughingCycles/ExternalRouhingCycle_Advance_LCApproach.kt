package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityExternalRouhingCycleAdvanceLcapproachBinding

class ExternalRouhingCycle_Advance_LCApproach : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRouhingCycleAdvanceLcapproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRouhingCycleAdvanceLcapproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from the previous activity
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
        val feed = intent.getStringExtra("feed")
        val depthOfCut = intent.getStringExtra("depthOfCut")

        // Use the retrieved data to set the values in the new layout
        binding.SParameterEt.setText("200") // Assuming Material is Steel
        binding.FParameterEt.setText(feed)
        binding.RParameterEt.setText("0.5") // Example value, adjust as needed
        binding.DParameterEt.setText(depthOfCut)
        binding.UParameterEt.setText("0.3") // Example value, adjust as needed
        binding.WParameterEt.setText("0.1") // Example value, adjust as needed

        // Set up click listener for "SET" button
        binding.setBtn.setOnClickListener {
            generateAndDisplayCode()
        }
    }

    private fun generateAndDisplayCode() {
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
        val feed = intent.getStringExtra("feed")
        val depthOfCut = intent.getStringExtra("depthOfCut")

        val generatedCode = """
            (EXT. ROUGH ALC)
            T0202(${toolComment});
            G54;
            G96S200M3;
            G18G99;
            M8;
            G0G42X52.Z3.;
            G0X47.6;
            G1Z-33.8;
            G1U1.W0.5;
            G0Z3.;
            X43.2;
            G1Z-31.6;
            G1U1.W0.5;
            G0Z3.;
            X38.8;
            G1Z-19.4;
            G1U1.W0.5;
            G0Z3.;
            X34.4;
            G1Z-17.2;
            G1U1.W0.5;
            G0Z3.;
            G0X52.Z3.;
            G1X30.Z0.F$feed;
            G1X30.Z-14.172;
            G2X31.172Z-15.586R2.;
            G1X39.414Z-19.707;
            G3X40.Z-20.414R1.;
            G1X40.Z-28.757;
            G2X41.757Z-30.879R3.;
            G1X50.Z-35.;
            G1X52.;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()

        // Create and show the dialog
        AlertDialog.Builder(this)
            .setTitle("Generated Code")
            .setMessage(generatedCode)
            .setPositiveButton("OK") { dialog, _ ->
                saveCodeInSharedPreferences(generatedCode)
                dialog.dismiss()
                navigateToMainActivity()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveCodeInSharedPreferences(generatedCode: String) {
        val sharedPref = getSharedPreferences("CNC_CODE_PREFS", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("generated_code", generatedCode)
            apply()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}