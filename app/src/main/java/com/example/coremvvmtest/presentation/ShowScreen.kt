package com.example.coremvvmtest.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ShowScreen {
    fun show(
        @IdRes containerId: Int,
        fragmentManager: FragmentManager)
}