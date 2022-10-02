# Utilify

Utilify is helper library for faster and better productivity during the Android app developement. 

The goal is to be fast and write readable code as much as possible.

<h2>How to use</h2>
Add the following dependency to your app build.gradle file:

```groovy
dependencies {

  implementation 'com.github.borYans:Utilify:1.1'
  
}
```

Add it in your root build.gradle at the end of repositories:

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```

### It consists of several parts:
- validation (amount, email, age, city, password, masking credit card numbers)
- view dependent extensions (hiding and showing views, showing toasts and snackbars)
- date utility for easier handling of the date formats

### Password validation
It's flexible to set your own paremeters which will set validation to be loose or more strict.

```kotlin
val pass = "boryansUtilify123"

val isPassing = pass.isValidPassword(
   length = 7, // set length of the password
   shouldIncludeMixedCase = false, //default is set to true
   shouldIncludeSpecialChar = true
 )
```

### Amount validation
It's flexible to set your own minimum and maximum values for the validation.

```kotlin
val amount = "1000"

val isValid = amount.isValidAmount(minimumAmount = 500, maximmumAmount = 1001) //set your own range 

//or even
val isValid = amount.isValidAmount(maximmumAmount = 1001) // set only max amount, minimum is default null.
```

### Date parsing
Available dates for parsing:
```kotlin
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
    FORMAT_DD_MMMM_YYYY("dd MMMM yyyy")
```
Format and return a String after formatting
```kotlin

val someDate = Calendar.getInstance().time

val parseDateToString = parseDate(
dateFormat = DateFormat.FORMAT_DD_MMMM_YYYY,
date = someDate
)

```

### License

Copyright 2022 Boris Janevski

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
