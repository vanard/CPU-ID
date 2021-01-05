package com.vanard.cpuid

import android.content.Context
import android.widget.Toast


fun Context.createToast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }