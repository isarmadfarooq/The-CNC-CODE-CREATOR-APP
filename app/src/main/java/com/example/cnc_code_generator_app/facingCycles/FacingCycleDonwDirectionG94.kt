package com.example.cnc_code_generator_app.facingCycles

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleDonwDirectionG94Binding

class FacingCycleDownDirectionG94 : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleDonwDirectionG94Binding
    private var sharedPreferences: SharedPreferences? = null

    // Variables for received data
    private var selectedInsert: String? = null
    private var noseRadius: Double = 0.8
    private var feedFromPrev: String? = null
    private var toolNumber: String? = null
    private var toolComment: String? = null
    private var zeroPoint: String? = null
    private var coolantOn: String? = null
    private var coolantOff: String? = null
    private var spindleDir: String? = null
    private var optionStop: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewBinding
        binding = ActivityFacingCycleDonwDirectionG94Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences (only in-memory, not persistent after closing app)
        sharedPreferences = getSharedPreferences("FacingCyclePrefs", Context.MODE_PRIVATE)

        // Retrieve passed data
        getDataFromIntent()

        // Load saved data if available
        loadSavedData()

        // Button Listeners
        binding.setBtn.setOnClickListener { generateGCode() }
        binding.deleteBtn.setOnClickListener { clearFields() }
    }

    /**
     * Retrieves extras from the intent passed from the previous activity
     */
    private fun getDataFromIntent() {
        intent?.let {
            selectedInsert  = it.getStringExtra("SELECTED_INSERT")
            noseRadius      = it.getDoubleExtra("NOSE_RADIUS", 0.8)
            feedFromPrev    = it.getStringExtra("FEED")
            toolNumber      = it.getStringExtra("TOOL_NUMBER")
            toolComment     = it.getStringExtra("TOOL_COMMENT")
            zeroPoint       = it.getStringExtra("ZERO_POINT")
            coolantOn       = it.getStringExtra("COOLANT_ON")
            coolantOff      = it.getStringExtra("COOLANT_OFF")
            spindleDir      = it.getStringExtra("SPINDLE_DIR")
            optionStop      = it.getStringExtra("OPTION_STOP")
        }
    }

    /**
     * Load saved values from SharedPreferences (only for the current session)
     */
    private fun loadSavedData() {
        binding.apply {
            SParameterEt.setText(sharedPreferences?.getString("S_VALUE", "200"))
            FParameterEt.setText(sharedPreferences?.getString("F_VALUE", feedFromPrev ?: "0.24"))
            WParameterEt.setText(sharedPreferences?.getString("W_VALUE", "0.1"))
            g0XET.setText(sharedPreferences?.getString("START_X", "X52."))
            g0ZEt.setText(sharedPreferences?.getString("START_Z", "Z3."))
            endPositionXEt.setText(sharedPreferences?.getString("END_X", "X0."))
            endPositionZEt.setText(sharedPreferences?.getString("END_Z", "Z0.1"))
        }
    }

    /**
     * Generates G-Code based on user input and navigates back to MainActivity
     */
    private fun generateGCode() {
        val sValue = binding.SParameterEt.text.toString().trim()
        val fValue = binding.FParameterEt.text.toString().trim()
        val wValue = binding.WParameterEt.text.toString().trim()
        val startX = binding.g0XET.text.toString().trim()
        val startZ = binding.g0ZEt.text.toString().trim()
        val endX = binding.endPositionXEt.text.toString().trim()
        val endZ = binding.endPositionZEt.text.toString().trim()

        if (sValue.isEmpty() || fValue.isEmpty() || wValue.isEmpty() ||
            startX.isEmpty() || startZ.isEmpty() || endX.isEmpty() || endZ.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields before proceeding.", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences?.edit()
        editor?.putBoolean("isFacingCycleDownDataSaved", true) // Save flag
        editor?.putString("S_VALUE", sValue)
        editor?.putString("F_VALUE", fValue)
        editor?.putString("W_VALUE", wValue)
        editor?.putString("START_X", startX)
        editor?.putString("START_Z", startZ)
        editor?.putString("END_X", endX)
        editor?.putString("END_Z", endZ)
        editor?.putString("TOOL_NUMBER", toolNumber)
        editor?.putString("TOOL_COMMENT", toolComment)
        editor?.putString("ZERO_POINT", zeroPoint)
        editor?.putString("COOLANT_ON", coolantOn)
        editor?.putString("COOLANT_OFF", coolantOff)
        editor?.putString("SPINDLE_DIR", spindleDir)
        editor?.putString("OPTION_STOP", optionStop)
        editor?.apply()

        Toast.makeText(this, "G-Code Saved!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Clears all input fields, resets SharedPreferences, and moves to MainActivity
     */
    private fun clearFields() {
        // Clear SharedPreferences
        sharedPreferences?.edit()?.clear()?.apply()

        // Clear UI Fields
        binding.apply {
            SParameterEt.setText("")
            FParameterEt.setText("")
            WParameterEt.setText("")
            g0XET.setText("")
            g0ZEt.setText("")
            endPositionXEt.setText("")
            endPositionZEt.setText("")
        }

        // Clear retrieved data from previous activity
        selectedInsert = null
        noseRadius = 0.8
        feedFromPrev = null
        toolNumber = null
        toolComment = null
        zeroPoint = null
        coolantOn = null
        coolantOff = null
        spindleDir = null
        optionStop = null

        Toast.makeText(this, "All Data Cleared!", Toast.LENGTH_SHORT).show()

        // Navigate to MainActivity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
