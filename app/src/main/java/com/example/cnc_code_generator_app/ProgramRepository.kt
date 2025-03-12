package com.example.cnc_code_generator_app

object ProgramRepository {

    // Data about the current program (control, material, etc.)
    var currentProgramData = ProgramData()

    // Lines or steps in the Program Structure
    val programStructure = mutableListOf<String>()
}
