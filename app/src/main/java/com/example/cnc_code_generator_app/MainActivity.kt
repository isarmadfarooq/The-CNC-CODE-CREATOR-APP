package com.example.cnc_code_generator_app

import android.content.Context
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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMainActions()
        setupProgramCycles()
        setupPostButton()
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


    private fun setupPostButton() {
        binding.postBtn.setOnClickListener {
            // Clear and generate the program structure
            ProgramRepository.programStructure.clear()

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

            ProgramRepository.programStructure.add("Material Selected: ${ProgramRepository.currentProgramData.material}")
            ProgramRepository.programStructure.add("Control Type: ${ProgramRepository.currentProgramData.controlType}")

            ProgramRepository.currentProgramData.dynamicFields.forEach { (key, value) ->
                ProgramRepository.programStructure.add("$key: $value")
            }

            // Generate the output code
            val facingUpPrefs = getSharedPreferences("FacingCycleUpPrefs", Context.MODE_PRIVATE)
            val facingDownPrefs = getSharedPreferences("FacingCyclePrefs", Context.MODE_PRIVATE)

            val outputBuilder = StringBuilder()

            // Check if FacingCycleUp data exists and append it
            if (facingUpPrefs.getBoolean("isFacingCycleUpDataSaved", false)) {
                val facingUpCode = """
                (FACING UP)
                T${facingUpPrefs.getString("TOOL_NUMBER", "0101")} ${facingUpPrefs.getString("TOOL_COMMENT", "")?.let { "($it)" }}
                ${facingUpPrefs.getString("ZERO_POINT", "G54")}
                G96 S${facingUpPrefs.getString("S_VALUE", "200")} ${facingUpPrefs.getString("SPINDLE_DIR", "M3")}
                G18 G99
                ${facingUpPrefs.getString("COOLANT_ON", "M8")}
                G0 ${facingUpPrefs.getString("START_X", "X0.")}${facingUpPrefs.getString("START_Z", "Z3.")}
                G94 ${facingUpPrefs.getString("END_X", "X52.")}${facingUpPrefs.getString("END_Z", "Z0.")} F${facingUpPrefs.getString("F_VALUE", "0.24")}
                G80
                ${facingUpPrefs.getString("COOLANT_OFF", "M9")}
                G0 ${facingUpPrefs.getString("TOOL_RETRACTION_X", "X200.")}${facingUpPrefs.getString("TOOL_RETRACTION_Z", "Z100.")}
                ${facingUpPrefs.getString("OPTION_STOP", "M1")}
                ;
            """.trimIndent()
                outputBuilder.append(facingUpCode).append("\n\n")
            }

            // Check if FacingCycleDown data exists and append it
            if (facingDownPrefs.getBoolean("isFacingCycleDownDataSaved", false)) {
                val facingDownCode = """
                (FACING DOWN)
                T${facingDownPrefs.getString("TOOL_NUMBER", "0101")} ${facingDownPrefs.getString("TOOL_COMMENT", "")?.let { "($it)" }}
                ${facingDownPrefs.getString("ZERO_POINT", "G54")}
                G96 S${facingDownPrefs.getString("S_VALUE", "200")} ${facingDownPrefs.getString("SPINDLE_DIR", "M3")}
                G18 G99
                ${facingDownPrefs.getString("COOLANT_ON", "M8")}
                G0 ${facingDownPrefs.getString("START_X", "X52.")}${facingDownPrefs.getString("START_Z", "Z3.")}
                G94 ${facingDownPrefs.getString("END_X", "X0.")}${facingDownPrefs.getString("END_Z", "Z0.1")} F${facingDownPrefs.getString("F_VALUE", "0.24")}
                G80
                ${facingDownPrefs.getString("COOLANT_OFF", "M9")}
                G0 X200.Z100.
                ${facingDownPrefs.getString("OPTION_STOP", "M1")}
                ;
            """.trimIndent()
                outputBuilder.append(facingDownCode).append("\n\n")
            }

            // Combine the program structure and output code
            val programStructureText = ProgramRepository.programStructure.joinToString("\n")
            val outputText = outputBuilder.toString()

            if (outputText.isNotBlank()) {
                // Display combined text if there is valid data
                val combinedText = "$programStructureText\n\n$outputText"
                binding.outputTv.text = combinedText
                binding.programProceedCodeSv.invalidate()
                Toast.makeText(this, "Output displayed!", Toast.LENGTH_SHORT).show()
            } else {
                // If no data exists, show a message to restart the process
                binding.outputTv.text = ""
                Toast.makeText(this, "No data found. Please start the process again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // Clear SharedPreferences on app close
        getSharedPreferences("FacingCycleUpPrefs", Context.MODE_PRIVATE).edit().clear().apply()
        getSharedPreferences("FacingCyclePrefs", Context.MODE_PRIVATE).edit().clear().apply()
    }
    private fun refreshProgramStructureDisplay() {
        val structureText = ProgramRepository.programStructure.joinToString("\n")
        binding.outputTv.text = structureText
    }
}