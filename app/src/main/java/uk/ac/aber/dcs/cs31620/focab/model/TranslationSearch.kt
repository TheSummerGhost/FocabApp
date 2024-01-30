package uk.ac.aber.dcs.cs31620.focab.model

/**
*
*Data class representing a search for translations.
*@property searchString: String representing the search string.
*@property sortByAlphAsc: Boolean representing whether the search results should be sorted alphabetically in ascending order.
*@property sortByAlphDesc: Boolean representing whether the search results should be sorted alphabetically in descending order.
*@property sortByDateAsc: Boolean representing whether the search results should be sorted by date in ascending order.
*@property sortByDateDesc: Boolean representing whether the search results should be sorted by date in descending order.
 */

data class TranslationSearch (
    var searchString : String = "",
    var sortByAlphAsc : Boolean = false,
    var sortByAlphDesc : Boolean = false,
    var sortByDateAsc : Boolean = false,
    var sortByDateDesc : Boolean = false
        )