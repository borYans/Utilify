package com.boryans.utilify.date

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormat {

  fun Calendar.addToCalendar(type: Int, amount: Int): Calendar {
    add(type, amount)
    return this
  }

  fun formatDate(dateFormat: DateFormat, date: Date): String? {
    try {
      return SimpleDateFormat(dateFormat.name, Locale.getDefault()).format(date)
    } catch (ex: Exception) {
      // no-op
    }
    return null
  }

  fun getCalendarWithValues(
    hourOfDay: Int,
    minute: Int,
    second: Int,
    year: Int,
    month: Int,
    dayOfMonth: Int,
    milliseconds: Int = 0,
    calendar: Calendar = Calendar.getInstance()
  ): Calendar {
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
    calendar.set(Calendar.MINUTE, minute)
    calendar.set(Calendar.SECOND, second)
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    calendar.set(Calendar.MILLISECOND, milliseconds)
    return calendar
  }

  fun Date.formatDate(dateFormat: DateFormat, timeZone: TimeZone): String {
    try {
      val format =
        SimpleDateFormat(dateFormat.name, Locale.getDefault())
      format.timeZone = timeZone
      return format.format(this)
    } catch (ex: Exception) {
      // no-op
    }
    return ""
  }

  fun getTodayCalenderInstance(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar
  }

  @RequiresApi(Build.VERSION_CODES.O)
  fun String.parseDateToLocalDate(
    dateFormat: DateFormat = DateFormat.FORMAT_ISO_8601_DATE,
  ): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.name, Locale.getDefault())
    return LocalDate.parse(this, formatter)
  }

  fun Date.formatToShortDate(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MMM, timeZone)


  fun Date.formatToLongDate(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MMM_YYYY, timeZone)


  fun Date.formatMonth(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MM_YYYY, timeZone)

  fun Date.formatDisplay(context: Context): String =
    android.text.format.DateFormat.getDateFormat(context).format(this)

  fun getGmtTimeZone(): TimeZone {
    return TimeZone.getTimeZone("GMT")
  }

  fun getLocalTimeZone(): TimeZone {
    return TimeZone.getTimeZone("GMT+02:00")
  }

  private fun Date?.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    this?.let {
      calendar.time = it
    }
    return calendar
  }

  fun String.parseDate(
    dateFormat: DateFormat = DateFormat.FORMAT_ISO_8601_DATE,
    timeZone: TimeZone = getGmtTimeZone()
  ): Date? {
    try {
      val format = SimpleDateFormat(dateFormat.name, Locale.getDefault())
      format.timeZone = timeZone
      return format.parse(this)
    } catch (ex: Exception) {
      //no-op
    }
    return null
  }

  fun getSoonerDate(firstDate: Date?, secondDate: Date?): Date? {
    return if (firstDate == null && secondDate == null) {
      null
    } else if (firstDate != null && secondDate == null) {
      firstDate
    } else if (firstDate == null && secondDate != null) {
      secondDate
    } else {
      if (firstDate!! < secondDate!!) firstDate else secondDate
    }
  }

  fun parseStringDate(dateAsString: String?, dateFormat: DateFormat): Date? {
    try {
      return SimpleDateFormat(dateFormat.name, Locale.getDefault()).apply {
        timeZone = getGmtTimeZone()
      }
        .parse(dateAsString.orEmpty())
    } catch (ex: Exception) {
    }
    return null
  }

  private fun String.dateIsToday(): Boolean {
    val dateInMillis = parseStringDate(this, DateFormat.FORMAT_ISO_8601_DATE)?.time
    return dateInMillis?.let { android.text.format.DateUtils.isToday(it) } ?: false
  }

  fun String?.isBeforeToday(): Boolean {
    return this?.parseDate()?.before(Date()) == true && !dateIsToday()
  }


  fun Date.atStartOfDay(): Date = this.toCalendar().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
  }.time


  fun getCurrentYear(): String {
    return Calendar.getInstance().get(Calendar.YEAR).toString()
  }

  /**
   * Date formats.
   */
  enum class DateFormat(
    private val genericFormat: String,
    private val french: String = genericFormat,
    private val german: String = genericFormat,
    private val italian: String = genericFormat,
    private val english: String = genericFormat,
  ) {
    FORMAT_DD_MMM_YYYY("dd. MMM yyyy", "dd MMM yyyy"),
    FORMAT_EE_DD_MMM_YYYY("EE dd. MMM yyyy", "EE dd MMM yyyy"),
    FORMAT_DD_MMM("dd. MMM", "dd MMM"),
    FORMAT_MMMM_YYYY("MMMM yyyy"),
    FORMAT_MMMM("MMMM"),
    FORMAT_DD_MMMM_YYYY("dd MMMM yyyy"),
    FORMAT_ISO_8601_DATE("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    FORMAT_ISO_8601_DATE_MILISECONDS("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"),
    FORMAT_ISO_DATE("yyyy-MM-dd", "dd-MM-yyyy"),
    FORMAT_MM_YY("MM/yy"),
    FORMAT_DD_MM_YYYY("dd.MM.yyyy", "dd MM yyyy"),


  }
}