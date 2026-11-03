package com.denser.june.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeInput
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.glass.GlassStyle
import dev.chrisbanes.haze.hazeGlass
import dev.chrisbanes.haze.hazeSource

@Composable
fun JuneFloatingActionBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = containerColor,
        shadowElevation = 8.dp,
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
fun JuneFloatingAction(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    selected: Boolean? = null,
    enabled: Boolean = true,
    containerColor: Color = when (selected) {
        true -> MaterialTheme.colorScheme.surfaceBright
        false -> Color.Transparent
        else -> MaterialTheme.colorScheme.primary
    },
    contentColor: Color = when (selected) {
        true -> MaterialTheme.colorScheme.onSurface
        false -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onPrimary
    },
    disabledContainerColor: Color = Color.Transparent,
    disabledContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
) {
    val hazeState = remember { HazeState() }
    Box(modifier = modifier.hazeSource(state = hazeState)) {
        Box(
            modifier = Modifier
                .hazeGlass(
                    input = HazeInput.Sources(hazeState),
                    style = GlassStyle.regular.then {
                        shape(CircleShape)
                        tint(Color.White.copy(alpha = 0.16f))
                    }
                )
                .clickable(enabled = enabled) { onClick() }
                .height(40.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                icon?.invoke()
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (enabled) contentColor else disabledContentColor
                )
            }
        }
    }
}
