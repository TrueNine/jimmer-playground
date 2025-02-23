package org.example.entities

import org.babyfish.jimmer.jackson.Converter

class StringToStringJsonConverter : Converter<String, String> {
  override fun output(value: String?): String {
    return value ?: ""
  }

  override fun input(jsonValue: String?): String {
    return jsonValue ?: ""
  }
}
