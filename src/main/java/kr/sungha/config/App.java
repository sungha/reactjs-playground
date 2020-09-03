package kr.sungha.config;


import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
@Getter
@ToString
public class App {

}
