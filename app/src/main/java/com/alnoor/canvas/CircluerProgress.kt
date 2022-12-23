package com.alnoor.canvas

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    primaryProgressColor: Color = MaterialTheme.colorScheme.primary,
    secondaryProgressColor: Color = primaryProgressColor.copy(alpha = 0.6f),
    backgroundProgressColor: Color = primaryProgressColor.copy(alpha = 0.1f),
    values: ()-> ProgressValues,
    maxValue: Float,
    strokeWidth: Float,
    animationSpec: AnimationSpec<Float> = tween(500),
    centerContent: (@Composable ()-> Unit)? = null,
){
    val primaryValue = values().primary
    val secondaryValue = values().secondary

    val primaryWrappedValue =
        if(primaryValue > maxValue) maxValue
        else{
            if (primaryValue < 0) 0f else primaryValue
        }
    val secondaryWrappedValue =
        if(secondaryValue > maxValue) secondaryValue
        else{
            if (secondaryValue < 0) 0f else secondaryValue
        }

    val animatedPrimaryValue = animateFloatAsState(
        targetValue = primaryWrappedValue,
        animationSpec = animationSpec
    )

    val animatedSecondryValue = animateFloatAsState(
        targetValue = secondaryWrappedValue,
        animationSpec = animationSpec
    )

    Box(modifier = modifier){
        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){

            drawProgress(
                color = backgroundProgressColor.copy(alpha = 0.5f),
                strokeWidth = strokeWidth
            )

            drawProgress(
                color = secondaryProgressColor,
                value = calculateCurrentValue(animatedSecondryValue.value, maxValue),
                strokeWidth = strokeWidth
            )

            drawProgress(
                color = primaryProgressColor,
                value = calculateCurrentValue(animatedPrimaryValue.value, maxValue),
                strokeWidth = strokeWidth
            )

        }

        centerContent?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(strokeWidth.dp),
                contentAlignment = Alignment.Center
            ){
                it()
            }
        }
    }
}

private fun DrawScope.drawProgress(
    value: Float = 360f,
    color: Color,
    strokeWidth: Float
){
    drawArc(
        color = color,
        startAngle = 270f,
        sweepAngle = value,
        useCenter = false,
        style = Stroke(
            width = strokeWidth,
            cap = StrokeCap.Round
        )
    )
}