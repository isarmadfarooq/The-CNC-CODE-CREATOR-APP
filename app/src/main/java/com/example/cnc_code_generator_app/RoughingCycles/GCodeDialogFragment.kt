package com.example.cnc_code_generator_app.RoughingCycles.InternalRoughingCycles

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class GCodeDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_GCODE_FANUC = "gcode_fanuc"
        private const val ARG_GCODE_HAAS = "gcode_haas"

        fun newInstance(gCodeFanuc: String, gCodeHaas: String): GCodeDialogFragment {
            val fragment = GCodeDialogFragment()
            val args = Bundle()
            args.putString(ARG_GCODE_FANUC, gCodeFanuc)
            args.putString(ARG_GCODE_HAAS, gCodeHaas)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val gCodeFanuc = arguments?.getString(ARG_GCODE_FANUC)
        val gCodeHaas = arguments?.getString(ARG_GCODE_HAAS)

        return AlertDialog.Builder(requireContext())
            .setTitle("Generated G-Code")
            .setMessage("FANUC:\n$gCodeFanuc\n\nHAAS:\n$gCodeHaas")
            .setPositiveButton("OK", null)
            .create()
    }
}