package com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityExternalFinishingCycleXdirectionLongCodeBinding

class EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE : AppCompatActivity() {

    private lateinit var binding: ActivityExternalFinishingCycleXdirectionLongCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityExternalFinishingCycleXdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        val buttons = listOf(
            binding.cnmg80Btn,
            binding.wnmg80Btn,
            binding.tnmgBtn,
            binding.dnmgBtn
        )

        for (button in buttons) {
            button.setOnClickListener {
                toggleButtonState(button)
            }
        }

        binding.setBtn.setOnClickListener {
            if (areAllFieldsFilled()) {
                val intent = Intent(this, EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LC_APPROACH::class.java)
                intent.putExtra("cnmg80", binding.cnmg80Btn.text.toString())
                intent.putExtra("wnmg80", binding.wnmg80Btn.text.toString())
                intent.putExtra("tnmg", binding.tnmgBtn.text.toString())
                intent.putExtra("dnmg", binding.dnmgBtn.text.toString())
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

    private fun toggleButtonState(button: AppCompatButton) {
        if (button.text == getString(R.string.take)) {
            button.text = getString(R.string.ok)
            button.setBackgroundColor(Color.parseColor("#EA509CD3"))
        } else {
            button.text = getString(R.string.take)
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
        }
    }

    private fun areAllFieldsFilled(): Boolean {
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