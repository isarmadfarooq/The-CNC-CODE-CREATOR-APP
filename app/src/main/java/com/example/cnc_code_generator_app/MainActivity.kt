package com.example.cnc_code_generator_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.CuttingCycles.CuttingCyclesActivity
import com.example.cnc_code_generator_app.FinishingCycles.FinishingCyclesActivity
import com.example.cnc_code_generator_app.GroovingCycles.GroovingCyclesActivity
import com.example.cnc_code_generator_app.RoughingCycles.RoughingCyclesActivity
import com.example.cnc_code_generator_app.ThreadingCycles.ThreadingCyclesActivity
import com.example.cnc_code_generator_app.databinding.ActivityMainBinding
import com.example.cnc_code_generator_app.facingCycles.FacingCycleActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding  // auto-generated from activity_main.xml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMainActions()
        setupProgramCycles()
        setupProgramStructure()
        setupOutput()
    }

    override fun onResume() {
        super.onResume()
        refreshProgramStructureDisplay()
    }

    private fun setupMainActions() {
        binding.startNewBtn.setOnClickListener {
            startActivity(Intent(this, StartNewProgramActivity::class.java))
        }
        binding.saveBtn.setOnClickListener {
            startActivity(Intent(this, SaveProgramToFolderActivity::class.java))
        }
        binding.loadBtn.setOnClickListener {
            Toast.makeText(this, "Loading program...", Toast.LENGTH_SHORT).show()
            // TODO: Implement load logic
        }
        binding.endProgBtn.setOnClickListener {
            ProgramRepository.programStructure.add("M30  (End of Program)")
            Toast.makeText(this, "End Program added.", Toast.LENGTH_SHORT).show()
            refreshProgramStructureDisplay()
        }
        binding.infoBtn.setOnClickListener {
            Toast.makeText(this, "CNC CODE CREATOR 2025 by Kalle & Mikko Software Oy", Toast.LENGTH_LONG).show()
        }
        binding.exitBtn.setOnClickListener {
            finish()
        }
    }

    private fun setupProgramCycles() {
        binding.facingBtn.setOnClickListener {
            startActivity(Intent(this, FacingCycleActivity::class.java))
        }
        binding.roughingBtn.setOnClickListener {
            startActivity(Intent(this, RoughingCyclesActivity::class.java))

        }
        binding.finishingBtn.setOnClickListener {
            startActivity(Intent(this, FinishingCyclesActivity::class.java))
        }
        binding.threadingBtn.setOnClickListener {
            startActivity(Intent(this, ThreadingCyclesActivity::class.java))
        }
        binding.groovingBtn.setOnClickListener {
            startActivity(Intent(this, GroovingCyclesActivity::class.java))
        }
        binding.cuttingBtn.setOnClickListener {
            startActivity(Intent(this, CuttingCyclesActivity::class.java))
        }
    }

    private fun setupProgramStructure() {
        binding.startProgramBtn.setOnClickListener {
            // Populate the program structure with data from ProgramData
            ProgramRepository.programStructure.clear() // Clear previous data if required

            // Add basic program structure
            ProgramRepository.programStructure.add("O${ProgramRepository.currentProgramData.programNumber}  (Program Start)")
            ProgramRepository.programStructure.add("${ProgramRepository.currentProgramData.zeroPoint}  (Work Coordinate System)")
            ProgramRepository.programStructure.add("${ProgramRepository.currentProgramData.coolantOn}  (Coolant On)")
            ProgramRepository.programStructure.add("${ProgramRepository.currentProgramData.spindleDir} S${ProgramRepository.currentProgramData.spindleLimit}  (Spindle On)")
            ProgramRepository.programStructure.add("G96 S${ProgramRepository.currentProgramData.defaultSurfaceSpeed}  (Constant Surface Speed)")
            ProgramRepository.programStructure.add("M1  (Optional Stop)")
            ProgramRepository.programStructure.add("G50 D${ProgramRepository.currentProgramData.blankDia}  (Set Maximum Spindle Speed)")
            ProgramRepository.programStructure.add("G00 X${ProgramRepository.currentProgramData.toolRetractionX} Z${ProgramRepository.currentProgramData.toolRetractionZ}  (Tool Retraction)")
            ProgramRepository.programStructure.add("${ProgramRepository.currentProgramData.coolantOff}  (Coolant Off)")
            ProgramRepository.programStructure.add("M30  (End of Program)")

            // Add material and control type information to the program structure
            ProgramRepository.programStructure.add("Material Selected: ${ProgramRepository.currentProgramData.material}")
            ProgramRepository.programStructure.add("Control Type: ${ProgramRepository.currentProgramData.controlType}")

            // Add dynamic fields to the program structure
            ProgramRepository.currentProgramData.dynamicFields.forEach { (key, value) ->
                ProgramRepository.programStructure.add("$key: $value")
            }

            // Refresh the program structure display
            refreshProgramStructureDisplay()
        }
    }
    private fun setupOutput() {
        binding.outputBtn.setOnClickListener {
            val code = ProgramRepository.programStructure.joinToString("\n")
            binding.outputTv.text = code
            // Optionally write 'code' to a file or folder
        }
    }

    private fun refreshProgramStructureDisplay() {
        val structureText = ProgramRepository.programStructure.joinToString("\n")
        binding.outputTv.text = structureText
    }
}