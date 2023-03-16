package com.minroud.mortyverse.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.R

@Composable
fun DrawerContent(
    drawerOptions: List<DrawerOption>
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(top = 64.dp),
            painter = painterResource(id = R.drawable.banner_show_me_what_you_got_heads_light),
            contentDescription = stringResource(id = R.string.banner_show_me_what_you_got_description),
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            drawerOptions.forEach {
                TextButton(
                    onClick = { it.onClick(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(vertical = 4.dp),
                        text = stringResource(id = it.name).uppercase(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 36.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

sealed class DrawerOption(@StringRes val name: Int) {
    data class Home(override inline val onClick: (DrawerOption) -> Unit) :
        DrawerOption(R.string.drawer_option_home)

    data class InvalidCharacterId(override inline val onClick: (DrawerOption) -> Unit) :
        DrawerOption(R.string.drawer_option_invalid_character_id)

    data class NetworkError(override inline val onClick: (DrawerOption) -> Unit) :
        DrawerOption(R.string.drawer_option_network_error)

    data class UnknownError(override inline val onClick: (DrawerOption) -> Unit) :
        DrawerOption(R.string.drawer_option_unknown_error)

    abstract val onClick: (DrawerOption) -> Unit
}
