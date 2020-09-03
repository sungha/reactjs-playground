package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter
public class YmdhmsConverter extends AbstractDateConverter {

  public YmdhmsConverter() {
    super("yyyyMMddHHmmss");
  }

}
