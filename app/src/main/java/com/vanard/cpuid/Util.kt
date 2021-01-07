package com.vanard.cpuid

import android.content.Context
import android.widget.Toast

object Util {

    fun Context.createToast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

}
