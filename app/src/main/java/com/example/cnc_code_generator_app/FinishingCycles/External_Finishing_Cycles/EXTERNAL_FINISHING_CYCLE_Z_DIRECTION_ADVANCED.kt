package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleZdirectionAdvancedBinding

class EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityExternalFinishingCycleZdirectionAdvancedBinding
    private val selectedData = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding and set the root view
        binding = ActivityExternalFinishingCycleZdirectionAdvancedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val buttons = listOf(
            binding.cnmg80Btn to binding.cnmg80Label,
            binding.wnmg80Btn to binding.wnmg80Lable,
            binding.tnmgBtn to binding.tnmgLable,
            binding.dnmgBtn to binding.dnmgLabel
        )

        buttons.forEach { (button, label) ->
            button.setOnClickListener {
                if (button.text == getString(R.string.take)) {
                    button.text = getString(R.string.ok)
                    button.setBackgroundColor(Color.parseColor("#EA509CD3"))
                    selectedData[label.text.toString()] = button.text.toString()
                } else {
                    button.text = getString(R.string.take)
                    button.setBackgroundColor(Color.parseColor("#ADD8E6"))
                    selectedData.remove(label.text.toString())
                }
            }
        }

        binding.deleteBtn.setOnClickListener {
            clearAllInputs()
            Toast.makeText(this, "All data deleted", Toast.LENGTH_SHORT).show()
        }

        binding.setBtn.setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCE_APPROACH::class.java)
                intent.putExtra("selectedData", HashMap(selectedData))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        return selectedData.isNotEmpty() &&
                binding.noseCompEt.text?.isNotEmpty() ?: false &&
                binding.noseRadiusEt.text?.isNotEmpty() ?: false &&
                binding.finishingAllowanceEt.text?.isNotEmpty() ?: false &&
                binding.zeroPointEt.text?.isNotEmpty() ?: false &&
                binding.toolEt.text?.isNotEmpty() ?: false &&
                binding.coolantOnEt.text?.isNotEmpty() ?: false &&
                binding.coolantOffEt.text?.isNotEmpty() ?: false &&
                binding.spindleDirEt.text?.isNotEmpty() ?: false &&
                binding.optionStopEt.text?.isNotEmpty() ?: false &&
                binding.toolRetractionXEt.text?.isNotEmpty() ?: false &&
                binding.toolRetractionZEt.text?.isNotEmpty() ?: false &&
                binding.toolCommentEt.text?.isNotEmpty() ?: false
    }

    private fun clearAllInputs() {
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
        val buttons = listOf(
            binding.cnmg80Btn,
            binding.wnmg80Btn,
            binding.tnmgBtn,
            binding.dnmgBtn
        )
        buttons.forEach {
            it.text = getString(R.string.take)
            it.setBackgroundColor(Color.parseColor("#ADD8E6"))
        }
        selectedData.clear()
    }
}