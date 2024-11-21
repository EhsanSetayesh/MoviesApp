package com.ehsansetayesh.core.designsystem.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.hafhashtad.android780.core.designsystem.compose.utils.colorPack

/**
 * Provides color definitions for custom components that require specific colors
 * not available within the standard Material Design color schema. This class is
 * used to ensure consistency and reusability of custom colors across the application
 * where default material colors are insufficient or inappropriate for the design needs.
 */

val MaterialTheme.colorScheme: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalColorScheme.current

val ColorScheme.primaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_primary_fixed,
        darkColor = md_theme_dark_primary_fixed
    )

val ColorScheme.dark2primaryFixed: Color
    @Composable
    get() = md_theme_dark2_primary_fixed

val ColorScheme.onPrimaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onPrimary_fixed,
        darkColor = md_theme_dark_onPrimary_fixed
    )

val ColorScheme.dark2onPrimaryFixed: Color
    @Composable
    get() = md_theme_dark2_onPrimary_fixed

val ColorScheme.primaryFixedDim: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_primary_fixed_dim,
        darkColor = md_theme_dark_primary_fixed_dim
    )

val ColorScheme.dark2primaryFixedDim: Color
    @Composable
    get() = md_theme_dark2_primary_fixed_dim

val ColorScheme.onPrimaryFixedVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onPrimary_fixed_variant,
        darkColor = md_theme_dark_onPrimary_fixed_variant
    )

val ColorScheme.dark2onPrimaryFixedVariant: Color
    @Composable
    get() = md_theme_dark2_onPrimary_fixed_variant

val ColorScheme.primarySurface: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_primary_surface,
        darkColor = md_theme_dark_primary_surface
    )

val ColorScheme.dark2primarySurface: Color
    @Composable
    get() = md_theme_dark2_primary_surface

val ColorScheme.onPrimarySurface: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onPrimary_surface,
        darkColor = md_theme_dark_onPrimary_surface
    )

val ColorScheme.dark2onPrimarySurface: Color
    @Composable
    get() = md_theme_dark2_onPrimary_surface

val ColorScheme.primarySurfaceVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_primary_surface_variant,
        darkColor = md_theme_dark_primary_surface_variant
    )

val ColorScheme.dark2primarySurfaceVariant: Color
    @Composable
    get() = md_theme_dark2_primary_surface_variant

val ColorScheme.primarySurfaceFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_primary_surface_fixed,
        darkColor = md_theme_dark_primary_surface_fixed
    )

val ColorScheme.dark2primarySurfaceFixed: Color
    @Composable
    get() = md_theme_dark2_primary_surface_fixed

val ColorScheme.secondaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_secondary_fixed,
        darkColor = md_theme_dark_secondary_fixed
    )

val ColorScheme.dark2secondaryFixed: Color
    @Composable
    get() = md_theme_dark2_secondary_fixed

val ColorScheme.secondaryFixedDim: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_secondary_fixed_dim,
        darkColor = md_theme_dark_secondary_fixed_dim
    )

val ColorScheme.dark2secondaryFixedDim: Color
    @Composable
    get() = md_theme_dark2_secondary_fixed_dim

val ColorScheme.onSecondaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSecondary_fixed,
        darkColor = md_theme_dark_onSecondary_fixed
    )

val ColorScheme.dark2onSecondaryFixed: Color
    @Composable
    get() = md_theme_dark2_onSecondary_fixed

val ColorScheme.onSecondaryFixedVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSecondary_fixed_variant,
        darkColor = md_theme_dark_onSecondary_fixed_variant
    )

val ColorScheme.dark2onSecondaryFixedVariant: Color
    @Composable
    get() = md_theme_dark2_onSecondary_fixed_variant

val ColorScheme.tertiaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_tertiary_fixed,
        darkColor = md_theme_dark_tertiary_fixed
    )

val ColorScheme.dark2tertiaryFixed: Color
    @Composable
    get() = md_theme_dark2_tertiary_fixed

val ColorScheme.tertiaryFixedDim: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_tertiary_fixed_dim,
        darkColor = md_theme_dark_tertiary_fixed_dim
    )

val ColorScheme.dark2tertiaryFixedDim: Color
    @Composable
    get() = md_theme_dark2_tertiary_fixed_dim

val ColorScheme.onTertiaryFixed: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onTertiary_fixed,
        darkColor = md_theme_dark_onTertiary_fixed
    )

val ColorScheme.dark2onTertiaryFixed: Color
    @Composable
    get() = md_theme_dark2_onTertiary_fixed

val ColorScheme.onTertiaryFixedVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onTertiary_fixed_variant,
        darkColor = md_theme_dark_onTertiary_fixed_variant
    )

val ColorScheme.dark2onTertiaryFixedVariant: Color
    @Composable
    get() = md_theme_dark2_onTertiary_fixed_variant

val ColorScheme.surfaceSaj: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_surface_saj,
        darkColor = md_theme_dark_surface_saj
    )

val ColorScheme.dark2surfaceSaj: Color
    @Composable
    get() = md_theme_dark2_surface_saj

val ColorScheme.surfaceRog: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_surface_rog,
        darkColor = md_theme_dark_surface_rog
    )

val ColorScheme.surfaceBg: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_surface_bg,
        darkColor = md_theme_dark_surface_bg
    )

