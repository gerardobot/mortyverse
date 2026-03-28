package com.minroud.mortyverse.feature.characters.ui.screens.list.paging

import com.minroud.mortyverse.domain.error.DomainError

class CharactersPagingException(
    val domainError: DomainError,
) : RuntimeException(domainError.cause)
