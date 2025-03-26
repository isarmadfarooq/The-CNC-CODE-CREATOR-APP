package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleXdirectionG72Binding

class InternalRoughingCycle_X_Direction_G72 : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleXdirectionG72Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleXdirectionG72Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup "TAKE" buttons
        setupTakeButtons()

        // Setup "SET" button
        binding.setBtn.setOnClickListener {
            if (verifyFields()) {
                moveToNextActivity()
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTakeButtons() {
        binding.cnmg80Btn.setOnClickListener { onTakeButtonClicked(binding.cnmg80Btn) }
        binding.wnmg80Btn.setOnClickListener { onTakeButtonClicked(binding.wnmg80Btn) }
        binding.tnmgBtn.setOnClickListener { onTakeButtonClicked(binding.tnmgBtn) }
        binding.dnmgBtn.setOnClickListener { onTakeButtonClicked(binding.dnmgBtn) }
    }

    private fun onTakeButtonClicked(button: AppCompatButton) {
        button.text = "OK"
        button.setBackgroundColor(Color.parseColor("#EA509CD3"))
    }

    private fun verifyFields(): Boolean {
        return binding.noseCompEt.text?.isNotEmpty() == true &&
                binding.noseRadiusEt.text?.isNotEmpty() == true &&
                binding.finishingAllowanceEt.text?.isNotEmpty() == true &&
                binding.zeroPointEt.text?.isNotEmpty() == true &&
                binding.toolEt.text?.isNotEmpty() == true &&
                binding.coolantOnEt.text?.isNotEmpty() == true &&
                binding.coolantOffEt.text?.isNotEmpty() == true &&
                binding.spindleDirEt.text?.isNotEmpty() == true &&
                binding.optionStopEt.text?.isNotEmpty() == true &&
                binding.toolRetractionXEt.text?.isNotEmpty() == true &&
                binding.toolRetractionZEt.text?.isNotEmpty() == true &&
                binding.toolCommentEt.text?.isNotEmpty() == true
    }

    private fun moveToNextActivity() {
        val intent = Intent(this, InternalRoughingCycle_X_DirApproach::class.java)
        intent.putExtra("noseComp", binding.noseCompEt.text.toString())
        intent.putExtra("noseRadius", binding.noseRadiusEt.text.toString())
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
        startActivity(intent)
    }
}