package com.example.coremvvmtest.presentation

import androidx.fragment.app.FragmentManager
import com.example.coremvvmtest.core.Matches

abstract class NavigationScreen(
    private val id: Int,
    private val clasz: Class<out BaseFragment<*>>,
    private val strategy: ShowStrategy
): Matches<NavigationScreen>, ShowScreen {
    override fun matches(data: NavigationScreen): Boolean = data.id == this.id
    override fun toString(): String = "id $id"
    override fun show(containerId: Int, fragmentManager: FragmentManager) {
        when(strategy){
            ShowStrategy.REPLACE -> fragmentManager.beginTransaction()
                .replace(containerId, clasz.newInstance())
                .commit()
            ShowStrategy.ADD -> fragmentManager.beginTransaction()
                .add(containerId, clasz.newInstance())
                .commit()
            ShowStrategy.POPUP -> fragmentManager.popBackStack()
        }
    }
}