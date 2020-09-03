package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter
public class Boolean01Converter extends AbstractBooleanConverter {

  public Boolean01Converter() {
    super("1", "0");
  }

}
