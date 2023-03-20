package com.minroud.mortyverse.ui.screens.character.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.R
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.framework.ui.image.AsyncImage
import org.koin.androidx.compose.get

@Composable
fun CharacterCard(
    character: MortyverseCharacter,
    AsyncImage: AsyncImage = get(),
    onClick: (String) -> Unit
) = Card(
    modifier = Modifier
        .padding(bottom = 4.dp, top = 4.dp, start = 8.dp, end = 8.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable(onClick = { onClick(character.id) }),
    shape = RectangleShape,
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Surface(
            modifier = Modifier.size(100.dp)
        ) {
            AsyncImage(
                source = character.image,
                contentDescription = stringResource(
                    R.string.character_card_image_description,
                    character.name
                ),
                modifier = Modifier,
                contentScale = null
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = character.type.ifEmpty { character.species },
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Status: ${character.status}",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
