package com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityInternalFinishingCycleZdirectionLongCodeBinding

class INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE : AppCompatActivity() {

    private lateinit var binding: ActivityInternalFinishingCycleZdirectionLongCodeBinding
    private var isCnmg80Selected = false
    private var isWnmg80Selected = false
    private var isTnmgSelected = false
    private var isDnmgSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInternalFinishingCycleZdirectionLongCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        setupToggleButton(binding.cnmg80Btn) { isCnmg80Selected = !isCnmg80Selected }
        setupToggleButton(binding.wnmg80Btn) { isWnmg80Selected = !isWnmg80Selected }
        setupToggleButton(binding.tnmgBtn) { isTnmgSelected = !isTnmgSelected }
        setupToggleButton(binding.dnmgBtn) { isDnmgSelected = !isDnmgSelected }

        binding.setBtn.setOnClickListener { onSetButtonClick() }
        binding.deleteBtn.setOnClickListener { onDeleteButtonClick() }
    }

    private fun setupToggleButton(button: Button, toggleAction: () -> Unit) {
        button.setOnClickListener {
            toggleAction()
            if (button.text == getString(R.string.take)) {
                button.text = getString(R.string.ok)
                button.setBackgroundColor(Color.parseColor("#EA509CD3"))
            } else {
                button.text = getString(R.string.take)
                button.setBackgroundColor(resources.getColor(R.color.lite_blue, null))
            }
        }
    }

    private fun onSetButtonClick() {
        if (areAllFieldsFilled()) {
            val intent = Intent(this, INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LC_APPROACH::class.java)
            intent.putExtra("data", collectData())
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onDeleteButtonClick() {
        clearAllFields()
    }

    private fun areAllFieldsFilled(): Boolean {
        return binding.noseCompEt.text.toString().isNotEmpty() &&
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

    private fun collectData(): Bundle {
        return Bundle().apply {
            putString("noseComp", binding.noseCompEt.text.toString())
            putString("noseRadius", binding.noseRadiusEt.text.toString())
            putString("finishingAllowance", binding.finishingAllowanceEt.text.toString())
            putString("zeroPoint", binding.zeroPointEt.text.toString())
            putString("tool", binding.toolEt.text.toString())
            putString("coolantOn", binding.coolantOnEt.text.toString())
            putString("coolantOff", binding.coolantOffEt.text.toString())
            putString("spindleDir", binding.spindleDirEt.text.toString())
            putString("optionStop", binding.optionStopEt.text.toString())
            putString("toolRetractionX", binding.toolRetractionXEt.text.toString())
            putString("toolRetractionZ", binding.toolRetractionZEt.text.toString())
            putString("toolComment", binding.toolCommentEt.text.toString())
        }
    }

    private fun clearAllFields() {
        binding.noseCompEt.setText("")
        binding.noseRadiusEt.setText("")
        binding.finishingAllowanceEt.setText("")
        binding.zeroPointEt.setText("")
        binding.toolEt.setText("")
        binding.coolantOnEt.setText("")
        binding.coolantOffEt.setText("")
        binding.spindleDirEt.setText("")
        binding.optionStopEt.setText("")
        binding.toolRetractionXEt.setText("")
        binding.toolRetractionZEt.setText("")
        binding.toolCommentEt.setText("")
    }
}