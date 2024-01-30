package uk.ac.aber.dcs.cs31620.focab.ui.navigation

/**
 * Sealed class representing the screens in the app.
 *
 * @property route String representation of the screen's route.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object AddLanguage : Screen("add_language")
    object AddTranslation : Screen("add_translation")
    object Translations : Screen("translations")
    object Quizzes : Screen("quizzes")
    object QuizOne : Screen("quiz_one")
    object QuizTwo : Screen("quiz_two")
    object QuizOneQuestion : Screen("quiz_one_question")
    object QuizTwoQuestion : Screen("quiz_two_question")
}

/**
 * List of screens accessible from the bottom bar
 */

val screens = listOf(
    Screen.Translations,
    Screen.Quizzes,
)