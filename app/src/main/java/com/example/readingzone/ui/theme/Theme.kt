package com.example.readingzone.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = darkColorScheme(
    primary = Color(0xff8f4c35),
    onPrimary = Color(0xffffffff),
    primaryContainer = Color(0xffffdbcf),
    onPrimaryContainer = Color(0xff390c00),
    //inversePrimary = Color(0xFF984800),
    secondary = Color(0xff77574c),
    onSecondary = Color(0xffffffff),
    secondaryContainer = Color(0xffffdbcf),
    onSecondaryContainer = Color(0xff2c160e),
    tertiary = Color(0xff6a5e2f),
    onTertiary = Color(0xffffffff),
    tertiaryContainer = Color(0xfff3e2a7),
    onTertiaryContainer = Color(0xff221b00),
    background = Color(0xfffff8f6),
    onBackground = Color(0xff231917),
    surface = Color(0xfffff8f6),
    onSurface = Color(0xff231917),
    surfaceVariant = Color(0xff52443c),
    onSurfaceVariant = Color(0xffd7c2b8),
//    surfaceTint = Color(0xff),
//    inverseSurface = Color(0xff),
//    inverseOnSurface = Color(0xff),
    error = Color(0xffba1a1a),
    onError = Color(0xffffffff),
    errorContainer = Color(0xffffdad6),
    onErrorContainer = Color(0xff410002),
    outline = Color(0xff85736e),
    outlineVariant = Color(0xffd8c2bb),
    scrim = Color(0xff000000)
)

private val DarkColorScheme = lightColorScheme(
    primary = Color(0xFFffb59c),
    onPrimary = Color(0xff55200c),
    primaryContainer = Color(0xff723520),
    onPrimaryContainer = Color(0xffffdbcf),
    //inversePrimary = Color(0xFF984800),
    secondary = Color(0xffe7bdb0),
    onSecondary = Color(0xff442a21),
    secondaryContainer = Color(0xff5d4036),
    onSecondaryContainer = Color(0xffffdbcf),
    tertiary = Color(0xffd6c68d),
    onTertiary = Color(0xff393005),
    tertiaryContainer = Color(0xff51461a),
    onTertiaryContainer = Color(0xfff3e2a7),
    background = Color(0xff1a110f),
    onBackground = Color(0xff201a17),
    surface = Color(0xff1a110f),
    onSurface = Color(0xfff1dfd9),
    surfaceVariant = Color(0xff423733),
    onSurfaceVariant = Color(0xffd8c2bb),
//    surfaceTint = Color(0xff),
//    inverseSurface = Color(0xff),
//    inverseOnSurface = Color(0xff),
    error = Color(0xffffb4ab),
    onError = Color(0xff690005),
    errorContainer = Color(0xff93000a),
    onErrorContainer = Color(0xffffdad6),
    outline = Color(0xffa08d87),
    outlineVariant = Color(0xff53433f),
    scrim = Color(0xff000000)

)

@Composable
fun ReadingZoneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}