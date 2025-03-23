package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleXdirectionG72Binding

class ExternalRoughingCycle_X_DirectionG72 : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleXdirectionG72Binding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRoughingCycleXdirectionG72Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("GCodePreferences", MODE_PRIVATE)

        // Setup logic for selecting inserts and handling button clicks
        setupInsertSelection()
        setupSetButton()
    }

    private fun setupInsertSelection() {
        val insertButtons = listOf(
            binding.cnmg80Btn to binding.cnmg80Label,
            binding.wnmg80Btn to binding.wnmg80Lable,
            binding.tnmgBtn to binding.tnmgLable,
            binding.dnmgBtn to binding.dnmgLabel
        )

        for ((button, label) in insertButtons) {
            button.setOnClickListener {
                insertButtons.forEach { (btn, _) ->
                    btn.text = getString(R.string.take)
                    btn.setBackgroundColor(Color.parseColor("#FFFFFF")) // Reset background color
                }
                button.text = getString(R.string.ok)
                button.setBackgroundColor(Color.parseColor("#EA509CD3")) // Change background color to #EA509CD3
            }
        }
    }

    private fun setupSetButton() {
        binding.setBtn.setOnClickListener {
            // Collect data from the form
            val noseComp = binding.noseCompEt.text.toString()
            val noseRadius = binding.noseRadiusEt.text.toString()
            val finishingAllowance = binding.finishingAllowanceEt.text.toString()
            val zeroPoint = binding.zeroPointEt.text.toString()
            val tool = binding.toolEt.text.toString()
            val coolantOn = binding.coolantOnEt.text.toString()
            val coolantOff = binding.coolantOffEt.text.toString()
            val spindleDir = binding.spindleDirEt.text.toString()
            val optionStop = binding.optionStopEt.text.toString()
            val toolRetractionX = binding.toolRetractionXEt.text.toString()
            val toolRetractionZ = binding.toolRetractionZEt.text.toString()
            val toolComment = binding.toolCommentEt.text.toString()

            // Save data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("noseComp", noseComp)
            editor.putString("noseRadius", noseRadius)
            editor.putString("finishingAllowance", finishingAllowance)
            editor.putString("zeroPoint", zeroPoint)
            editor.putString("tool", tool)
            editor.putString("coolantOn", coolantOn)
            editor.putString("coolantOff", coolantOff)
            editor.putString("spindleDir", spindleDir)
            editor.putString("optionStop", optionStop)
            editor.putString("toolRetractionX", toolRetractionX)
            editor.putString("toolRetractionZ", toolRetractionZ)
            editor.putString("toolComment", toolComment)
            editor.apply()

            // Proceed to the next activity and pass the collected data
            val intent = Intent(this, ExternalRouhingCycle_X_DirApproach::class.java)
            intent.putExtra("noseComp", noseComp)
            intent.putExtra("noseRadius", noseRadius)
            intent.putExtra("finishingAllowance", finishingAllowance)
            intent.putExtra("zeroPoint", zeroPoint)
            intent.putExtra("tool", tool)
            intent.putExtra("coolantOn", coolantOn)
            intent.putExtra("coolantOff", coolantOff)
            intent.putExtra("spindleDir", spindleDir)
            intent.putExtra("optionStop", optionStop)
            intent.putExtra("toolRetractionX", toolRetractionX)
            intent.putExtra("toolRetractionZ", toolRetractionZ)
            intent.putExtra("toolComment", toolComment)
            startActivity(intent)
        }
    }
}