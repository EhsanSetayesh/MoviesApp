package com.ehsansetayesh.core.designsystem.compose.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/**
 * Contains annotations for configuring Compose Previews in both Day and Night modes for different composable functions or screens.
 * These annotations are designed to facilitate the development and design process by allowing developers to visualize
 * how UI components appear under different lighting conditions (daylight and nighttime) directly within the development environment.
 */

/**
 * {@code GeneralPreview} is a meta-annotation that combines both Day and Night mode previews.
 * This annotation can be used to simultaneously apply both preview settings to a Compose function,
 * enabling quick visualization of UI components in both light and dark themes across different composable functions or screens.
 */

private const val neutral_98 = 0xFFfdfdfd
private const val neutral_variant_6 = 0xFF1c2029

@DayPreview
@NightPreview
annotation class GeneralPreview

@Preview(
    name = "Day Mode",
    group = "Theme",
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true,
    showBackground = true,
    backgroundColor = neutral_98
)
annotation class DayPreview

@Preview(
    name = "Night Mode",
    group = "Theme",
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true,
    backgroundColor = neutral_variant_6
)
annotation class NightPreview
