package com.minroud.mortyverse.feature.characters.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacterDetail
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.feature.characters.ui.screens.detail.components.CharacterBio
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.containers.AsyncContent
import com.minroud.mortyverse.ui.containers.MortyverseScaffold
import com.minroud.mortyverse.ui.theme.MortyverseTheme
import com.minroud.mortyverse.ui.topbar.TopBarButton

@Composable
internal fun CharacterDetail(
    topBarButton: TopBarButton,
    characterDetail: DomainResult<MortyverseCharacterDetail>?,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
) {
    val title: String? = when (characterDetail) {
        null -> stringResource(id = R.string.character_detail_title_loading)
        is DomainResult.Success -> characterDetail.data.mortyverseCharacter.name
        is DomainResult.Error -> null
    }

    MortyverseScaffold(
        modifier = modifier,
        button = topBarButton,
        title = title,
    ) { padding ->
        AsyncContent(result = characterDetail, modifier = Modifier.padding(padding)) {
            CharacterBio(
                characterDetail = it,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                imageLoader = imageLoader,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailPreview() {
    MortyverseTheme {
        CharacterDetail(
            topBarButton = TopBarButton.Back {},
            characterDetail = DomainResult.Success(
                MortyverseCharacterDetail(
                    mortyverseCharacter = MortyverseCharacter(
                        id = "1",
                        name = "Rick Sanchez",
                        species = "Human",
                        type = "",
                        status = "Alive",
                        image = "",
                    ),
                    gender = "Male",
                    origin = "Earth (C-137)",
                    location = "Citadel of Ricks",
                    episodes = listOf("S01E01"),
                ),
            ),
            imageLoader = PreviewImageLoader,
        )
    }
}

private object PreviewImageLoader : ImageLoader {
    @Composable
    override fun AsyncImage(
        model: String,
        contentDescription: String?,
        modifier: Modifier,
        contentScale: ContentScale?,
    ) = Box(modifier = modifier)

    @Composable
    override fun Gif(
        data: Any,
        contentDescription: String?,
        modifier: Modifier,
    ) = Box(modifier = modifier)
}
