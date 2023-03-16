package com.minroud.mortyverse.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun UnknownError() = Column(
    Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
    verticalArrangement = Arrangement.Center
) {
    Image(
        modifier = Modifier.fillMaxWidth().offset(y = (-24).dp),
        painter = painterResource(id = R.drawable.img_picklerick_and_lemorty),
        contentDescription = stringResource(id = R.string.error_unknown_image_description)
    )

    Spacer(modifier = Modifier.size(16.dp))

    Text(
        modifier = Modifier.padding(horizontal = 28.dp),
        text = stringResource(id = R.string.error_unknown_dialog),
        color = MortyShirt,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        textAlign = TextAlign.Start
    )
}
