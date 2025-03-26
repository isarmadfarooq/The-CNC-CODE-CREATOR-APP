package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleZdirectionG71Binding

class InternalRoughingCycle_Z_Direction_G71 : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleZdirectionG71Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleZdirectionG71Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup "TAKE" buttons
        setupTakeButtons()

        // Setup "SET" button
        binding.setBtn.setOnClickListener {
            if (verifyFields()) {
                generateGCode()
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
        return !binding.noseCompEt.text.isNullOrEmpty() &&
                !binding.noseRadiusEt.text.isNullOrEmpty() &&
                !binding.finishingAllowanceEt.text.isNullOrEmpty() &&
                !binding.zeroPointEt.text.isNullOrEmpty() &&
                !binding.toolEt.text.isNullOrEmpty() &&
                !binding.coolantOnEt.text.isNullOrEmpty() &&
                !binding.coolantOffEt.text.isNullOrEmpty() &&
                !binding.spindleDirEt.text.isNullOrEmpty() &&
                !binding.optionStopEt.text.isNullOrEmpty() &&
                !binding.toolRetractionXEt.text.isNullOrEmpty() &&
                !binding.toolRetractionZEt.text.isNullOrEmpty() &&
                !binding.toolCommentEt.text.isNullOrEmpty()
    }

    private fun generateGCode() {
        // Safely convert noseRadius to a Double
        val noseRadius = binding.noseRadiusEt.text?.toString()?.toDoubleOrNull() ?: 0.0
        val feed = 0.35 * noseRadius

        // Material speed from the selected RadioButton in the RadioGroup
        val materialSpeed = when (binding.materialSelectionRg.checkedRadioButtonId) {
            R.id.steelRadioButton -> 200
            R.id.aluminiumRadioButton -> 300
            R.id.aisi303RadioButton -> 160
            R.id.aisi316RadioButton -> 130
            else -> 200
        }
        val gCodeFanuc = """
            (INT. ROUGH Z)
            T0202(${binding.toolCommentEt.text});
            G54;
            G96S$materialSpeed M3;
            G18G99;
            M8;
            G0G41X20.Z3.;
            G71U${binding.finishingAllowanceEt.text} R${binding.toolRetractionXEt.text};
            G71P10Q20U${binding.finishingAllowanceEt.text} W${binding.toolRetractionZEt.text} F$feed;
            N10G1X50.Z0.F$feed;
            GIZ-20.;
            G1X36.;
            G2X32.Z-22.R2.;
            G1Z-30.;
            G1X22.;
            N20G1X20.;
            G70P10Q20;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()

        val gCodeHaas = """
            (INT. ROUGH Z)
            T0202(${binding.toolCommentEt.text});
            G54;
            G96S$materialSpeed M3;
            G18G99;
            M8;
            G0G41X20.Z3.;
            G71P10Q20U${binding.finishingAllowanceEt.text} W${binding.toolRetractionZEt.text} F$feed;
            N10G1X50.Z0.F$feed;
            GIZ-20.;
            G1X36.;
            G2X32.Z-22.R2.;
            G1Z-30.;
            G1X22.;
            N20G1X20.;
            G70P10Q20;
            G0X52.Z3.;
            M9;
            G0G40X200.Z100.;
            M1;
        """.trimIndent()

        // Show dialog with generated G-Code
        showGCodeDialog(gCodeFanuc, gCodeHaas)
    }

    private fun showGCodeDialog(gCodeFanuc: String, gCodeHaas: String) {
        val dialog = GCodeDialogFragment.newInstance(gCodeFanuc, gCodeHaas)
        dialog.show(supportFragmentManager, "GCodeDialog")
    }

    private fun moveToNextActivity() {
        val intent = Intent(this, InternalRoughingCycle_Z_DirApproach::class.java)
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
