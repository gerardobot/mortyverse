package com.minroud.mortyverse.feature.characters.ui.screens.list.paging

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import com.minroud.mortyverse.feature.characters.ui.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun HandleAppendRetry(
    isNearEnd: Boolean,
    appendState: LoadState,
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit,
    policy: AppendRetryPolicy = AppendRetryPolicy(),
) {
    val retryState = rememberAppendRetryState(policy.baseRetryDelay)
    val errorMessage = stringResource(R.string.append_retry_error)
    val recoveredMessage = stringResource(R.string.append_retry_recovered)

    LaunchedEffect(isNearEnd, appendState) {
        if (isNearEnd && appendState.isAppendError()) {
            retryAppendWithBackoff(
                retryState = retryState,
                snackbarHostState = snackbarHostState,
                maxRetryDelay = policy.maxRetryDelay,
                message = errorMessage,
                onRetry = onRetry,
            )
        }
    }

    LaunchedEffect(appendState) {
        if (appendState.isAppendRecovered()) {
            handleAppendRecovered(
                retryState = retryState,
                snackbarHostState = snackbarHostState,
                message = recoveredMessage,
            )
        }
    }
}

internal data class AppendRetryPolicy(
    val baseRetryDelay: Duration = appendRetryBaseDelay,
    val maxRetryDelay: Duration = appendRetryMaxDelay,
)

@Composable
private fun rememberAppendRetryState(
    baseRetryDelay: Duration,
): AppendRetryState {
    val lastRetryAtMs = rememberSaveable { mutableLongStateOf(0L) }
    val retryDelayMs = rememberSaveable { mutableLongStateOf(baseRetryDelay.inWholeMilliseconds) }
    val snackbarShownInBurst = rememberSaveable { mutableStateOf(false) }
    return AppendRetryState(
        baseRetryDelay = baseRetryDelay,
        lastRetryAtMs = lastRetryAtMs,
        retryDelayMs = retryDelayMs,
        snackbarShownInBurst = snackbarShownInBurst,
    )
}

private data class AppendRetryState(
    val baseRetryDelay: Duration,
    val lastRetryAtMs: MutableState<Long>,
    val retryDelayMs: MutableState<Long>,
    val snackbarShownInBurst: MutableState<Boolean>,
) {
    val retryDelay = retryDelayMs.value.milliseconds
}

private suspend fun retryAppendWithBackoff(
    retryState: AppendRetryState,
    snackbarHostState: SnackbarHostState,
    maxRetryDelay: Duration,
    message: String,
    onRetry: () -> Unit,
) {
    val now = System.currentTimeMillis()
    val canRetry = now - retryState.lastRetryAtMs.value >= retryState.retryDelayMs.value
    if (!canRetry) return

    if (!retryState.snackbarShownInBurst.value) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        )
        retryState.snackbarShownInBurst.value = true
    }
    onRetry()
    retryState.lastRetryAtMs.value = now
    retryState.retryDelayMs.value =
        (retryState.retryDelay * 2).coerceAtMost(maxRetryDelay).inWholeMilliseconds
}

private suspend fun handleAppendRecovered(
    retryState: AppendRetryState,
    snackbarHostState: SnackbarHostState,
    message: String,
) {
    if (retryState.snackbarShownInBurst.value) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        )
    }
    retryState.retryDelayMs.value = retryState.baseRetryDelay.inWholeMilliseconds
    retryState.snackbarShownInBurst.value = false
}

private fun LoadState.isAppendError(): Boolean = this is LoadState.Error

private fun LoadState.isAppendRecovered(): Boolean = this is LoadState.NotLoading
