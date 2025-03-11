package com.example.cnc_code_generator_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivityMainBinding

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
            ProgramRepository.programStructure.add("FACING cycle added")
            refreshProgramStructureDisplay()
        }
        binding.roughingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("ROUGHING cycle added")
            refreshProgramStructureDisplay()
        }
        binding.finishingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("FINISHING cycle added")
            refreshProgramStructureDisplay()
        }
        binding.threadingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("THREADING cycle added")
            refreshProgramStructureDisplay()
        }
        binding.groovingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("GROOVING cycle added")
            refreshProgramStructureDisplay()
        }
        binding.cuttingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("CUTTING cycle added")
            refreshProgramStructureDisplay()
        }
    }

    private fun setupProgramStructure() {
        binding.startProgramBtn.setOnClickListener {
            ProgramRepository.programStructure.add("1. START PROGRAM")
            refreshProgramStructureDisplay()
        }
        binding.extRoughBtn.setOnClickListener {
            ProgramRepository.programStructure.add("2. EXT. ROUGH G71")
            refreshProgramStructureDisplay()
        }
        binding.extCuttingBtn.setOnClickListener {
            ProgramRepository.programStructure.add("3. EXT. CUTTING ALC")
            refreshProgramStructureDisplay()
        }
        binding.endProgBtn.setOnClickListener {
            ProgramRepository.programStructure.add("4. END PROGRAM")
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
