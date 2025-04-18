//package com.example.cnc_code_generator_app.facingCycles
//
//import android.content.Intent
//import android.graphics.Color
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import com.example.cnc_code_generator_app.R
//import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleDownDirBinding
//
//class FacingCycleDownDirActivity : AppCompatActivity() {
//
//    // View Binding
//    private lateinit var binding: ActivityFacingCycleDownDirBinding
//
//    // Selected Insert
//    private var selectedInsert: String? = null
//
//    // Color Constants
//    private val defaultButtonColor by lazy { ContextCompat.getColor(this, R.color.lite_blue) }
//    private val selectedButtonColor = Color.parseColor("#EA509CD3") // Selected Color
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        // Inflate the layout
//        binding = ActivityFacingCycleDownDirBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Setup button click listeners
//        setupInsertButtonListeners()
//
//        // "SET" button logic
//        binding.setBtn.setOnClickListener {
//            onSetButtonClicked()
//        }
//
//        // "DELETE" button logic
//        binding.deleteBtn.setOnClickListener {
//            onDeleteButtonClicked()
//        }
//    }
//
//    private fun setupInsertButtonListeners() {
//        binding.cnmg80Btn.setOnClickListener { handleInsertSelection(binding.cnmg80Btn, "CNMG 80") }
//        binding.wnmg80Btn.setOnClickListener { handleInsertSelection(binding.wnmg80Btn, "WNMG 80") }
//        binding.tnmgBtn.setOnClickListener { handleInsertSelection(binding.tnmgBtn, "TNMG 60") }
//        binding.dnmgBtn.setOnClickListener { handleInsertSelection(binding.dnmgBtn, "DNMG 55") }
//
//    }
//
//    private fun handleInsertSelection(button: androidx.appcompat.widget.AppCompatButton, insertLabel: String) {
//        resetAllInsertButtons()
//        button.text = "OK"
//        button.setBackgroundColor(selectedButtonColor)
//        selectedInsert = insertLabel
//    }
//
//    private fun resetAllInsertButtons() {
//        listOf(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn).forEach {
//            it.text = getString(R.string.take)
//            it.setBackgroundColor(defaultButtonColor)
//        }
//    }
//
//    private fun onSetButtonClicked() {
//        val noseComp = binding.noseCompEt.text.toString().trim()
//        val noseRadiusStr = binding.noseRadiusEt.text.toString().trim()
//        val finishingAllowance = binding.finishingAllowanceEt.text.toString().trim()
//        val zeroPoint = binding.zeroPointEt.text.toString().trim()
//        val toolNumber = binding.toolEt.text.toString().trim()
//        val coolantOn = binding.coolantOnEt.text.toString().trim()
//        val coolantOff = binding.coolantOffEt.text.toString().trim()
//        val spindleDir = binding.spindleDirEt.text.toString().trim()
//        val optionStop = binding.optionStopEt.text.toString().trim()
//        val retractX = binding.toolRetractionXEt.text.toString().trim()
//        val retractZ = binding.toolRetractionZEt.text.toString().trim()
//        val toolComment = binding.toolCommentEt.text.toString().trim()
//
//        if (selectedInsert == null || noseComp.isEmpty() || noseRadiusStr.isEmpty() ||
//            finishingAllowance.isEmpty() || zeroPoint.isEmpty() || toolNumber.isEmpty() ||
//            coolantOn.isEmpty() || coolantOff.isEmpty() || spindleDir.isEmpty() ||
//            optionStop.isEmpty() || retractX.isEmpty() || retractZ.isEmpty() || toolComment.isEmpty()
//        ) {
//            Toast.makeText(this, "Please fill all fields before proceeding!", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val noseRadiusVal = noseRadiusStr.toDoubleOrNull() ?: 0.8
//        val feedStr = String.format("%.2f", 0.35 * noseRadiusVal)
//
//        val intent = Intent(this, FacingCycleDownDirectionG94::class.java).apply {
//            putExtra("SELECTED_INSERT", selectedInsert)
//            putExtra("NOSE_COMP", noseComp)
//            putExtra("NOSE_RADIUS", noseRadiusVal)
//            putExtra("FEED", feedStr)
//            putExtra("FINISH_ALLOW", finishingAllowance)
//            putExtra("ZERO_POINT", zeroPoint)
//            putExtra("TOOL_NUMBER", toolNumber)
//            putExtra("COOLANT_ON", coolantOn)
//            putExtra("COOLANT_OFF", coolantOff)
//            putExtra("SPINDLE_DIR", spindleDir)
//            putExtra("OPTION_STOP", optionStop)
//            putExtra("RETRACT_X", retractX)
//            putExtra("RETRACT_Z", retractZ)
//            putExtra("TOOL_COMMENT", toolComment)
//        }
//        startActivity(intent)
//    }
//
//    private fun onDeleteButtonClicked() {
//        resetAllInsertButtons()
//        selectedInsert = null
//
//        listOf(
//            binding.noseCompEt, binding.noseRadiusEt, binding.finishingAllowanceEt, binding.zeroPointEt,
//            binding.toolEt, binding.coolantOnEt, binding.coolantOffEt, binding.spindleDirEt,
//            binding.optionStopEt, binding.toolRetractionXEt, binding.toolRetractionZEt, binding.toolCommentEt
//        ).forEach { it.setText("") }
//    }
//}

package com.example.cnc_code_generator_app.facingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleDownDirBinding

class FacingCycleDownDirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleDownDirBinding

    private var selectedInsert: String? = null
    private val defaultButtonColor by lazy { ContextCompat.getColor(this, R.color.lite_blue) }
    private val selectedButtonColor = Color.parseColor("#EA509CD3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFacingCycleDownDirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefillDefaultData()

        setupInsertButtonListeners()

        binding.setBtn.setOnClickListener {
            onSetButtonClicked()
        }

        binding.deleteBtn.setOnClickListener {
            onDeleteButtonClicked()
        }
    }

    private fun prefillDefaultData() {
        binding.noseCompEt.setText("G41")
        binding.noseRadiusEt.setText("0.8")
        binding.finishingAllowanceEt.setText("0.2")
        binding.zeroPointEt.setText("G54")
        binding.toolEt.setText("0101")
        binding.coolantOnEt.setText("M8")
        binding.coolantOffEt.setText("M9")
        binding.spindleDirEt.setText("M3")
        binding.optionStopEt.setText("M1")
        binding.toolRetractionXEt.setText("X200")
        binding.toolRetractionZEt.setText("Z100")
        binding.toolCommentEt.setText("0001")
    }

    private fun setupInsertButtonListeners() {
        binding.cnmg80Btn.setOnClickListener { handleInsertSelection(binding.cnmg80Btn, "CNMG 80") }
        binding.wnmg80Btn.setOnClickListener { handleInsertSelection(binding.wnmg80Btn, "WNMG 80") }
        binding.tnmgBtn.setOnClickListener { handleInsertSelection(binding.tnmgBtn, "TNMG 60") }
        binding.dnmgBtn.setOnClickListener { handleInsertSelection(binding.dnmgBtn, "DNMG 55") }
    }

    private fun handleInsertSelection(button: androidx.appcompat.widget.AppCompatButton, insertLabel: String) {
        resetAllInsertButtons()
        button.text = "OK"
        button.setBackgroundColor(selectedButtonColor)
        selectedInsert = insertLabel
    }

    private fun resetAllInsertButtons() {
        listOf(binding.cnmg80Btn, binding.wnmg80Btn, binding.tnmgBtn, binding.dnmgBtn).forEach {
            it.text = getString(R.string.take)
            it.setBackgroundColor(defaultButtonColor)
        }
    }

    private fun onSetButtonClicked() {
        val noseComp = binding.noseCompEt.text.toString().trim()
        val noseRadiusStr = binding.noseRadiusEt.text.toString().trim()
        val finishingAllowance = binding.finishingAllowanceEt.text.toString().trim()
        val zeroPoint = binding.zeroPointEt.text.toString().trim()
        val toolNumber = binding.toolEt.text.toString().trim()
        val coolantOn = binding.coolantOnEt.text.toString().trim()
        val coolantOff = binding.coolantOffEt.text.toString().trim()
        val spindleDir = binding.spindleDirEt.text.toString().trim()
        val optionStop = binding.optionStopEt.text.toString().trim()
        val retractX = binding.toolRetractionXEt.text.toString().trim()
        val retractZ = binding.toolRetractionZEt.text.toString().trim()
        val toolComment = binding.toolCommentEt.text.toString().trim()

        if (selectedInsert == null || noseComp.isEmpty() || noseRadiusStr.isEmpty() ||
            finishingAllowance.isEmpty() || zeroPoint.isEmpty() || toolNumber.isEmpty() ||
            coolantOn.isEmpty() || coolantOff.isEmpty() || spindleDir.isEmpty() ||
            optionStop.isEmpty() || retractX.isEmpty() || retractZ.isEmpty() || toolComment.isEmpty()
        ) {
            Toast.makeText(this, "Please fill all fields before proceeding!", Toast.LENGTH_SHORT).show()
            return
        }

        val noseRadiusVal = noseRadiusStr.toDoubleOrNull() ?: 0.8
        val feedStr = String.format("%.2f", 0.35 * noseRadiusVal)

        val intent = Intent(this, FacingCycleDownDirectionG94::class.java).apply {
            putExtra("SELECTED_INSERT", selectedInsert)
            putExtra("NOSE_COMP", noseComp)
            putExtra("NOSE_RADIUS", noseRadiusVal)
            putExtra("FEED", feedStr)
            putExtra("FINISH_ALLOW", finishingAllowance)
            putExtra("ZERO_POINT", zeroPoint)
            putExtra("TOOL_NUMBER", toolNumber)
            putExtra("COOLANT_ON", coolantOn)
            putExtra("COOLANT_OFF", coolantOff)
            putExtra("SPINDLE_DIR", spindleDir)
            putExtra("OPTION_STOP", optionStop)
            putExtra("RETRACT_X", retractX)
            putExtra("RETRACT_Z", retractZ)
            putExtra("TOOL_COMMENT", toolComment)
        }
        startActivity(intent)
    }

    private fun onDeleteButtonClicked() {
        resetAllInsertButtons()
        selectedInsert = null

        listOf(
            binding.noseCompEt, binding.noseRadiusEt, binding.finishingAllowanceEt, binding.zeroPointEt,
            binding.toolEt, binding.coolantOnEt, binding.coolantOffEt, binding.spindleDirEt,
            binding.optionStopEt, binding.toolRetractionXEt, binding.toolRetractionZEt, binding.toolCommentEt
        ).forEach { it.setText("") }
    }
}
