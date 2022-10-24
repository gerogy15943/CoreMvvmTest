package com.example.coremvvmtest.presentation

import android.view.View
import androidx.lifecycle.ViewModel

interface Visibility {
    fun apply(view: View)

    abstract class Abstract(private val visibility: Int): Visibility{
        override fun apply(view: View) = view.setVisibility(visibility)
    }

    class Visible: Abstract(View.VISIBLE)
    class Gone: Abstract(View.GONE)
    class InVisible: Abstract(View.INVISIBLE)
}