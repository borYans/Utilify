package com.boryans.utilify.context

import android.content.Context
import android.widget.Toast

/**
 * Shows short duration Toast message
 */
fun Context.shortToast(stringRes: Int) {
  Toast.makeText(this, getString(stringRes), Toast.LENGTH_SHORT).show()
}

/**
 * Shows long duration Toast message
 */
fun Context.longToast(stringRes: Int) {
  Toast.makeText(this, getString(stringRes), Toast.LENGTH_LONG).show()
}

/**
 * Shows toast message with custom length
 */
fun Context.toast(stringRes: Int, duration: Int) {
  Toast.makeText(this, getString(stringRes), duration).show()
}
