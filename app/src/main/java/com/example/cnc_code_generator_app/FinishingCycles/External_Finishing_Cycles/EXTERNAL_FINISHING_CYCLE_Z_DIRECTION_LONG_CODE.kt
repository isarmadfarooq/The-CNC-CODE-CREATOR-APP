package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleZdirectionLongCodeBinding

class EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityExternalFinishingCycleZdirectionLongCodeBinding

    // Track the number of clicked "Take" buttons
    private var clickedTakeButtons = mutableListOf<AppCompatButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display if needed
        // enableEdgeToEdge() // Uncomment if you have an implementation for this

        // Inflate the binding and set the root view as the content view
        binding = ActivityExternalFinishingCycleZdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners for the buttons
        setButtonListeners()
    }

    private fun setButtonListeners() {
        // Handle "Take" button clicks
        val takeButtons = listOf(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn)
        takeButtons.forEach { button ->
            button.setOnClickListener {
                handleTakeButtonClick(button)
            }
        }

        // Handle "Set" button click
        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                val intent = Intent(this, EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LC_APPROACH::class.java)
                // Pass the data to the next activity
                intent.putExtras(getAllFieldsData())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle "Delete" button click
        binding.deleteBtn.setOnClickListener {
            clearAllFields()
        }
    }

    private fun handleTakeButtonClick(button: AppCompatButton) {
        val takeText = getString(R.string.take)
        val okText = getString(R.string.ok)
        if (button.text.toString() == takeText) {
            button.text = okText
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            clickedTakeButtons.add(button)
        } else if (button.text.toString() == okText) {
            button.text = takeText
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
            clickedTakeButtons.remove(button)
        }
    }

    private fun areAllFieldsFilled(): Boolean {
        return binding.cnmg80Btn.text.toString().isNotEmpty() &&
                binding.wnmg80Btn.text.toString().isNotEmpty() &&
                binding.tnmgBtn.text.toString().isNotEmpty() &&
                binding.dnmgBtn.text.toString().isNotEmpty() &&
                binding.noseCompEt.text.toString().isNotEmpty() &&
                binding.noseRadiusEt.text.toString().isNotEmpty() &&
                binding.finishingAllowanceEt.text.toString().isNotEmpty() &&
                binding.zeroPointEt.text.toString().isNotEmpty() &&
                binding.toolEt.text.toString().isNotEmpty() &&
                binding.coolantOnEt.text.toString().isNotEmpty() &&
                binding.coolantOffEt.text.toString().isNotEmpty() &&
                binding.spindleDirEt.text.toString().isNotEmpty() &&
                binding.optionStopEt.text.toString().isNotEmpty() &&
                binding.toolRetractionXEt.text.toString().isNotEmpty() &&
                binding.toolRetractionZEt.text.toString().isNotEmpty() &&
                binding.toolCommentEt.text.toString().isNotEmpty()
    }

    private fun getAllFieldsData(): Bundle {
        val bundle = Bundle()
        bundle.putString("cnmg80Btn", binding.cnmg80Btn.text.toString())
        bundle.putString("wnmg80Btn", binding.wnmg80Btn.text.toString())
        bundle.putString("tnmgBtn", binding.tnmgBtn.text.toString())
        bundle.putString("dnmgBtn", binding.dnmgBtn.text.toString())
        bundle.putString("noseCompEt", binding.noseCompEt.text.toString())
        bundle.putString("noseRadiusEt", binding.noseRadiusEt.text.toString())
        bundle.putString("finishingAllowanceEt", binding.finishingAllowanceEt.text.toString())
        bundle.putString("zeroPointEt", binding.zeroPointEt.text.toString())
        bundle.putString("toolEt", binding.toolEt.text.toString())
        bundle.putString("coolantOnEt", binding.coolantOnEt.text.toString())
        bundle.putString("coolantOffEt", binding.coolantOffEt.text.toString())
        bundle.putString("spindleDirEt", binding.spindleDirEt.text.toString())
        bundle.putString("optionStopEt", binding.optionStopEt.text.toString())
        bundle.putString("toolRetractionXEt", binding.toolRetractionXEt.text.toString())
        bundle.putString("toolRetractionZEt", binding.toolRetractionZEt.text.toString())
        bundle.putString("toolCommentEt", binding.toolCommentEt.text.toString())
        return bundle
    }

    private fun clearAllFields() {
        val takeText = getString(R.string.take)
        binding.cnmg80Btn.text = takeText
        binding.cnmg80Btn.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
        binding.wnmg80Btn.text = takeText
        binding.wnmg80Btn.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
        binding.tnmgBtn.text = takeText
        binding.tnmgBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
        binding.dnmgBtn.text = takeText
        binding.dnmgBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
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
        clickedTakeButtons.clear()
    }
}
