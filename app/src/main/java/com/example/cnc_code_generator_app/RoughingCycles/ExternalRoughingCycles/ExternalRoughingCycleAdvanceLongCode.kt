package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalRoughingCycleAdvanceLongCodeBinding

class ExternalRoughingCycleAdvanceLongCode : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRoughingCycleAdvanceLongCodeBinding
    private var selectedInsert: String? = null
    private var depthOfCut = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityExternalRoughingCycleAdvanceLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners for "TAKE" buttons
        setUpTakeButtons()

        // Set up click listener for "SET" button
        binding.setBtn.setOnClickListener {
            if (selectedInsert == null) {
                Toast.makeText(this, "Please choose an insert", Toast.LENGTH_SHORT).show()
            } else {
                moveToAdvanceLCApproach()
            }
        }
    }

    private fun setUpTakeButtons() {
        val takeButtons = listOf(
            binding.cnmg80Btn,
            binding.wnmg80Btn,
            binding.tnmgBtn,
            binding.dnmgBtn
        )

        for (button in takeButtons) {
            button.setOnClickListener {
                takeButtons.forEach { it.text = getString(R.string.take) }
                button.text = getString(R.string.ok)
                button.setBackgroundColor(Color.parseColor("#EA509CD3"))
                selectedInsert = button.text.toString()
                depthOfCut = when (button.id) {
                    R.id.cnmg80Btn -> 2.2
                    R.id.wnmg80Btn -> 2.0
                    R.id.tnmgBtn -> 1.7
                    R.id.dnmgBtn -> 1.4
                    else -> 0.0
                }
            }
        }
    }

    private fun moveToAdvanceLCApproach() {
        val noseRadius = binding.noseRadiusEt.text.toString().toDoubleOrNull() ?: 0.8
        val feed = 0.35 * noseRadius

        val intent = Intent(this, ExternalRouhingCycle_Advance_LCApproach::class.java)
        // Pass data to the new activity
        intent.putExtra("noseComp", binding.noseCompEt.text.toString())
        intent.putExtra("noseRadius", noseRadius.toString())
        intent.putExtra("finishingAllowance", binding.finishingAllowanceEt.text.toString())
        intent.putExtra("zeroPoint", binding.zeroPointEt.text.toString())
        intent.putExtra("tool", binding.toolEt.text.toString())
        intent.putExtra("coolantOn", binding.coolantOnEt.text.toString())
        intent.putExtra("coolantOff", binding.coolantOffEt.text.toString())
        intent.putExtra("spindleDir", binding.spindleDirEt.text.toString())
        intent.putExtra("optionStop", binding.optionStopEt.text.toString())
        intent.putExtra("toolRetractionX", binding.toolRetractionXEt.text.toString())
        intent.putExtra("toolRetractionZ", binding.toolRetractionZEt.text.toString())
        intent.putExtra("toolComment", binding.toolCommentEt.text.toString())
        intent.putExtra("feed", feed.toString())
        intent.putExtra("depthOfCut", depthOfCut.toString())
        startActivity(intent)
    }
}