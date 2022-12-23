package com.alnoor.canvas

data class ProgressValues(
    val primary: Float,
    val secondary: Float,
)

fun calculateCurrentValue(value: Float, max: Float): Float{
    return value / max * 360f
}
