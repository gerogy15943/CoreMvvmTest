package com.example.coremvvmtest.presentation

interface GlobalErrorCommunication {
    interface Observe : Communication.Observe<String>
    interface Update : Communication.Update<String>
    interface Mutable : Communication.Mutable<String>, Update, Observe

    class Base(): Communication.SinglePostUpdate<String>(), Mutable
}