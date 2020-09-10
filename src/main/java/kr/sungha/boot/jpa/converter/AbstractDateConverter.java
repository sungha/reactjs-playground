package kr.sungha.boot.jpa.converter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;

public abstract class AbstractDateConverter implements AttributeConverter<LocalDateTime, String> {

  private final DateTimeFormatter formatter;

  public AbstractDateConverter(String format) {
    this.formatter = DateTimeFormatter.ofPattern(format);
  }

  @Override
  public String convertToDatabaseColumn(LocalDateTime date) {
    if (date == null) {
      return null;
    }
    return formatter.format(date);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(String date) {
    if (date == null) {
      return null;
    }

    return LocalDateTime.parse(date, formatter);
  }

}
