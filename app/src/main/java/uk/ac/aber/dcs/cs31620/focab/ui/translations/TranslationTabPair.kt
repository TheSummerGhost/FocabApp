package uk.ac.aber.dcs.cs31620.focab.ui.translations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_dark_onSurface
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_light_onSurface

/**
 * A composable function that displays a pair of tabs for a translation.
 *
 * @param translation The translation to be displayed in the tabs.
 * @param modifier Modifier to be applied to the tabs.
 */

@Composable
fun TranslationTabPair(translation: Translation,
modifier : Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .height(50.dp)
                .width(250.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.focabtableft),
                contentDescription = stringResource(id = R.string.focab_left_tab),
                modifier = Modifier.fillMaxSize())
            Text(translation.firstWord,
                modifier = Modifier.align(Alignment.Center),
                color = md_theme_dark_onSurface
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .height(50.dp)
                .width(250.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.focabtabright),
                contentDescription = stringResource(id = R.string.focab_right_tab),
                modifier = Modifier.fillMaxSize())
            Text(translation.secondWord,
                modifier = Modifier.align(Alignment.Center),
                color = md_theme_light_onSurface
            )
        }
    }
}

@Preview
@Composable
fun ShowTranslationTabPair() {
    val translation = Translation(id = 0, "Hello", "Hola")
    TranslationTabPair(translation = translation)
}