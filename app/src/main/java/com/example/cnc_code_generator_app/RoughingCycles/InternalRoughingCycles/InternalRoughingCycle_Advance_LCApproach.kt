package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityInternalRoughingCycleAdvanceLcapproachBinding

class InternalRoughingCycle_Advance_LCApproach : AppCompatActivity() {

    private lateinit var binding: ActivityInternalRoughingCycleAdvanceLcapproachBinding
    private var noseComp: String? = null
    private var noseRadius: String? = null
    private var finishingAllowance: String? = null
    private var zeroPoint: String? = null
    private var tool: String? = null
    private var coolantOn: String? = null
    private var coolantOff: String? = null
    private var spindleDir: String? = null
    private var optionStop: String? = null
    private var toolRetractionX: String? = null
    private var toolRetractionZ: String? = null
    private var toolComment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityInternalRoughingCycleAdvanceLcapproachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from the previous activity
        val extras = intent.extras
        if (extras != null) {
            noseComp = extras.getString("noseComp")
            noseRadius = extras.getString("noseRadius")
            finishingAllowance = extras.getString("finishingAllowance")
            zeroPoint = extras.getString("zeroPoint")
            tool = extras.getString("tool")
            coolantOn = extras.getString("coolantOn")
            coolantOff = extras.getString("coolantOff")
            spindleDir = extras.getString("spindleDir")
            optionStop = extras.getString("optionStop")
            toolRetractionX = extras.getString("toolRetractionX")
            toolRetractionZ = extras.getString("toolRetractionZ")
            toolComment = extras.getString("toolComment")
        }

