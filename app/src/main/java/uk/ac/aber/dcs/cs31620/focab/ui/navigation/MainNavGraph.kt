package uk.ac.aber.dcs.cs31620.focab.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import uk.ac.aber.dcs.cs31620.focab.ui.components.LanguageSelectScreen
import uk.ac.aber.dcs.cs31620.focab.ui.quiz.*
import uk.ac.aber.dcs.cs31620.focab.ui.splashscreen.AnimatedSplashScreen
import uk.ac.aber.dcs.cs31620.focab.ui.translations.AddTranslationScreen
import uk.ac.aber.dcs.cs31620.focab.ui.translations.TranslationsScreen

/**
 * Main navigation graph for the app, using the [NavHost] composable.
 * This function creates a navigation graph using the NavHost composable, with a
 * start destination of Screen.Splash.route. It also defines several routes and the
 * corresponding composables that should be displayed for each route. The
 * navController variable is a NavController object that is used to navigate
 * between the different screens in the app. The translationsViewModel parameter
 * is a TranslationsViewModel object that is passed to some of the composables in the navigation graph.
 *
 * @param translationsViewModel ViewModel for translations data, default value is obtained from the `viewModel` function.
 */

@Composable
fun MainNavGraph(
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    )
    {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.AddLanguage.route) {
            LanguageSelectScreen(navController)
        }
        composable(route = Screen.Translations.route) {
            TranslationsScreen(navController, translationsViewModel)
        }
        composable(route = Screen.Quizzes.route) {
            QuizMainScreen(navController)
        }
        composable(route = Screen.QuizOne.route) {
            QuizOneTopLevel(navController)
        }
        composable(route = Screen.QuizTwo.route) {
            QuizTwoTopLevel(navController)
        }
        composable(route = Screen.QuizTwoQuestion.route) {
            QuizTwoQuestion(navController)
        }
        composable(route = Screen.AddTranslation.route) {
            AddTranslationScreen(navController, translationsViewModel)
        }
        composable(route = Screen.QuizOneQuestion.route) {
            QuizOneQuestion(navController)
        }
    }
}


