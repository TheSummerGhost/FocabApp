package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme
import java.util.*

const val quizOne = "Quiz One"

/**
 * A composable function that displays the top level layout of the first quiz screen, including the
 * content of the quiz.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param translationsViewModel The view model for the translations screen.
 */

@Composable
fun QuizOneTopLevel(
    navController: NavHostController,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    val translationsList by translationsViewModel.translationList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        QuizOne(
            translationsList = translationsList,
            navController
        )
    }

}

/**
 * A composable function that displays the content of the first quiz screen.
 *
 * @param translationsList The list of translations for the quiz.
 * @param navController The navigation controller for navigating to other screens.
 */

@Composable
fun QuizOne(
    translationsList: List<Translation>?,
    navController: NavHostController,
) {
    GenerateQuestion(translationsList, navController, Screen.QuizOneQuestion.route)
}



@Preview
@Composable
fun QuizOneTopLevelPreview() {
    FocabTheme() {
        Surface {
            val navController = rememberNavController()
            QuizOneTopLevel(navController)
        }
    }
}

