package com.boryans.utilify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boryans.utilify.date.DateFormat
import com.boryans.utilify.date.DateFormat.parseDate

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

  }
}