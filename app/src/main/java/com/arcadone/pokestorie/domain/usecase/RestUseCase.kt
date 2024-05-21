package com.arcadone.pokestorie.domain.usecase

import android.util.Log
import com.arcadone.pokestorie.rest.model.ApiResult

abstract class RestUseCase<Arguments, ReturnType> where ReturnType : Any {

    internal abstract suspend fun execute(params: Arguments): ApiResult<ReturnType>

    suspend fun invoke(params: Arguments): ApiResult<ReturnType> {
        return try {
            execute(params)
        } catch (exception: Exception) {
            Log.w(
                RestUseCase::class.java.simpleName,
                "Unable to launch useCase - got an exception ${exception.message}"
            )
            ApiResult.error(exception)
        }
    }
}
