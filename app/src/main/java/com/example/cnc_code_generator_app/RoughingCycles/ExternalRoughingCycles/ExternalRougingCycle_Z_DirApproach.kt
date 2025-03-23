package com.example.cnc_code_generator_app.RoughingCycles.ExternalRoughingCycles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityExternalRougingCycleZdirApproachBinding

class ExternalRougingCycle_Z_DirApproach : AppCompatActivity() {

    private lateinit var binding: ActivityExternalRougingCycleZdirApproachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityExternalRougingCycleZdirApproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the G-code strings passed from the previous activity
        val fanucCode = intent.getStringExtra("FANUC_CODE")
        val haasCode = intent.getStringExtra("HAAS_CODE")

        // Load any previously saved G-code from SharedPreferences
        loadGCodeFromPreferences()

        // Set button -> collect input, do calculations, build G code, and save it
        binding.setBtn.setOnClickListener {
            val finalFanucCode = buildFanucProgram(fanucCode)
            val finalHaasCode = buildHaasProgram(haasCode)

            // Save the G-code to SharedPreferences and move back to the Main Activity
            saveGCodeToPreferences(finalFanucCode, finalHaasCode)
            moveToMainActivity()
        }

        // Delete or cancel
        binding.deleteBtn.setOnClickListener {
            finish()
        }
    }

    /**
     * Build the final G-code in Fanuc style with additional user inputs.
     */
    private fun buildFanucProgram(baseCode: String?): String {
        val sParam = binding.SParameterEt.text.toString().ifBlank { "200" }
        val fParam = binding.FParameterEt.text.toString().ifBlank { "0.24" }
        val rParam = binding.RParameterEt.text.toString().ifBlank { "0.5" }
        val dParam = binding.DParameterEt.text.toString().ifBlank { "2.2" }
        val uParam = binding.UParameterEt.text.toString().ifBlank { "0.3" }
        val wParam = binding.WParameterEt.text.toString().ifBlank { "0.1" }

        val g0X = binding.g0XET.text.toString().ifBlank { "X52" }
        val g0Z = binding.g0ZEt.text.toString().ifBlank { "Z3" }

        val secondX = binding.secondXET.text.toString().ifBlank { "X30" }
        val secondZ = binding.secondZEt.text.toString().ifBlank { "Z0" }
        val secondR = binding.secondREt.text.toString().ifBlank { "R" }

        val thirdX = binding.thirdXET.text.toString().ifBlank { "X30" }
        val thirdZ = binding.thirdZEt.text.toString().ifBlank { "Z-18" }
        val thirdR = binding.thirdREt.text.toString().ifBlank { "R" }

        val fourthX = binding.fourthXET.text.toString().ifBlank { "X34" }
        val fourthZ = binding.fourthZEt.text.toString().ifBlank { "Z-20" }
        val fourthR = binding.fourthREt.text.toString().ifBlank { "R2" }

        val fifthX = binding.fifthXET.text.toString().ifBlank { "X38" }
        val fifthZ = binding.fifthZEt.text.toString().ifBlank { "Z-20" }
        val fifthR = binding.fifthREt.text.toString().ifBlank { "R" }

        val sixthX = binding.sixthXET.text.toString().ifBlank { "X40" }
        val sixthZ = binding.sixthZEt.text.toString().ifBlank { "Z-21" }
        val sixthR = binding.sixthREt.text.toString().ifBlank { "R1" }

        val seventhX = binding.seventhXET.text.toString().ifBlank { "X40" }
        val seventhZ = binding.seventhZEt.text.toString().ifBlank { "Z-30" }
        val seventhR = binding.seventhREt.text.toString().ifBlank { "R" }

        val eighthX = binding.eighthXET.text.toString().ifBlank { "X50" }
        val eighthZ = binding.eighthZEt.text.toString().ifBlank { "Z-30" }
        val eighthR = binding.eighthREt.text.toString().ifBlank { "R" }

        val ninthX = binding.ninthXET.text.toString().ifBlank { "X" }
        val ninthZ = binding.ninthZEt.text.toString().ifBlank { "Z" }
        val ninthR = binding.ninthREt.text.toString().ifBlank { "R" }

        val tenthX = binding.tenthXET.text.toString().ifBlank { "X" }
        val tenthZ = binding.tenthZEt.text.toString().ifBlank { "Z" }
        val tenthR = binding.tenthREt.text.toString().ifBlank { "R" }

        val eleventhX = binding.eleventhXET.text.toString().ifBlank { "X" }
        val eleventhZ = binding.eleventhZEt.text.toString().ifBlank { "Z" }
        val eleventhR = binding.eleventhREt.text.toString().ifBlank { "R" }

        val endPositionX = binding.endPositionXEt.text.toString().ifBlank { "X20" }
        val endPositionZ = binding.endPositionZEt.text.toString().ifBlank { "Z-30" }

        // Replace placeholders in the G-code with actual values
        return baseCode?.replace("G96S200", "G96S$sParam")
            ?.replace("F0.24", "F$fParam")
            ?.replace("R0.5", "R$rParam")
            ?.replace("U2.2", "U$dParam")
            ?.replace("U0.3", "U$uParam")
            ?.replace("W0.1", "W$wParam")
            ?.replace("G0X52Z3", "G0$g0X$g0Z")
            ?.replace("G1X30Z0", "G1$secondX$secondZ")
            ?.replace("G1X30Z-18", "G1$thirdX$thirdZ")
            ?.replace("G2X34Z-20R2", "G2$fourthX$fourthZ$fourthR")
            ?.replace("G1X38Z-20", "G1$fifthX$fifthZ")
            ?.replace("G3X40Z-21R1", "G3$sixthX$sixthZ$sixthR")
            ?.replace("G1X40Z-30", "G1$seventhX$seventhZ")
            ?.replace("G1X50Z-30", "G1$eighthX$eighthZ")
            ?.replace("G1X$endPositionX$endPositionZ", "G1$endPositionX$endPositionZ")
            ?: ""
    }

    /**
     * Build the final G-code in Haas style with additional user inputs.
     */
    private fun buildHaasProgram(baseCode: String?): String {
        val sParam = binding.SParameterEt.text.toString().ifBlank { "200" }
        val fParam = binding.FParameterEt.text.toString().ifBlank { "0.24" }
        val rParam = binding.RParameterEt.text.toString().ifBlank { "0.5" }
        val dParam = binding.DParameterEt.text.toString().ifBlank { "2.2" }
        val uParam = binding.UParameterEt.text.toString().ifBlank { "0.3" }
        val wParam = binding.WParameterEt.text.toString().ifBlank { "0.1" }

        val g0X = binding.g0XET.text.toString().ifBlank { "X52" }
        val g0Z = binding.g0ZEt.text.toString().ifBlank { "Z3" }

        val secondX = binding.secondXET.text.toString().ifBlank { "X30" }
        val secondZ = binding.secondZEt.text.toString().ifBlank { "Z0" }
        val secondR = binding.secondREt.text.toString().ifBlank { "R" }

        val thirdX = binding.thirdXET.text.toString().ifBlank { "X30" }
        val thirdZ = binding.thirdZEt.text.toString().ifBlank { "Z-18" }
        val thirdR = binding.thirdREt.text.toString().ifBlank { "R" }

        val fourthX = binding.fourthXET.text.toString().ifBlank { "X34" }
        val fourthZ = binding.fourthZEt.text.toString().ifBlank { "Z-20" }
        val fourthR = binding.fourthREt.text.toString().ifBlank { "R2" }

        val fifthX = binding.fifthXET.text.toString().ifBlank { "X38" }
        val fifthZ = binding.fifthZEt.text.toString().ifBlank { "Z-20" }
        val fifthR = binding.fifthREt.text.toString().ifBlank { "R" }

        val sixthX = binding.sixthXET.text.toString().ifBlank { "X40" }
        val sixthZ = binding.sixthZEt.text.toString().ifBlank { "Z-21" }
        val sixthR = binding.sixthREt.text.toString().ifBlank { "R1" }

        val seventhX = binding.seventhXET.text.toString().ifBlank { "X40" }
        val seventhZ = binding.seventhZEt.text.toString().ifBlank { "Z-30" }
        val seventhR = binding.seventhREt.text.toString().ifBlank { "R" }

        val eighthX = binding.eighthXET.text.toString().ifBlank { "X50" }
        val eighthZ = binding.eighthZEt.text.toString().ifBlank { "Z-30" }
        val eighthR = binding.eighthREt.text.toString().ifBlank { "R" }

        val ninthX = binding.ninthXET.text.toString().ifBlank { "X" }
        val ninthZ = binding.ninthZEt.text.toString().ifBlank { "Z" }
        val ninthR = binding.ninthREt.text.toString().ifBlank { "R" }

        val tenthX = binding.tenthXET.text.toString().ifBlank { "X" }
        val tenthZ = binding.tenthZEt.text.toString().ifBlank { "Z" }
        val tenthR = binding.tenthREt.text.toString().ifBlank { "R" }

        val eleventhX = binding.eleventhXET.text.toString().ifBlank { "X" }
        val eleventhZ = binding.eleventhZEt.text.toString().ifBlank { "Z" }
        val eleventhR = binding.eleventhREt.text.toString().ifBlank { "R" }

        val endPositionX = binding.endPositionXEt.text.toString().ifBlank { "X20" }
        val endPositionZ = binding.endPositionZEt.text.toString().ifBlank { "Z-30" }

        // Replace placeholders in the G-code with actual values
        return baseCode?.replace("G96S200", "G96S$sParam")
            ?.replace("F0.24", "F$fParam")
            ?.replace("R0.5", "R$rParam")
            ?.replace("D2.2", "D$dParam")
            ?.replace("U0.3", "U$uParam")
            ?.replace("W0.1", "W$wParam")
            ?.replace("G0X52Z3", "G0$g0X$g0Z")
            ?.replace("G1X30Z0", "G1$secondX$secondZ")
            ?.replace("G1X30Z-18", "G1$thirdX$thirdZ")
            ?.replace("G2X34Z-20R2", "G2$fourthX$fourthZ$fourthR")
            ?.replace("G1X38Z-20", "G1$fifthX$fifthZ")
            ?.replace("G3X40Z-21R1", "G3$sixthX$sixthZ$sixthR")
            ?.replace("G1X40Z-30", "G1$seventhX$seventhZ")
            ?.replace("G1X50Z-30", "G1$eighthX$eighthZ")
            ?.replace("G1X$endPositionX$endPositionZ", "G1$endPositionX$endPositionZ")
            ?: ""
    }

    /**
     * Save the final G-code to SharedPreferences.
     */
    private fun saveGCodeToPreferences(fanucCode: String, haasCode: String) {
        val sharedPreferences = getSharedPreferences("GCodePreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("FanucCode", fanucCode)
        editor.putString("HaasCode", haasCode)
        editor.apply()
    }

    /**
     * Load the G-code from SharedPreferences if it exists.
     */
    private fun loadGCodeFromPreferences() {
        val sharedPreferences = getSharedPreferences("GCodePreferences", Context.MODE_PRIVATE)
        val fanucCode = sharedPreferences.getString("FanucCode", "")
        val haasCode = sharedPreferences.getString("HaasCode", "")

        if (!fanucCode.isNullOrEmpty() || !haasCode.isNullOrEmpty()) {
            showFinalGCode(fanucCode ?: "", haasCode ?: "")
        }
    }

    /**
     * Move to the Main Activity.
     */
    private fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Display the final G-code using an AlertDialog.
     */
    private fun showFinalGCode(fanucCode: String, haasCode: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Generated G-Code")
        builder.setMessage("Fanuc Code:\n$fanucCode\n\nHaas Code:\n$haasCode")
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}