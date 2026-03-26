package com.minroud.mortyverse.feature.characters.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.feature.characters.ui.R
import com.minroud.mortyverse.ui.theme.MortyverseTheme

@Composable
internal fun InvalidCharacterIdError() =
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_open_eyes),
            contentDescription = stringResource(id = R.string.error_invalid_character_id_image_description),
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier.padding(horizontal = 28.dp),
            text = stringResource(id = R.string.error_invalid_character_id_dialog),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp,
            textAlign = TextAlign.Start,
        )
    }

@Preview(showBackground = true)
@Composable
private fun InvalidCharacterIdErrorPreview() {
    MortyverseTheme {
        InvalidCharacterIdError()
    }
}
