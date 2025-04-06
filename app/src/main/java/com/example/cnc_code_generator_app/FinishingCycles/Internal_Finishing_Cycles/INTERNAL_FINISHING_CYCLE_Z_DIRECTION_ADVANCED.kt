package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleZdirectionAdvancedBinding

class INTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleZdirectionAdvancedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalFinishingCycleZdirectionAdvancedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // Set up the take buttons click listeners
        val takeButtons = arrayOf(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn)
        takeButtons.forEach { button ->
            button.setOnClickListener {
                if (button.text == getString(R.string.take)) {
                    button.text = getString(R.string.ok)
                    button.setBackgroundColor(Color.parseColor("#EA509CD3"))
                } else {
                    button.text = getString(R.string.take)
                    button.setBackgroundColor(Color.parseColor("#D0E8FF"))
                }
            }
        }

        // Set up the delete button click listener
        binding.deleteBtn.setOnClickListener {
            resetFields()
        }

        // Set up the set button click listener
        binding.setBtn.setOnClickListener {
            if (areFieldsFilled()) {
                val intent = Intent(this, INTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED_APPROACH::class.java)
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
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun resetFields() {
        binding.noseCompEt.text?.clear()
        binding.noseRadiusEt.text?.clear()
        binding.finishingAllowanceEt.text?.clear()
        binding.zeroPointEt.text?.clear()
        binding.toolEt.text?.clear()
        binding.coolantOnEt.text?.clear()
        binding.coolantOffEt.text?.clear()
        binding.spindleDirEt.text?.clear()
        binding.optionStopEt.text?.clear()
        binding.toolRetractionXEt.text?.clear()
        binding.toolRetractionZEt.text?.clear()
        binding.toolCommentEt.text?.clear()

        val takeButtons = arrayOf(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn)
        takeButtons.forEach { button ->
            button.text = getString(R.string.take)
            button.setBackgroundColor(Color.parseColor("#D0E8FF"))
        }
    }

    private fun areFieldsFilled(): Boolean {
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
}