        // Setup "SET" button
        binding.setBtn.setOnClickListener {
            if (verifyFields()) {
                val (gCodeFanuc, gCodeHaas) = generateGCode()
                saveGCodeToPreferences(gCodeFanuc, gCodeHaas)
                showGCodeDialog(gCodeFanuc, gCodeHaas)

                // Navigate to MainActivity and clear all previous activities
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteBtn.setOnClickListener {
            // List of all EditText fields
            val editTexts = listOf(
                binding.SParameterEt, binding.FParameterEt, binding.RParameterEt, binding.DParameterEt, binding.UParameterEt, binding.WParameterEt,
                binding.g0XET, binding.g0ZEt, binding.secondXET, binding.secondZEt, binding.secondREt,
                binding.thirdXET, binding.thirdZEt, binding.thirdREt, binding.fourthXET, binding.fourthZEt, binding.fourthREt,
                binding.fifthXET, binding.fifthZEt, binding.fifthREt, binding.sixthXET, binding.sixthZEt, binding.sixthREt,
                binding.seventhXET, binding.seventhZEt, binding.seventhREt, binding.eighthXET, binding.eighthZEt, binding.eighthREt,
                binding.ninthXET, binding.ninthZEt, binding.ninthREt, binding.tenthXET, binding.tenthZEt, binding.tenthREt,
                binding.eleventhXET, binding.eleventhZEt, binding.eleventhREt, binding.endPositionXEt, binding.endPositionZEt
            )

            // Clear all EditText fields
            editTexts.forEach { it.text?.clear() }

            // Remove saved G-Code from SharedPreferences
            getSharedPreferences("GCodePreferences", Context.MODE_PRIVATE).edit().apply {
                remove("GCodeFanuc")
                remove("GCodeHaas")
                apply()
            }

            // Show success message
            Toast.makeText(this, "All data deleted successfully", Toast.LENGTH_SHORT).show()
        }

    }






    private fun verifyFields(): Boolean {
        return binding.SParameterEt.text?.isNotEmpty() == true &&
                binding.FParameterEt.text?.isNotEmpty() == true &&
                binding.RParameterEt.text?.isNotEmpty() == true &&
                binding.DParameterEt.text?.isNotEmpty() == true &&
                binding.UParameterEt.text?.isNotEmpty() == true &&
                binding.WParameterEt.text?.isNotEmpty() == true &&
                binding.g0XET.text?.isNotEmpty() == true &&
                binding.g0ZEt.text?.isNotEmpty() == true &&
                binding.secondXET.text?.isNotEmpty() == true &&
                binding.secondZEt.text?.isNotEmpty() == true &&
                binding.secondREt.text?.isNotEmpty() == true &&
                binding.thirdXET.text?.isNotEmpty() == true &&
                binding.thirdZEt.text?.isNotEmpty() == true &&
                binding.thirdREt.text?.isNotEmpty() == true &&
                binding.fourthXET.text?.isNotEmpty() == true &&
                binding.fourthZEt.text?.isNotEmpty() == true &&
                binding.fourthREt.text?.isNotEmpty() == true &&
                binding.fifthXET.text?.isNotEmpty() == true &&
                binding.fifthZEt.text?.isNotEmpty() == true &&
                binding.fifthREt.text?.isNotEmpty() == true &&
                binding.sixthXET.text?.isNotEmpty() == true &&
                binding.sixthZEt.text?.isNotEmpty() == true &&
                binding.sixthREt.text?.isNotEmpty() == true &&
                binding.seventhXET.text?.isNotEmpty() == true &&
                binding.seventhZEt.text?.isNotEmpty() == true &&
                binding.seventhREt.text?.isNotEmpty() == true &&
                binding.eighthXET.text?.isNotEmpty() == true &&
                binding.eighthZEt.text?.isNotEmpty() == true &&
                binding.eighthREt.text?.isNotEmpty() == true &&
                binding.ninthXET.text?.isNotEmpty() == true &&
                binding.ninthZEt.text?.isNotEmpty() == true &&
                binding.ninthREt.text?.isNotEmpty() == true &&
                binding.tenthXET.text?.isNotEmpty() == true &&
                binding.tenthZEt.text?.isNotEmpty() == true &&
                binding.tenthREt.text?.isNotEmpty() == true &&
                binding.eleventhXET.text?.isNotEmpty() == true &&
                binding.eleventhZEt.text?.isNotEmpty() == true &&
                binding.eleventhREt.text?.isNotEmpty() == true &&
                binding.endPositionXEt.text?.isNotEmpty() == true &&
                binding.endPositionZEt.text?.isNotEmpty() == true
    }

    private fun generateGCode(): Pair<String, String> {
        // Generate G-Code based on user input and predefined formulas
        val noseRadiusValue = noseRadius?.toDoubleOrNull() ?: 0.0
        val feed = 0.35 * noseRadiusValue
        val materialSpeed = binding.SParameterEt.text.toString().toInt()
        val finishingAllowanceValue = finishingAllowance ?: ""
        val toolRetractionXValue = toolRetractionX ?: ""
        val toolRetractionZValue = toolRetractionZ ?: ""

        val gCodeFanuc = """
            (INT. ROUGH X)
            T0202($toolComment);
            G54;
            G96S$materialSpeed M3;
            G18G99;
            M8;
            G0G42X${binding.g0XET.text}.Z${binding.g0ZEt.text};
            G72U${binding.DParameterEt.text}.R${binding.RParameterEt.text};
            G72P10Q20U${binding.UParameterEt.text}W${binding.WParameterEt.text}F$feed;
            N10 G1Z${binding.secondZEt.text}.F$feed;
            G1X${binding.secondXET.text};
            G1Z${binding.thirdZEt.text};
            G3X${binding.thirdXET.text}.Z${binding.thirdZEt.text}R${binding.thirdREt.text};
            G1X${binding.fourthXET.text};
            G1Z${binding.fourthZEt.text};
            G3X${binding.fourthXET.text}.Z${binding.fourthZEt.text}R${binding.fourthREt.text};
            G1X${binding.fifthXET.text};
            G1Z${binding.fifthZEt.text};
            N20 G1Z${binding.endPositionZEt.text};
            G70P10Q20;
            G0X${binding.endPositionXEt.text}.Z${binding.endPositionZEt.text};
            M9;
            M1;
        """.trimIndent()

        val gCodeHaas = """
            (INT. ROUGH X)
            T0202($toolComment);
            G54;
            G96S$materialSpeed M3;
            G18G99;
            M8;
            G0G42X${binding.g0XET.text}.Z${binding.g0ZEt.text};
            G72P10Q20U${binding.UParameterEt.text}W${binding.WParameterEt.text}D${binding.DParameterEt.text}F$feed;
            N10 G1Z${binding.secondZEt.text}.F$feed;
            G1X${binding.secondXET.text};
            G1Z${binding.thirdZEt.text};
            G3X${binding.thirdXET.text}.Z${binding.thirdZEt.text}R${binding.thirdREt.text};
            G1X${binding.fourthXET.text};
            G1Z${binding.fourthZEt.text};
            G3X${binding.fourthXET.text}.Z${binding.fourthZEt.text}R${binding.fourthREt.text};
            G1X${binding.fifthXET.text};
            G1Z${binding.fifthZEt.text};
            N20 G1Z${binding.endPositionZEt.text};
            G70P10Q20;
            G0X${binding.endPositionXEt.text}.Z${binding.endPositionZEt.text};
            M9;            M1;
        """.trimIndent()

        return Pair(gCodeFanuc, gCodeHaas)
    }

    private fun saveGCodeToPreferences(gCodeFanuc: String, gCodeHaas: String) {
        val sharedPreferences = getSharedPreferences("GCodePreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("GCodeFanuc", gCodeFanuc)
        editor.putString("GCodeHaas", gCodeHaas)
        editor.apply()
    }

    private fun showGCodeDialog(gCodeFanuc: String, gCodeHaas: String) {
        val dialog = GCodeDialogFragment.newInstance(gCodeFanuc, gCodeHaas)
        dialog.show(supportFragmentManager, "GCodeDialog")
    }
}