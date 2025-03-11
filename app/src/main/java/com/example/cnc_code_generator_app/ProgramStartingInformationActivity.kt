package com.example.cnc_code_generator_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cnc_code_generator_app.databinding.ActivityProgramStartingInformationBinding

class ProgramStartingInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramStartingInformationBinding  // auto-generated from activity_program_starting_information.xml
    private val data = ProgramRepository.currentProgramData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramStartingInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupControlButtons()
        setupMaterialButtons()
        preFillData()

        binding.setBtn.setOnClickListener {
            saveData()
            // If not already added, add "1. START PROGRAM" to the program structure
            if (!ProgramRepository.programStructure.contains("1. START PROGRAM")) {
                ProgramRepository.programStructure.add("1. START PROGRAM")
            }
            Toast.makeText(this, "Program info set!", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.deleteBtn.setOnClickListener {
            Toast.makeText(this, "Start program canceled.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupControlButtons() {
        binding.selectFauncTv.setOnClickListener {
            data.controlType = "FANUC"
            binding.selectFauncTv.text = "OK"
            binding.selectFauncTv.setBackgroundResource(R.color.blue)
            binding.selectHassTv.text = getString(R.string.take)
            binding.selectHassTv.setBackgroundResource(R.color.lite_blue)
        }

        binding.selectHassTv.setOnClickListener {
            data.controlType = "HAAS"
            binding.selectHassTv.text = "OK"
            binding.selectHassTv.setBackgroundResource(R.color.blue)
            binding.selectFauncTv.text = getString(R.string.take)
            binding.selectFauncTv.setBackgroundResource(R.color.lite_blue)
        }
    }

    private fun setupMaterialButtons() {
        binding.selectSteelTv.setOnClickListener {
            data.material = "STEEL"
            binding.selectSteelTv.text = "OK"
            binding.selectSteelTv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectSteelTv)
            data.finalSurfaceSpeed = (data.defaultSurfaceSpeed * 1.0).toInt()
        }

        binding.selectAluminiumTv.setOnClickListener {
            data.material = "ALUMINIUM"
            binding.selectAluminiumTv.text = "OK"
            binding.selectAluminiumTv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAluminiumTv)
            data.finalSurfaceSpeed = (data.defaultSurfaceSpeed * 1.5).toInt()
        }

        binding.selectAisi303Tv.setOnClickListener {
            data.material = "AISI_303"
            binding.selectAisi303Tv.text = "OK"
            binding.selectAisi303Tv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAisi303Tv)
            data.finalSurfaceSpeed = (data.defaultSurfaceSpeed * 0.8).toInt()
        }

        binding.selectAisi316Tv.setOnClickListener {
            data.material = "AISI_316"
            binding.selectAisi316Tv.text = "OK"
            binding.selectAisi316Tv.setBackgroundResource(R.color.blue)
            resetMaterialButtonsExcept(binding.selectAisi316Tv)
            data.finalSurfaceSpeed = (data.defaultSurfaceSpeed * 0.65).toInt()
        }
    }

    // Helper function to reset other material buttons to default "Take" state.
    private fun resetMaterialButtonsExcept(selected: Button) {
        val buttons = listOf(binding.selectSteelTv, binding.selectAluminiumTv, binding.selectAisi303Tv, binding.selectAisi316Tv)
        buttons.filter { it != selected }.forEach { btn ->
            btn.text = getString(R.string.take)
            btn.setBackgroundResource(R.color.lite_blue)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun preFillData() {
        binding.zeroPointEt.setText(data.zeroPoint)
        binding.spindleLimitEt.setText(data.spindleLimit.toString())
        binding.coolantOnEt.setText(data.coolantOn)
        binding.coolantOffEt.setText(data.coolantOff)
        binding.spindleDirEt.setText(data.spindleDir)
        binding.optionStopEt.setText(data.optionStop)
        binding.blancDiaEt.setText(data.blankDia.toString())
        binding.faceAllowEt.setText(data.faceAllowance.toString())
        binding.toolRetractionXEt.setText(data.toolRetractionX.toString())
        binding.toolRetractionZEt.setText(data.toolRetractionZ.toString())
        binding.programNumberEt.setText(data.programNumber.toString())

        // Reflect pre-selected control type
        if (data.controlType == "FANUC") {
            binding.selectFauncTv.text = "OK"
            binding.selectFauncTv.setBackgroundResource(R.color.blue)
        } else {
            binding.selectHassTv.text = "OK"
            binding.selectHassTv.setBackgroundResource(R.color.blue)
        }

        // Reflect pre-selected material
        when (data.material) {
            "STEEL" -> {
                binding.selectSteelTv.text = "OK"
                binding.selectSteelTv.setBackgroundResource(R.color.blue)
            }
            "ALUMINIUM" -> {
                binding.selectAluminiumTv.text = "OK"
                binding.selectAluminiumTv.setBackgroundResource(R.color.blue)
            }
            "AISI_303" -> {
                binding.selectAisi303Tv.text = "OK"
                binding.selectAisi303Tv.setBackgroundResource(R.color.blue)
            }
            "AISI_316" -> {
                binding.selectAisi316Tv.text = "OK"
                binding.selectAisi316Tv.setBackgroundResource(R.color.blue)
            }
        }
    }

    private fun saveData() {
        data.zeroPoint = binding.zeroPointEt.text.toString().ifEmpty { "G54" }
        data.spindleLimit = binding.spindleLimitEt.text.toString().toIntOrNull()?.coerceIn(500, 4000) ?: 2000
        data.coolantOn = binding.coolantOnEt.text.toString().ifEmpty { "M8" }
        data.coolantOff = binding.coolantOffEt.text.toString().ifEmpty { "M9" }
        data.spindleDir = binding.spindleDirEt.text.toString().ifEmpty { "M3" }
        data.optionStop = binding.optionStopEt.text.toString().ifEmpty { "M1" }
        data.blankDia = binding.blancDiaEt.text.toString().toDoubleOrNull()?.coerceIn(5.0, 500.0) ?: 50.0
        data.faceAllowance = binding.faceAllowEt.text.toString().toDoubleOrNull()?.coerceIn(1.0, 50.0) ?: 1.5
        data.toolRetractionX = binding.toolRetractionXEt.text.toString().toDoubleOrNull()?.coerceIn(50.0, 500.0) ?: 200.0
        data.toolRetractionZ = binding.toolRetractionZEt.text.toString().toDoubleOrNull()?.coerceIn(50.0, 500.0) ?: 100.0
        data.programNumber = binding.programNumberEt.text.toString().toIntOrNull() ?: 1
    }
}
