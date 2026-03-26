package com.minroud.mortyverse.ui.topbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minroud.mortyverse.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    button: TopBarButton,
    modifier: Modifier = Modifier,
    title: String = "",
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.shadow(elevation = 2.dp),
        navigationIcon = {
            IconButton(onClick = { button.onClick() }) {
                Icon(
                    imageVector = button.icon,
                    contentDescription = stringResource(button.iconDescription),
                )
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
            )
        },
    )
}

sealed class TopBarButton(
    val icon: ImageVector,
    @StringRes val iconDescription: Int,
) {
    data class Menu(
        override val onClick: () -> Unit,
    ) : TopBarButton(Icons.Filled.Menu, R.string.top_bar_icon_description_menu)

    data class Back(
        override val onClick: () -> Unit,
    ) : TopBarButton(Icons.AutoMirrored.Filled.ArrowBack, R.string.top_bar_icon_description_back)

    abstract val onClick: () -> Unit
}
