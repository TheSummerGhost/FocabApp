package uk.ac.aber.dcs.cs31620.focab.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.TranslationSearch

const val filterButtons = "FilterButtons"

/**
 * Composable function that generates a card containing a button for sorting translations alphabetically.
 *
 * @param modifier Modifier to be applied to the card.
 * @param translationSearch TranslationSearch object that holds the current search criteria.
 * @param updateSearch Callback function that updates the search criteria with the new sorting criteria.
 */

@Composable
fun FilterButtons(
    modifier: Modifier = Modifier,
    translationSearch: TranslationSearch,
    updateSearch: (TranslationSearch) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        Row {
            Button(
                onClick = {
                    Log.i(filterButtons, "ALPH TOGGLE PRESSED")
                    updateSearch(
                        TranslationSearch(
                            sortByAlphAsc = !translationSearch.sortByAlphAsc
                        )
                    )
                },
                ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                )
                {
                    Icon(
                        imageVector = Icons.Default.SortByAlpha,
                        contentDescription = stringResource(id = R.string.add_new_translation),
                    )
                }
            }
        }
    }
}


