package com.minroud.mortyverse.domain.usecases

import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.domain.result.error.DomainError
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, Type>(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    protected abstract suspend fun call(params: Params): DomainResult<Type>

    suspend operator fun invoke(params: Params) = withContext(coroutineDispatcher) {
        try {
            call(params)
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        } catch (throwable: Throwable) {
            DomainResult.Error(DomainError.Unknown())
        }
    }
}
