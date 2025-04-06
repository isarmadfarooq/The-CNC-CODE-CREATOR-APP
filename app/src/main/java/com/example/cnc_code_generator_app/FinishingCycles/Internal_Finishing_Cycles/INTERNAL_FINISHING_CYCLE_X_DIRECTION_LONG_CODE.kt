package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleXdirectionLongCodeBinding

class INTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleXdirectionLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInternalFinishingCycleXdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // Take Button Toggle Logic
        setupToggleButton(binding.cnmg80Btn)
        setupToggleButton(binding.wnmg80Btn)
        setupToggleButton(binding.tnmgBtn)
        setupToggleButton(binding.dnmgBtn)

        // Delete Button Logic
        binding.deleteBtn.setOnClickListener {
            clearAllFields()
        }

        // Set Button Logic
        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                val intent = Intent(this, INTERNAL_FINISHING_CYCLE_X_DIRECTION_LC_APPROACH::class.java)
                // Pass data to the new activity
                intent.putExtra("CNMG80", binding.cnmg80Btn.text.toString())
                intent.putExtra("WNMG80", binding.wnmg80Btn.text.toString())
                intent.putExtra("TNMG", binding.tnmgBtn.text.toString())
                intent.putExtra("DNMG", binding.dnmgBtn.text.toString())
                intent.putExtra("NoseComp", binding.noseCompEt.text.toString())
                intent.putExtra("NoseRadius", binding.noseRadiusEt.text.toString())
                intent.putExtra("SurfaceRoughness", binding.finishingAllowanceEt.text.toString())
                intent.putExtra("ZeroPoint", binding.zeroPointEt.text.toString())
                intent.putExtra("Tool", binding.toolEt.text.toString())
                intent.putExtra("CoolantOn", binding.coolantOnEt.text.toString())
                intent.putExtra("CoolantOff", binding.coolantOffEt.text.toString())
                intent.putExtra("SpindleDir", binding.spindleDirEt.text.toString())
                intent.putExtra("OptionStop", binding.optionStopEt.text.toString())
                intent.putExtra("ToolRetractionX", binding.toolRetractionXEt.text.toString())
                intent.putExtra("ToolRetractionZ", binding.toolRetractionZEt.text.toString())
                intent.putExtra("ToolComment", binding.toolCommentEt.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupToggleButton(button: AppCompatButton) {
        button.setOnClickListener {
            if (button.text == getString(R.string.take)) {
                button.text = getString(R.string.ok)
                button.setBackgroundColor(Color.parseColor("#EA509CD3"))
            } else {
                button.text = getString(R.string.take)
                button.setBackgroundColor(Color.parseColor("#ADD8E6")) // lite_blue
            }
        }
    }

    private fun clearAllFields() {
        binding.cnmg80Btn.text = getString(R.string.take)
        binding.cnmg80Btn.setBackgroundColor(Color.parseColor("#ADD8E6"))
        binding.wnmg80Btn.text = getString(R.string.take)
        binding.wnmg80Btn.setBackgroundColor(Color.parseColor("#ADD8E6"))
        binding.tnmgBtn.text = getString(R.string.take)
        binding.tnmgBtn.setBackgroundColor(Color.parseColor("#ADD8E6"))
        binding.dnmgBtn.text = getString(R.string.take)
        binding.dnmgBtn.setBackgroundColor(Color.parseColor("#ADD8E6"))
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
    }

    private fun areAllFieldsFilled(): Boolean {
        // Check if all required fields are filled
        return binding.cnmg80Btn.text.isNotEmpty() &&
                binding.wnmg80Btn.text.isNotEmpty() &&
                binding.tnmgBtn.text.isNotEmpty() &&
                binding.dnmgBtn.text.isNotEmpty() &&
                binding.noseCompEt.text?.isNotEmpty() == true &&
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