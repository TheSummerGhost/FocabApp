package uk.ac.aber.dcs.cs31620.focab.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing an icon group that contains a filled version of the icon, an outlined version of the icon,
 * and a label for the icon.
 *
 * @property filledIcon ImageVector representing the filled version of the icon.
 * @property outlinedIcon ImageVector representing the outlined version of the icon.
 * @property label Label for the icon, as a string.
 */

data class IconGroup (
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    val label: String
)