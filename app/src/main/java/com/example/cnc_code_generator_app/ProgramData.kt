package com.example.cnc_code_generator_app

data class ProgramData(
    var controlType: String = "FANUC",  // or "HAAS"
    var material: String = "STEEL",
    var zeroPoint: String = "G54",
    var spindleLimit: Int = 2000,
    var coolantOn: String = "M8",
    var coolantOff: String = "M9",
    var spindleDir: String = "M3",
    var optionStop: String = "M1",
    var blankDia: Double = 50.0,
    var faceAllowance: Double = 1.5,
    var toolRetractionX: Double = 200.0,
    var toolRetractionZ: Double = 100.0,
    var programNumber: Int = 1,
    var defaultSurfaceSpeed: Int = 1000,
    var finalSurfaceSpeed: Int = 1000
)
