package com.boryans.utilify.constants

const val EMAIL_REGEX = "[a-zA-Z0-9+._%\\-]{1,256}" +
  "@" +
  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
  "(" +
  "\\." +
  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
  ")+"

const val CITY_REGEX = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*\$"