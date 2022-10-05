package com.boryans.utilify.context.services

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * Get vibrator from the system service.
 */
inline val Context.vibrator: android.os.Vibrator
  get() = getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator

/**
 * Get layout inflater from system service.
 */
inline val Context.layoutInflater: android.view.LayoutInflater
  get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater

/**
 * Get shared preferences from the system service.
 */
inline val Fragment.defaultSharedPreferences: SharedPreferences
  get() = PreferenceManager.getDefaultSharedPreferences(activity)

/**
 * Get input manager from the system service.
 */
inline val Context.inputManager: InputMethodManager
get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
