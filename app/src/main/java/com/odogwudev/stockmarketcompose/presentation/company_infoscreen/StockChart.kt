package com.odogwudev.stockmarketcompose.presentation.company_infoscreen

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odogwudev.stockmarketcompose.domain.model.IntraDayInfo
import kotlin.math.round
import kotlin.math.roundToInt

// i would be using canvases here
@Composable
fun StockChart(
    infos: List<IntraDayInfo> = emptyList(),
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Green
) {
    val spacing = 100f
    val transparentGraphColor =
        remember {// remember because we dont want to recaluclate values everytime on recomposition
            graphColor.copy(alpha = 0.5f)
        }
    val upperValue = remember(infos) {///remember value is recalculated when infos change
        (infos.maxOfOrNull { it.close }?.plus(1))?.roundToInt() ?: 0
    }
    val lowerValue = remember(infos) {
        infos.minOfOrNull { it.close }?.toInt() ?: 0
    }

    //when you want to draw canvas you need paint, when you want to draw canvas you use the native canvas compose canvas cant yet draw

    val density =
        LocalDensity.current//we pass it here so when density changes we recalculate thispaint object
    val textPaint = remember {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {

        val spacePerHour =
            (size.width - spacing) / infos.size //this refers to x axis(space per hour is value in pixel of how much we have in hour between values
        (0 until infos.size - 1 step 2).forEach { i -> // just loop through infos an take every 2 hours
            val info = infos[i]
            val hour = info.date.hour

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }
        val priceStep = (upperValue - lowerValue) / 5f
        (0..5).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    30f,
                    size.height - spacing - i * height / 5f,
                    textPaint
                )
            }
        }

        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in infos.indices) {
                val info = infos[i]
                val nextInfo = infos.getOrNull(i + 1) ?: infos.last()
                val leftRatio =
                    (info.close - lowerValue) / (upperValue - lowerValue) //percentage value of info point
                val rightRatio = (info.close - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + i + 1 * spacePerHour
                val y2 = height - spacing - (rightRatio * height).toFloat()
                if (i == 0) {
                    moveTo(
                        x1, y1
                    )
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )//mathemmatic calculations to get a smooth pattern (control points determines how a curve is rounded)
            }
        }

        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}