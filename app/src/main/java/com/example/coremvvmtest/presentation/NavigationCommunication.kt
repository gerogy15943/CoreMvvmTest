package com.example.coremvvmtest.presentation

interface NavigationCommunication {
    interface Update: Communication.Update<NavigationScreen>
    interface Observe: Communication.Observe<NavigationScreen>
    interface Mutable: Communication.Mutable<NavigationScreen>

    class Base: Communication.SinglePostUpdate<NavigationScreen>(), Mutable
}