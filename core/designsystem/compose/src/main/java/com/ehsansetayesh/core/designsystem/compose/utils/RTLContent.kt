package ir.hafhashtad.android780.core.designsystem.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/**
 * Ensures Right-to-Left (RTL) layout handling is applied for supported locales.
 * This should be used at the root of the composable hierarchy within the main activity's setContent block.
 * It configures the content direction to adhere to RTL when necessary, providing correct
 * alignment and text direction for RTL languages.
 *
 * Usage:
 * ```
 * class MainActivity : ComponentActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContent {
 *           HafhashtadTheme{
 *             RTLContent {
 *                 // Your composable here
 *             }
 *           }
 *         }
 *     }
 * }
 * ```
 */

@Composable
fun RTLContent(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}