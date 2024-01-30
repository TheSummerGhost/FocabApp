package uk.ac.aber.dcs.cs31620.focab.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.screens

/**
 * Composable function that generates a bottom navigation bar for the main pages of the app.
 *
 * @param navController NavController for navigating between screens in the app.
 */

@Composable
fun MainPageNavigationBar(
    navController : NavController
) {
    val icons = mapOf(
        Screen.Translations to IconGroup(
            filledIcon = Icons.Filled.Translate,
            outlinedIcon = Icons.Outlined.Translate,
            label = stringResource(id = R.string.translations)
        ),
        Screen.Quizzes to IconGroup(
            filledIcon = Icons.Filled.Quiz,
            outlinedIcon = Icons.Outlined.Quiz,
            label = stringResource(id = R.string.quizzes)
        )
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach {screen ->
            val isSelected = currentDestination?.hierarchy?.any{
                it.route == screen.route} == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (
                                if (isSelected)
                                    icons[screen]!!.filledIcon
                                else
                                    icons[screen]!!.outlinedIcon),
                        contentDescription = labelText
                    )
                },
                label = {Text(labelText)},
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ShowMainPageNavigationBar() {
    FocabTheme() {
        val navController = rememberNavController()
        MainPageNavigationBar(navController)
    }
}
