package com.example.coremvvmtest.domain

import com.example.coremvvmtest.core.HandleError
import java.net.UnknownHostException

class HandleDomainError : HandleError {
    override fun handleError(error: Exception): Exception =
        if(error is UnknownHostException)
            NoInternetException()
        else
            ServiceUnavailableException()
}