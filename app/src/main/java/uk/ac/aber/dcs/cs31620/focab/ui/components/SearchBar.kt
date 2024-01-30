package uk.ac.aber.dcs.cs31620.focab.ui.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.TranslationSearch

const val searchBar = "SearchBar"

/**
 * Composable function that generates a search bar.
 * The SearchBar function generates a search bar using the TextField
 * composable from the Material Design library. The search bar has a leading icon,
 * which is a search icon from the Material Design icon set. The search bar updates
 * the search using the updateSearch function, which takes a TranslationSearch
 * object as its argument. The textValue variable is a MutableState object that
 * holds the current value of the search bar.
 *
 * @param modifier Modifier to be applied to the search bar.
 * @param translationSearch TranslationSearch object to be used for searching.
 * @param updateSearch Function that updates the search. Default value is an empty function.
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    translationSearch: TranslationSearch,
    updateSearch: (TranslationSearch) -> Unit = {},
) {
    val textValue = rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = textValue.value, onValueChange = {
        textValue.value = it

        updateSearch(
            TranslationSearch(
                searchString = textValue.value
            )
        )
    },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(R.string.searchBar))
        }
    )
}