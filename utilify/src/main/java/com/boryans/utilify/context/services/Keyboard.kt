package com.boryans.utilify.context.services

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Hides keyboard when called in [Fragment].
 */
fun Fragment.hideKeyboard() {
  view?.let {
    activity?.hideKeyboard(it)
  }
}

/**
 * Hides keyboard for a given view.
 */
fun Context.hideKeyboard(view: View) {
  inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}