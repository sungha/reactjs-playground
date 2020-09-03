package kr.sungha.boot.jpa.converter;


import javax.persistence.AttributeConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public abstract class AbstractDateConverter implements AttributeConverter<DateTime, String> {

  private final DateTimeFormatter formatter;

  public AbstractDateConverter(String format) {
    this.formatter = DateTimeFormat.forPattern(format);
  }

  @Override
  public String convertToDatabaseColumn(DateTime date) {
    if (date == null) {
      return null;
    }
    return date.toString(formatter);
  }

  @Override
  public DateTime convertToEntityAttribute(String date) {
    if (date == null) {
      return null;
    }

    return DateTime.parse(date, formatter);
  }

}
