package com.example.coremvvmtest.presentation

import androidx.annotation.StringRes
import com.example.coremvvmtest.R
import com.example.coremvvmtest.core.HandleError
import com.example.coremvvmtest.core.ManageResources
import com.example.coremvvmtest.domain.NoInternetException
import com.example.coremvvmtest.domain.ServiceUnavailableException

abstract class HandleUiErrorAbstract(
    private val manageResources: ManageResources,
    private val globalErrorCommunication: GlobalErrorCommunication.Update,
    private val handleError: HandleError
): HandleError {
    @StringRes
    protected open val noConnectionExceptionMessage: Int = R.string.no_connection_message

    @StringRes
    protected open val serviceUnavailableExceptionMessage: Int = R.string.no_service_message

    override fun handleError(error: Exception): Exception {
        val messageId = when(error){
            is NoInternetException -> noConnectionExceptionMessage
            is ServiceUnavailableException -> serviceUnavailableExceptionMessage
            else -> 0
        }
        return if(messageId == 0)
                    handleError.handleError(error)
               else {
                    globalErrorCommunication.map(manageResources.string(messageId))
                    error
        }
    }
}

class HandleErrorGenericUiError(
    private val managedResources: ManageResources,
    private val globalErrorCommunication: GlobalErrorCommunication.Update
    ): HandleError {
    override fun handleError(error: Exception): Exception {
        globalErrorCommunication.map(managedResources.string(R.string.unexpected_error_message))
        return error
    }
}

class HandleUiError(
    manageResources: ManageResources,
    globalErrorCommunication: GlobalErrorCommunication.Update
) :HandleUiErrorAbstract(manageResources, globalErrorCommunication)