package com.example.cnc_code_generator_app.FinishingCycles

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles.EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE
import com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles.EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED
import com.example.cnc_code_generator_app.FinishingCycles.External_Finishing_Cycles.EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE
import com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles.INTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE
import com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles.INTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED
import com.example.cnc_code_generator_app.FinishingCycles.Internal_Finishing_Cycles.INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE
import com.example.cnc_code_generator_app.R
import com.example.cnc_code_generator_app.databinding.ActivityFinishingCyclesBinding

class FinishingCyclesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishingCyclesBinding
    private var selectedActivityClass: Class<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Remove this if you haven’t defined enableEdgeToEdge()
        // enableEdgeToEdge()

        binding = ActivityFinishingCyclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) Make sure these classes exist!
        // 2) Make sure you import them if they are in a different package
        val buttonMapping = mapOf(
            binding.externalFCZDirLCBtn to EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE::class.java,
            binding.externalFCXDirLCBtn to EXTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE::class.java,
            binding.externalFCZDirAdvanceBtn to EXTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED::class.java,
            binding.internalFCZDirLCBtn to INTERNAL_FINISHING_CYCLE_Z_DIRECTION_LONG_CODE::class.java,
            binding.internalFCXDirLCBtn to INTERNAL_FINISHING_CYCLE_X_DIRECTION_LONG_CODE::class.java,
            binding.internalFCZDirAdvanceBtn to INTERNAL_FINISHING_CYCLE_Z_DIRECTION_ADVANCED::class.java
        )

        // Resets all take buttons to default "take" state
        fun resetButtons() {
            buttonMapping.keys.forEach { button ->
                button.text = getString(R.string.take)
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.lite_blue))
            }
        }

        // Attach click listeners to each "take" button
        buttonMapping.forEach { (button, activityClass) ->
            button.setOnClickListener {
                // First reset everything
                resetButtons()
                // Mark the clicked button as selected
                button.text = "OK"
                button.setBackgroundColor(Color.parseColor("#EA509CD3"))
                selectedActivityClass = activityClass
            }
        }

        // "Set" button: open the chosen activity
        binding.btnSet.setOnClickListener {
            if (selectedActivityClass != null) {
                startActivity(Intent(this, selectedActivityClass))
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

        // "Close" button: finish the current activity
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}
