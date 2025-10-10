package me.twc.kpose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.platform.LocalResources

/**
 * @author 唐万超
 * @date 2025/09/24
 */

@Composable
fun AdaptScreen(
    width: Int = Kpose.adaptScreenWidth,
    content: @Composable () -> Unit
) {
    val fontScale = LocalDensity.current.fontScale
    val displayMetrics = LocalResources.current.displayMetrics
    val widthPixels = displayMetrics.widthPixels.toFloat()
    CompositionLocalProvider(
        LocalDensity provides Density(
            density = widthPixels / width,
            fontScale = fontScale
        )
    ) {
        content()
    }
}

@Composable
fun SystemScreen(content: @Composable () -> Unit) {
    val displayMetrics = LocalResources.current.displayMetrics
    val systemDensity = displayMetrics.density
    val fontScale = LocalDensity.current.fontScale

    CompositionLocalProvider(
        LocalDensity provides Density(
            density = systemDensity,
            fontScale = fontScale
        )
    ) {
        content()
    }
}