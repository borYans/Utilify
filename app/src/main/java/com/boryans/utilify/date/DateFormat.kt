package com.boryans.utilify.date

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormat {

  /**
   * Add an amount of a type (days, months, years) to the calendar object.
   * @param type the type to be added
   *   [Calendar.DATE]  for adding days.
   *   [Calendar.MONTH] for adding months.
   *   [Calendar.YEAR]  for adding years.
   * @param amount [Int]
   */
  fun Calendar.addToCalendar(type: Int, amount: Int): Calendar {
    add(type, amount)
    return this
  }

  /**
   * Formats date by the given [DateFormat],
   *
   * @param dateFormat The date format.
   * @param date The date.
   *
   * @return [String] The formatted date.
   */
  fun formatDate(dateFormat: DateFormat, date: Date): String? {
    try {
      return SimpleDateFormat(dateFormat.name, Locale.getDefault()).format(date)
    } catch (ex: Exception) {
      // no-op
    }
    return null
  }

  /**
   * Return a calendar object with the passed values for the date and the time.
   */
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

  /**
   * Formats the [Date].
   *
   * @param dateFormat [DateFormat] used for formatting.
   * @param timeZone [TimeZone] the desired time zone.
   * @return the parsed date.
   */
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

  /**
   * Return a calendar object with the current date and 00:00:00 for the time.
   */
  fun getTodayCalenderInstance(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar
  }

  /**
   * Parses the [String] into a [LocalDate].
   *
   * @param dateFormat [DateFormat].
   * @return the parsed local date.
   */
  @RequiresApi(Build.VERSION_CODES.O)
  fun String.parseDateToLocalDate(
    dateFormat: DateFormat = DateFormat.FORMAT_ISO_8601_DATE,
  ): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(dateFormat.name, Locale.getDefault())
    return LocalDate.parse(this, formatter)
  }

  /**
   * Returns the [Date] in a format ready to be displayed.
   */
  fun Date.formatForDisplayShort(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MMM, timeZone)

  /**
   * Returns the [Date] in a format ready to be displayed.
   */
  fun Date.formatForDisplayLong(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MMM_YYYY, timeZone)

  /**
   * Returns the [Date] in a format ready to be displayed as 2 digits month length.
   */
  fun Date.formatForDisplayShortMonth(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MM_YYYY, timeZone)

  /**
   * Returns the [Date] in a format used by accessibility readers.
   */
  fun Date.formatForAccessibility(timeZone: TimeZone = getLocalTimeZone()) =
    this.formatDate(DateFormat.FORMAT_DD_MMMM_YYYY, timeZone)

  /**
   * Returns the [Date] in a format needed for backend requests.
   */
  fun Date.formatForBackendRequest(timeZone: TimeZone = getGmtTimeZone()) =
    this.formatDate(DateFormat.FORMAT_ISO_8601_DATE, timeZone)

  /**
   * Returns the [Date] in a format with truncated day.
   */
  fun Date.formatForDisplayLongWithLetters(timeZone: TimeZone = TimeZone.getDefault()) =
    this.formatDate(DateFormat.FORMAT_EE_DD_MMM_YYYY, timeZone)

  /**
   * Formats given [Date] for display based on device's locale.
   *
   * @param context [Context] view context (not application context) from which the date format is retrieved.
   */
  fun Date.formatForLocalDisplay(context: Context): String =
    android.text.format.DateFormat.getDateFormat(context).format(this)

  /**
   * Returns the GMT [TimeZone].
   */
  fun getGmtTimeZone(): TimeZone {
    return TimeZone.getTimeZone("GMT")
  }

  /**
   * Returns the local [TimeZone].
   */
  fun getLocalTimeZone(): TimeZone {
    return TimeZone.getTimeZone("GMT+02:00")
  }

  /**
   * Create a calendar object from the calling date object.
   */
  private fun Date?.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    this?.let {
      calendar.time = it
    }
    return calendar
  }

  /**
   * Parses the [String] into a [Date]. Returns null if the parsing fails.
   *
   * @param dateFormat [DateFormat].
   * @param timeZone [TimeZone].
   * @return the parsed date.
   */
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

  /**
   * Compares two dates and returns the one that is sooner.
   * @param firstDate [Date] the first date to be compared.
   * @param secondDate [Date] the second date to be compared.
   *
   * @return [Date] the soonest date or [null] if both are null.
   */
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

  /**
   * Parse String into Date by the given [DateFormat].
   *
   * @param dateFormat The date format.
   * @return [Date] The parsed date.
   */
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

  /**
   * Check if the given date is the same as today.
   */
  private fun String.dateIsToday(): Boolean {
    val dateInMillis = parseStringDate(this, DateFormat.FORMAT_ISO_8601_DATE)?.time
    return dateInMillis?.let { android.text.format.DateUtils.isToday(it) } ?: false
  }

  /**
   * Check if the given date string is before today.
   */
  fun String?.isBeforeToday(): Boolean {
    return this?.parseDate()?.before(Date()) == true && !dateIsToday()
  }


  /**
   * Maps Date to the start of the day i.e midnight 00h:00m:00s
   */
  fun Date.atStartOfDay(): Date = this.toCalendar().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
  }.time

  /**
   * Return a current year.
   * @return [String]
   */
  fun getCurrentYear(): String {
    return Calendar.getInstance().get(Calendar.YEAR).toString()
  }

  /**
   * Date formats.
   */
  enum class DateFormat(
    private val genericFormat: String,
    private val frenchAlternative: String = genericFormat,
    private val germanAlternative: String = genericFormat,
    private val italianAlternative: String = genericFormat,
    private val englishAlternative: String = genericFormat,
  ) {
    FORMAT_ISO_8601_DATE("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    FORMAT_ISO_8601_DATE_MILISECONDS("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"),
    FORMAT_ISO_DATE("yyyy-MM-dd", "dd-MM-yyyy"),
    FORMAT_MM_YY("MM/yy"),
    FORMAT_DD_MM_YYYY("dd.MM.yyyy", "dd MM yyyy"),
    FORMAT_DD_MMM_YYYY("dd. MMM yyyy", "dd MMM yyyy"),
    FORMAT_EE_DD_MMM_YYYY("EE dd. MMM yyyy", "EE dd MMM yyyy"),
    FORMAT_DD_MMM("dd. MMM", "dd MMM"),
    FORMAT_MMMM_YYYY("MMMM yyyy"),
    FORMAT_MMMM("MMMM"),
    FORMAT_DD_MMMM_YYYY("dd MMMM yyyy");

  }
}