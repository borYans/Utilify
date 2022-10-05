package com.boryans.utilify.context.services

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

/**
 * Utility method to open a date picker dialog with the passed values.
 *
 * @param calendar object used to set the correct date in the picker
 * @param minDate minimum selectable date in the picker
 * @param maxDate maximum selectable date in the picker or null if there is no limit
 * @param listener listener for the selection of a date
 */
fun Context.showDatePicker(
  calendar: Calendar, minDate: Long?, maxDate: Long?,
  listener: DatePickerDialog.OnDateSetListener
) {
  val pickerDialog = DatePickerDialog(
    this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH)
  )
  pickerDialog.datePicker.apply {
    minDate?.let {
      this.minDate = minDate
    }
    maxDate?.let {
      this.maxDate = maxDate
    }
  }
  pickerDialog.show()
}