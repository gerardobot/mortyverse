package com.minroud.mortyverse.feature.characters.domain.model

data class Page<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>,
) {
    val size = items.size
    val isLastPage = nextPage == null
}
