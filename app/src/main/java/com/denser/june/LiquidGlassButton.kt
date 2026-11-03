
package com.denser.june 

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeInput
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.glass.GlassStyle
import dev.chrisbanes.haze.hazeGlass
import dev.chrisbanes.haze.hazeSource

@Composable
fun LiquidGlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hazeState = remember { HazeState() }

    Box(
        modifier = modifier.hazeSource(state = hazeState)
    ) {
        Box(
            modifier = Modifier
                .hazeGlass(
                    input = HazeInput.Sources(hazeState),
                    style = GlassStyle.regular.then {
                        shape(RoundedCornerShape(24.dp))
                        tint(Color.White.copy(alpha = 0.16f))
                    }
                )
                .clickable { onClick() }
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
