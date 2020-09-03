package kr.sungha.boot.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class PrefixNamingStrategy extends SpringPhysicalNamingStrategy {

  private static final String PREFIX = "SH_";

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
    Identifier prefixedTableName = new Identifier(PREFIX + name.getText().toLowerCase(), name.isQuoted());
    return super.toPhysicalTableName(prefixedTableName, context);
  }

}
