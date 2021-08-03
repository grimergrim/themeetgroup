package com.themeetgroup.iavorskyitestapp.utils

import android.content.Context
import android.widget.Toast

object ToastsHelper {

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}