package com.example.cnc_code_generator_app.facingCycles

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cnc_code_generator_app.MainActivity
import com.example.cnc_code_generator_app.databinding.ActivityFacingCycleUpDirectionG94Binding

class FacingCycleUpDirectionG94 : AppCompatActivity() {

    private lateinit var binding: ActivityFacingCycleUpDirectionG94Binding

    // We use this SharedPreferences only while the app is open.
    // If you clear them on the app's first screen, data will be lost upon app close.
    private var ephemeralPrefs: SharedPreferences? = null

    // Data retrieved from the previous activity (FacingCycleUpDirActivity)
    private var selectedInsert: String? = null
    private var noseComp: String? = null
    private var noseRadius: String? = null
    private var finishingAllowance: String? = null
    private var zeroPoint: String? = null
    private var toolNumber: String? = null
    private var coolantOn: String? = null
    private var coolantOff: String? = null
    private var spindleDir: String? = null
    private var optionStop: String? = null
    private var toolRetractX: String? = null
    private var toolRetractZ: String? = null
    private var toolComment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the layout using View Binding
        binding = ActivityFacingCycleUpDirectionG94Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ephemeral SharedPreferences
        // (Will persist only while the app remains in memory, if you clear them on app start)
        ephemeralPrefs = getSharedPreferences("FacingCycleUpPrefs", Context.MODE_PRIVATE)

        // Retrieve data from previous activity
        retrieveDataFromPreviousActivity()

        // Optionally, you can load previously saved data (if you want to keep it while app is still running)
        // loadSavedData()

        // Button listeners
        binding.setBtn.setOnClickListener { onSetButtonClicked() }
        binding.deleteBtn.setOnClickListener { clearAllFields() }
    }

    /**
     * Retrieves data from the Intent sent by FacingCycleUpDirActivity.
     */
    private fun retrieveDataFromPreviousActivity() {
        intent?.let {
            selectedInsert       = it.getStringExtra("SELECTED_INSERT")
            noseComp             = it.getStringExtra("NOSE_COMP")
            noseRadius           = it.getStringExtra("NOSE_RADIUS")
            finishingAllowance   = it.getStringExtra("FINISHING_ALLOWANCE")
            zeroPoint            = it.getStringExtra("ZERO_POINT")
            toolNumber           = it.getStringExtra("TOOL_NUMBER")
            coolantOn            = it.getStringExtra("COOLANT_ON")
            coolantOff           = it.getStringExtra("COOLANT_OFF")
            spindleDir           = it.getStringExtra("SPINDLE_DIR")
            optionStop           = it.getStringExtra("OPTION_STOP")
            toolRetractX         = it.getStringExtra("TOOL_RETRACTION_X")
            toolRetractZ         = it.getStringExtra("TOOL_RETRACTION_Z")
            toolComment          = it.getStringExtra("TOOL_COMMENT")
        }
    }

    /**
     * Called when the user presses the "SET" button.
     * 1) Validate if all fields here are filled.
     * 2) If valid, store everything (previous data + these fields) into ephemeral SharedPreferences.
     * 3) Clear fields.
     * 4) Move to MainActivity.
     */
    private fun onSetButtonClicked() {
        // Check if any field in this activity is empty
        if (!areFieldsValid()) {
            Toast.makeText(this, "Please fill all fields before saving!", Toast.LENGTH_SHORT).show()
            return
        }

        // If everything is valid, store data in ephemeral SharedPreferences
        val editor = ephemeralPrefs?.edit()

        // 1. Store the data retrieved from previous activity
        editor?.putString("SELECTED_INSERT", selectedInsert)
        editor?.putString("NOSE_COMP", noseComp)
        editor?.putString("NOSE_RADIUS", noseRadius)
        editor?.putString("FINISHING_ALLOWANCE", finishingAllowance)
        editor?.putString("ZERO_POINT", zeroPoint)
        editor?.putString("TOOL_NUMBER", toolNumber)
        editor?.putString("COOLANT_ON", coolantOn)
        editor?.putString("COOLANT_OFF", coolantOff)
        editor?.putString("SPINDLE_DIR", spindleDir)
        editor?.putString("OPTION_STOP", optionStop)
        editor?.putString("TOOL_RETRACTION_X", toolRetractX)
        editor?.putString("TOOL_RETRACTION_Z", toolRetractZ)
        editor?.putString("TOOL_COMMENT", toolComment)

        // 2. Store the data from the fields in THIS activity
        with(binding) {
            editor?.putString("S_VALUE", SParameterEt.text.toString().trim())
            editor?.putString("F_VALUE", FParameterEt.text.toString().trim())
            editor?.putString("W_VALUE", WParameterEt.text.toString().trim())
            editor?.putString("START_X", g0XET.text.toString().trim())
            editor?.putString("START_Z", g0ZEt.text.toString().trim())
            editor?.putString("END_X", endPositionXEt.text.toString().trim())
            editor?.putString("END_Z", endPositionZEt.text.toString().trim())
        }

        // Apply changes
        editor?.apply()

        // Show a Toast message
        Toast.makeText(this, "Data saved in SharedPreferences (ephemeral)!", Toast.LENGTH_SHORT).show()

        // Clear fields
        clearAllFields()

        // Navigate to MainActivity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Checks if all required fields in THIS activity are non-empty.
     */
    private fun areFieldsValid(): Boolean = with(binding) {
        return@with !(SParameterEt.text.isNullOrBlank() ||
                FParameterEt.text.isNullOrBlank() ||
                WParameterEt.text.isNullOrBlank() ||
                g0XET.text.isNullOrBlank() ||
                g0ZEt.text.isNullOrBlank() ||
                endPositionXEt.text.isNullOrBlank() ||
                endPositionZEt.text.isNullOrBlank()
                )
    }

    /**
     * Clears all fields in THIS activity. Does NOT remove data from ephemeral SharedPreferences.
     * If you want to remove data from ephemeral SharedPreferences as well, you can do that here.
     */
    private fun clearAllFields() {
        with(binding) {
            SParameterEt.setText("")
            FParameterEt.setText("")
            WParameterEt.setText("")
            g0XET.setText("")
            g0ZEt.setText("")
            endPositionXEt.setText("")
            endPositionZEt.setText("")
        }
        Toast.makeText(this, "Fields cleared!", Toast.LENGTH_SHORT).show()
    }

    /**
     * If you want to load data (while the app is still open) from ephemeral SharedPreferences,
     * you can implement it here. This is optional, depending on your design.
     */
    private fun loadSavedData() {
        ephemeralPrefs?.let { sp ->
            binding.SParameterEt.setText(sp.getString("S_VALUE", "200"))
            binding.FParameterEt.setText(sp.getString("F_VALUE", "0.24"))
            binding.WParameterEt.setText(sp.getString("W_VALUE", "0.1"))
            binding.g0XET.setText(sp.getString("START_X", "X0."))
            binding.g0ZEt.setText(sp.getString("START_Z", "Z3."))
            binding.endPositionXEt.setText(sp.getString("END_X", "X52."))
            binding.endPositionZEt.setText(sp.getString("END_Z", "Z0."))
        }
    }
}

