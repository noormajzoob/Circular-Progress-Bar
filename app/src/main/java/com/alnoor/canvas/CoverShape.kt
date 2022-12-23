package com.alnoor.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class CoverShape(
    private val cornerSize: Float,
    private val centerSize: Float
): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline = Outline.Generic(
        Path().apply {
            val width = size.width
            val height = size.height

            reset()
            arcTo(
                rect = Rect(
                    offset = Offset(x = 0f, y = 0f),
                    size = Size(width = 250f, height = 250f),
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(width - 250, 0f)

            arcTo(
                rect = Rect(
                    offset = Offset(x = width - 250, y = 0f),
                    size = Size(width = 250f, height = 250f),
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(width, height - 250)

            arcTo(
                rect = Rect(
                    offset = Offset(x = width - 250f, y = height - 250f),
                    size = Size(width = 250f, height = 250f),
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
        }
    )
}


@Preview
@Composable
fun PreviewCoverShape() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Box(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(16.dp)
            .background(MaterialTheme.colorScheme.primary, CoverShape(50f, 50f))){

        }
    }
}







