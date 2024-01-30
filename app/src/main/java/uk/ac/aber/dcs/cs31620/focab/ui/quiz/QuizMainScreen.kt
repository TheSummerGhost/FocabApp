package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.ui.components.TopLevelScaffold
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen

var quizOneScore = 0
var quizTwoScore = 0

/**
 * A composable function that displays the main screen for the quiz.
 *
 * @param navController The navigation controller for navigating to other screens.
 */

@Composable
fun QuizMainScreen(
    navController : NavHostController = rememberNavController(),
) {

    val coroutineScope = rememberCoroutineScope()
    TopLevelScaffold(
        navController = navController,
        coroutineScope = coroutineScope
    ) { innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        )
        {
            QuizMainScreenContent(
                modifier = Modifier.padding(8.dp),
                navController = navController
            )
        }
    }
}