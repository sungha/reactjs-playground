package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter(autoApply = true)
public class YmdhmssssConverter extends AbstractDateConverter {

  public YmdhmssssConverter() {
    super("yyyyMMddHHmmssSSS");
  }

}
