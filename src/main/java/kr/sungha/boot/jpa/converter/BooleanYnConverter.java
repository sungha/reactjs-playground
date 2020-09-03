package kr.sungha.boot.jpa.converter;


import javax.persistence.Converter;


@Converter(autoApply = true)
public class BooleanYnConverter extends AbstractBooleanConverter {

  public BooleanYnConverter() {
    super("Y", "N");
  }

}
