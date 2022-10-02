package com.boryans.utilify.date

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun Calendar.addToCalendar(type: Int, amount: Int): Calendar {
  add(type, amount)
  return this
}

fun Date.formatDate(dateFormat: DateFormat): String? {
  kotlin.runCatching {
    return SimpleDateFormat(dateFormat.name, Locale.getDefault()).format(this)
  }
  return null
}

fun getCalendar(
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
  kotlin.runCatching {
    val format =
      SimpleDateFormat(dateFormat.name, Locale.getDefault())
    format.timeZone = timeZone
    return format.format(this)
  }
  return ""
}

fun Pair<Date?,Date?>.getClosestDate(): Date? {
  when {
    first == null && second == null -> return null
    first != null && second == null -> first
    first == null && second != null -> second
    else -> if(first!! < second) first else second
  }
  return null
}

fun getTodayCalendar(): Calendar {
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

fun Date.toShortDate(timeZone: TimeZone) =
  this.formatDate(DateFormat.FORMAT_DD_MMM, timeZone)


fun Date.toLongDate(timeZone: TimeZone) =
  this.formatDate(DateFormat.FORMAT_DD_MMM_YYYY, timeZone)


fun Date.formatMonth(timeZone: TimeZone) =
  this.formatDate(DateFormat.FORMAT_DD_MM_YYYY, timeZone)

fun Date.formatDisplay(context: Context): String =
  android.text.format.DateFormat.getDateFormat(context).format(this)

private fun Date?.toCalendar(): Calendar {
  val calendar = Calendar.getInstance()
  this?.let {
    calendar.time = it
  }
  return calendar
}

fun String.parseDate(
  dateFormat: DateFormat = DateFormat.FORMAT_ISO_8601_DATE,
  timeZone: TimeZone
): Date? {
  kotlin.runCatching {
    val format = SimpleDateFormat(dateFormat.name, Locale.getDefault()).apply {
      this.timeZone = timeZone
    }
    return format.parse(this)
  }
  return null
}

fun String?.toDate(dateFormat: DateFormat): Date? {
  runCatching {
    return SimpleDateFormat(dateFormat.name, Locale.getDefault())
      .parse(this.orEmpty())
  }
  return null
}

private fun String.dateIsToday(): Boolean {
  val dateInMillis = this.toDate(DateFormat.FORMAT_ISO_8601_DATE)?.time
  return dateInMillis?.let { android.text.format.DateUtils.isToday(it) } ?: false
}

fun getCurrentYear(): String {
  return Calendar.getInstance().get(Calendar.YEAR).toString()
}

/**
 * Date formats.
 */
enum class DateFormat(
  private val genericFormat: String,
  private val fr: String = genericFormat,
  private val ge: String = genericFormat,
  private val it: String = genericFormat,
  private val en: String = genericFormat,
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
