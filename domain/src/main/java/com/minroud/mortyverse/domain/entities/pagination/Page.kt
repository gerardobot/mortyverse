package com.minroud.mortyverse.domain.entities.pagination

data class Page<T>(
    val previousPage: Int?,
    val nextPage: Int?,
    val items: List<T>
) {
    val size = items.size
    val isLastPage = nextPage == null
}
