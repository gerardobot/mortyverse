package com.minroud.mortyverse.ui.screens.character.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.R
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.framework.ui.image.AsyncImage
import org.koin.androidx.compose.get

@Composable
fun CharacterBio(
    characterDetail: MortyverseCharacterDetail,
    modifier: Modifier = Modifier,
    AsyncImage: AsyncImage = get()
) =
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            source = characterDetail.mortyverseCharacter.image,
            contentDescription = stringResource(
                R.string.character_card_image_description,
                characterDetail.mortyverseCharacter.name
            ),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = characterDetail.mortyverseCharacter.name,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(fontSize = 46.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                modifier = Modifier.offset(y = (0).dp),
                text = characterDetail.mortyverseCharacter.type.ifEmpty { characterDetail.mortyverseCharacter.species },
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 26.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            val statusText = stringResource(id = R.string.character_detail_status)
            Text(
                text = "$statusText ${characterDetail.mortyverseCharacter.status}",
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                text = stringResource(
                    id = R.string.character_detail_bio,
                    formatArgs = formatArgs(characterDetail)
                ),
                fontWeight = FontWeight.Normal,
                style = TextStyle(
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

@Composable
private fun formatArgs(mortyverseCharacterDetail: MortyverseCharacterDetail) =
    buildList {
        add(stringResource(mortyverseCharacterDetail.gender.toGender().stringRes))
        add(mortyverseCharacterDetail.origin)
        add(mortyverseCharacterDetail.mortyverseCharacter.name)
        add(episodeNumber(mortyverseCharacterDetail.episodes.first()))
        val episodeCount = mortyverseCharacterDetail.episodes.size
        add(stringResource(episodesText(episodeCount)))
        add(mortyverseCharacterDetail.mortyverseCharacter.name)
        add(mortyverseCharacterDetail.location)
    }.toTypedArray()

private fun episodesText(count: Int) = when {
    count == 1 -> R.string.character_detail_bio_episodes_one
    count < 10 -> R.string.character_detail_bio_episodes_several
    else -> R.string.character_detail_bio_episodes_many
}

private fun episodeNumber(string: String) = string.replace("[^\\d ]".toRegex(), "")

enum class Gender(@StringRes val stringRes: Int) {
    Male(R.string.character_detail_bio_male),
    Female(R.string.character_detail_bio_female),
    Other(R.string.character_detail_bio_other)
}

fun String.toGender() = when (this.lowercase()) {
    Gender.Male.name.lowercase() -> Gender.Male
    Gender.Female.name.lowercase() -> Gender.Female
    else -> Gender.Other
}
