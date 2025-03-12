package com.example.cnc_code_generator_app

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.databinding.ActivitySaveProgramToFolderBinding
import java.io.File

class SaveProgramToFolderActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveProgramToFolderBinding
    private var selectedFolder: String = "my_files" // Default folder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveProgramToFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE
        binding.folderNameTv.text = selectedFolder

        binding.folderNameTv.setOnClickListener { showFolderEditDialog() }
        binding.programToFolderFindBtn.setOnClickListener { showFolderSelectionDialog() }
        binding.btnSave.setOnClickListener { if (validateInputs()) showConfirmDialog() }
        binding.btnClose.setOnClickListener { finish() }
    }

    private fun showFolderSelectionDialog() {
        val folders = arrayOf("my_files", "CNC_Programs", "Project_X")
        AlertDialog.Builder(this)
            .setTitle("Select Folder")
            .setItems(folders) { _, which ->
                selectedFolder = folders[which]
                binding.folderNameTv.text = selectedFolder
            }
            .setNegativeButton("Edit") { _, _ -> showFolderEditDialog() }
            .show()
    }

    private fun showFolderEditDialog() {
        val editText = EditText(this).apply { setText(selectedFolder) }
        AlertDialog.Builder(this)
            .setTitle("Edit Folder Name")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                selectedFolder = editText.text.toString().trim().ifEmpty { "my_files" }
                binding.folderNameTv.text = selectedFolder
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun validateInputs(): Boolean {
        val programName = binding.programNameTv.text.toString().trim()
        if (programName.isEmpty()) {
            Toast.makeText(this, "Program name cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun showConfirmDialog() {
        val programName = binding.programNameTv.text.toString().trim()
        val summary = "Program Name: $programName\nFolder: $selectedFolder\n\nProceed with saving?"
        AlertDialog.Builder(this)
            .setTitle("Confirm Save")
            .setMessage(summary)
            .setPositiveButton("Confirm") { _, _ -> saveProgram() }
            .setNegativeButton("Edit", null)
            .show()
    }

    private fun saveProgram() {
        binding.btnSave.visibility = View.GONE
        binding.btnClose.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.progress = 0

        object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((3000 - millisUntilFinished) * 100 / 3000).toInt()
                binding.progressBar.progress = progress
            }

            override fun onFinish() {
                binding.progressBar.progress = 100
                val programName = binding.programNameTv.text.toString().trim()
                val programCode = ProgramRepository.programStructure.joinToString("\n")
                val directory = getExternalFilesDir(selectedFolder)?.apply { mkdirs() }
                val file = File(directory, "$programName.txt")

                try {
                    file.writeText(programCode)
                    val fileContent = file.readText()
                    showFileContentDialog(programName, fileContent)
                    Log.d("SaveProgram", "File saved: ${file.absolutePath}")
                    Toast.makeText(this@SaveProgramToFolderActivity, "Program saved successfully!", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Log.e("SaveProgram", "Error saving file", e)
                    Toast.makeText(this@SaveProgramToFolderActivity, "Failed to save program", Toast.LENGTH_LONG).show()
                }

                binding.progressBar.visibility = View.GONE
                binding.btnSave.visibility = View.VISIBLE
                binding.btnClose.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun showFileContentDialog(programName: String, content: String) {
        AlertDialog.Builder(this)
            .setTitle("File Content: $programName")
            .setMessage(content)
            .setPositiveButton("OK", null)
            .show()
    }
}