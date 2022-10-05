package com.boryans.utilify.view

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.boryans.utilify.context.longToast
import com.boryans.utilify.context.toast
import com.google.android.material.snackbar.Snackbar

/**
 * Shows the view.
 */
fun View.show() {
  visibility = View.VISIBLE
}

/**
 * Hides the view.
 */
fun View.hide() {
  visibility = View.GONE
}

/**
 * Makes the view visible based on the flag.
 */
fun View.shouldShow(show: Boolean) {
  visibility = if (show) View.VISIBLE else View.GONE
}

/**
 * Makes the view invisible.
 */
fun View.invisible() {
  visibility = View.INVISIBLE
}

/**
 * Makes the view invisible based on the flag.
 */
fun View.shouldBeInvisible(invisible: Boolean) {
  visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

/**
 * Shows short duration snackBar.
 */
fun View.shortSnackBar(stringRes: Int) {
  Snackbar.make(this, this.context.getString(stringRes), Snackbar.LENGTH_SHORT).show()
}

/**
 * Shows long duration snackBar.
 */
fun View.longSnackBar(stringRes: Int) {
  Snackbar.make(this, this.context.getString(stringRes), Snackbar.LENGTH_LONG).show()
}

/**
 * Shows snackBar with custom duration.
 */
fun View.longSnackBar(stringRes: Int, duration: Int) {
  Snackbar.make(this, this.context.getString(stringRes), duration).show()
}

/**
 * Shows snackBar with indefinite duration.
 */
fun View.indefiniteSnackBar(stringRes: Int) {
  Snackbar.make(this, this.context.getString(stringRes), Snackbar.LENGTH_INDEFINITE).show()
}

/**
 * Shows multi-line snackBar message.
 */
fun View.multiLineSnackBar(stringRes: Int, duration: Int, maxLines: Int) {
  Snackbar.make(this, this.context.getString(stringRes), duration).setTextMaxLines(maxLines).show()
}

/**
 * Shows toast from the fragment
 */
fun Fragment.toast(textResource: Int) {
  requireActivity().toast(textResource, Toast.LENGTH_SHORT)
}

/**
 * Shows long toast from the fragment
 */
fun Fragment.longToast(textResource: Int)  {
  requireActivity().longToast(textResource)
}

