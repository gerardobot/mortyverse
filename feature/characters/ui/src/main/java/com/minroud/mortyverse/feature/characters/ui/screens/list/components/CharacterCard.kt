package com.minroud.mortyverse.feature.characters.ui.screens.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.ui.adapters.imageloader.ImageLoader
import com.minroud.mortyverse.ui.theme.MortyverseTheme

@Composable
internal fun CharacterCard(
    character: MortyverseCharacter,
    imageLoader: ImageLoader,
    onClick: (String) -> Unit,
) = Card(
    modifier = Modifier
        .padding(bottom = 4.dp, top = 4.dp, start = 8.dp, end = 8.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable(onClick = { onClick(character.id) }),
    shape = RectangleShape,
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
        ) {
            imageLoader.AsyncImage(
                model = character.image,
                contentDescription = stringResource(
                    R.string.character_card_image_description,
                    character.name,
                ),
                modifier = Modifier,
                contentScale = null,
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.onSurface,
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = character.type.ifEmpty { character.species },
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = "Status: ${character.status}",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterCardPreview() {
    val previewImageLoader = object : ImageLoader {
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

    MortyverseTheme {
        CharacterCard(
            character = MortyverseCharacter(
                id = "1",
                name = "Rick Sanchez",
                species = "Human",
                type = "",
                status = "Alive",
                image = "",
            ),
            imageLoader = previewImageLoader,
            onClick = {},
        )
    }
}
