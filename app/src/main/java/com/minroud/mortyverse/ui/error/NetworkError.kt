package com.minroud.mortyverse.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.R
import com.minroud.mortyverse.ui.theme.MortyShirt

@Composable
fun NetworkError() = Column(
    Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Image(
        modifier = Modifier.size(320.dp),
        painter = painterResource(id = R.drawable.img_rick_morty_portal),
        contentDescription = stringResource(id = R.string.error_network_image_description)
    )

    Spacer(modifier = Modifier.size(16.dp))

    Text(
        modifier = Modifier.padding(horizontal = 24.dp),
        text = stringResource(id = R.string.error_network_dialog),
        color = MortyShirt,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        textAlign = TextAlign.Start
    )
}