val ColorScheme.dark2surfaceRog: Color
    @Composable
    get() = md_theme_dark2_surface_rog

val ColorScheme.surfaceFix: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_surface_fix,
        darkColor = md_theme_dark_surface_fix
    )

val ColorScheme.dark2surfaceFix: Color
    @Composable
    get() = md_theme_dark2_surface_fix

val ColorScheme.onSurfaceFix: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSurface_fix,
        darkColor = md_theme_dark_onSurface_fix
    )

val ColorScheme.dark2onSurfaceFix: Color
    @Composable
    get() = md_theme_dark2_onSurface_fix

val ColorScheme.shadow: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_shadow,
        darkColor = md_theme_dark_shadow
    )

val ColorScheme.dark2shadow: Color
    @Composable
    get() = md_theme_dark2_shadow

val ColorScheme.warning: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_warning,
        darkColor = md_theme_dark_warning
    )

val ColorScheme.warningContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_warning_container_variant,
        darkColor = md_theme_dark_warning_container_variant
    )

val ColorScheme.onWarningContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onWarning_container_variant,
        darkColor = md_theme_dark_onWarning_container_variant
    )

val ColorScheme.dark2warning: Color
    @Composable
    get() = md_theme_dark2_warning

val ColorScheme.onWarning: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onWarning,
        darkColor = md_theme_dark_onWarning
    )

val ColorScheme.dark2onWarning: Color
    @Composable
    get() = md_theme_dark2_onWarning

val ColorScheme.warningContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_warning_container,
        darkColor = md_theme_dark_warning_container
    )

val ColorScheme.dark2warningContainer: Color
    @Composable
    get() = md_theme_dark2_warning_container

val ColorScheme.onWarningContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onWarning_container,
        darkColor = md_theme_dark_onWarning_container
    )

val ColorScheme.dark2onWarningContainer: Color
    @Composable
    get() = md_theme_dark2_onWarning_container

val ColorScheme.success: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_success,
        darkColor = md_theme_dark_success
    )

val ColorScheme.dark2success: Color
    @Composable
    get() = md_theme_dark2_success

val ColorScheme.onSuccess: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSuccess,
        darkColor = md_theme_dark_onSuccess
    )

val ColorScheme.dark2onSuccess: Color
    @Composable
    get() = md_theme_dark2_onSuccess

val ColorScheme.successContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_success_container,
        darkColor = md_theme_dark_success_container
    )

val ColorScheme.dark2successContainer: Color
    @Composable
    get() = md_theme_dark2_success_container

val ColorScheme.onSuccessContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSuccess_container,
        darkColor = md_theme_dark_onSuccess_container
    )

val ColorScheme.dark2onSuccessContainer: Color
    @Composable
    get() = md_theme_dark2_onSuccess_container

val ColorScheme.info: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_info,
        darkColor = md_theme_dark_info
    )

val ColorScheme.dark2info: Color
    @Composable
    get() = md_theme_dark2_info

val ColorScheme.onInfo: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onInfo,
        darkColor = md_theme_dark_onInfo
    )

val ColorScheme.dark2onInfo: Color
    @Composable
    get() = md_theme_dark2_onInfo

val ColorScheme.infoContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_info_container,
        darkColor = md_theme_dark_info_container
    )

val ColorScheme.dark2infoContainer: Color
    @Composable
    get() = md_theme_dark2_info_container

val ColorScheme.onInfoContainer: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onInfo_container,
        darkColor = md_theme_dark_onInfo_container
    )

val ColorScheme.dark2onInfoContainer: Color
    @Composable
    get() = md_theme_dark2_onInfo_container

val ColorScheme.illustrationBg: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_illustration_bg,
        darkColor = md_theme_dark_illustration_bg
    )

val ColorScheme.illustration1: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_illustration_1,
        darkColor = md_theme_dark_illustration_1
    )

val ColorScheme.illustration2: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_illustration_2,
        darkColor = md_theme_dark_illustration_2
    )

val ColorScheme.illustration3: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_illustration_3,
        darkColor = md_theme_dark_illustration_3
    )

val ColorScheme.errorContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_error_container_variant,
        darkColor = md_theme_dark_error_container_variant
    )

val ColorScheme.onErrorContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onError_container_variant,
        darkColor = md_theme_dark_onError_container_variant
    )

val ColorScheme.infoContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_info_container_variant,
        darkColor = md_theme_dark_info_container_variant
    )

val ColorScheme.onInfoContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onInfo_container_variant,
        darkColor = md_theme_dark_onInfo_container_variant
    )

val ColorScheme.successContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_success_container_variant,
        darkColor = md_theme_dark_success_container_variant
    )

val ColorScheme.onSuccessContainerVariant: Color
    @Composable
    get() = colorPack(
        lightColor = md_theme_light_onSuccess_container_variant,
        darkColor = md_theme_dark_onSuccess_container_variant
    )

// Old Design System Custom Colors
val ColorScheme.hafhashtadPrimaryColor: Color
    @Composable
    get() = hafhashtad_primary_color

val ColorScheme.hafhashtadBackgroundDisabledColor: Color
    @Composable
    get() = hafhashtad_background_disabled_color

val ColorScheme.hafhashtadContentDisabledColor: Color
    @Composable
    get() = hafhashtad_content_disabled_color

val ColorScheme.staticWhite: Color
    @Composable
    get() = static_white
