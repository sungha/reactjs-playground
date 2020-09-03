package kr.sungha.boot.jpa.converter;


import javax.persistence.AttributeConverter;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class AbstractBooleanConverter implements AttributeConverter<Boolean, String> {

  private final String yesValue;
  private final String noValue;

  @Override
  public String convertToDatabaseColumn(Boolean value) {
    if (value == null) {
      return null;
    }
    return value ? yesValue : noValue;
  }

  @Override
  public Boolean convertToEntityAttribute(String value) {
    return yesValue.equals(value);
  }

}
