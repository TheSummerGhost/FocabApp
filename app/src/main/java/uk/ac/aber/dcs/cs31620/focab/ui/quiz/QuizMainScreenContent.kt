package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen

/**
 * A composable function that displays the content of the main quiz screen, including two buttons
 * that navigate to the first and second quiz screens.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param modifier A modifier to be applied to the root element of the composable.
 * @param translationsViewModel The view model for the translations screen.
 */

@Composable
fun QuizMainScreenContent (
    navController: NavHostController,
    modifier : Modifier = Modifier,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    val translationsList by translationsViewModel.translationList.observeAsState()
    var canTakeQuiz = true
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        if (translationsList?.isEmpty() == true) {
            canTakeQuiz = false
        }
        Button(
            onClick = { navController.navigate(Screen.QuizOne.route) },
            modifier = Modifier
                .height(100.dp)
                .width(200.dp),
            enabled = canTakeQuiz
        ) {
            Text(text = "Translation Matcher")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate(Screen.QuizTwo.route) },
            modifier = Modifier
                .height(100.dp)
                .width(200.dp),
            enabled = canTakeQuiz
        ) {
            Text(text = "Spell Checker")
        }

    }
}