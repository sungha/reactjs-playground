package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter
public class YmdConverter extends AbstractDateConverter {

  public YmdConverter() {
    super("yyyyMMdd");
  }

}
