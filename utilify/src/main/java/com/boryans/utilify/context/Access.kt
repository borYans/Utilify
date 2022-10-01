package com.boryans.utilify.context

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.share(text: String, subject: String = "", title: String? = null): Boolean {
  return try {
    val intent = Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      putExtra(Intent.EXTRA_SUBJECT, subject)
      putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(intent, title))
    true
  } catch (e: ActivityNotFoundException) {
    e.printStackTrace()
    false
  }
}

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
  return try {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
    if (newTask) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
    true
  } catch (e: ActivityNotFoundException) {
    e.printStackTrace()
    false
  }
}

fun Context.sendSMS(number: String, text: String = ""): Boolean {
  return try {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number")).apply {
      putExtra("sms_body", text)
    }
    startActivity(intent)
    true
  } catch (e: Exception) {
    e.printStackTrace()
    false
  }
}