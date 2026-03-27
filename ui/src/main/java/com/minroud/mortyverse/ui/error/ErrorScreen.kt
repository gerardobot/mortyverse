package com.minroud.mortyverse.ui.error

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.ui.R
import com.minroud.mortyverse.ui.topbar.UpdateTopBarTitle

@Composable
fun ErrorMessage(
    error: DomainError,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        when (error) {
            is DomainError.Feature -> {
                val renderer = LocalFeatureErrorRenderers.current
                    .firstOrNull { it.canRender(error.featureError) }
                val title = renderer?.title(error.featureError)
                    ?: stringResource(id = R.string.error_unknown_title)
                UpdateTopBarTitle(title = title)

                if (renderer != null) renderer.Render(error.featureError) else UnknownError()
            }

            is DomainError.Io,
            is DomainError.Network,
            -> {
                val title = stringResource(id = R.string.error_network_title)
                UpdateTopBarTitle(title = title)
                NetworkError()
            }

            is DomainError.Server,
            is DomainError.Unknown,
            -> {
                val title = stringResource(id = R.string.error_unknown_title)
                UpdateTopBarTitle(title = title)
                UnknownError()
            }
        }
    }
}
