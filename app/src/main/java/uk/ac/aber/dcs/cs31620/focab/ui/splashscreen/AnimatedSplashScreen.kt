package uk.ac.aber.dcs.cs31620.focab.ui.splashscreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_dark_surface
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_light_surface

/**
 * A composable function that displays an animated splash screen.
 *
 * @param navController The navigation controller for navigating to other screens.
 */

@Composable
fun AnimatedSplashScreen(navController : NavHostController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
//        navController.navigate(Screen.Translations.route)
        navController.navigate(Screen.AddLanguage.route)

    }

    Splash(alpha = alphaAnim.value)
}

/**
 * A composable function that displays a splash screen.
 *
 * @param alpha The alpha value for the splash screen.
 */

@Composable
fun Splash(alpha : Float) {
    Box(
        modifier = Modifier
            .background(
                if (isSystemInDarkTheme())
                    md_theme_dark_surface

                else
                    md_theme_light_surface
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Image(painter = painterResource(id = R.drawable.focab_falcon),
            contentDescription = stringResource(id = R.string.focab_logo),

        modifier = Modifier
            .size(500.dp)
            .alpha(alpha = alpha))
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkThemePreview()
{
    Splash(alpha = 1f)
}