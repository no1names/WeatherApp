package com.example.weatherapp.utils

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setErrorMessage(error: String) {
    text = error
    isVisible = error.isNotEmpty()
}