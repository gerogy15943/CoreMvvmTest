package com.example.coremvvmtest.presentation

interface ProgressCommunication {
    interface Update: Communication.Update<Visibility>
    interface Observe: Communication.Observe<Visibility>
    interface Mutable: Communication.Mutable<Visibility>

    class Base: Communication.UiUpdate<Visibility>(), Mutable
}