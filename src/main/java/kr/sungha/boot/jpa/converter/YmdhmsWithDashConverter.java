package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter
public class YmdhmsWithDashConverter extends AbstractDateConverter {

  public YmdhmsWithDashConverter() {
    super("yyyy-MM-dd HH:mm:ss");
  }

}
