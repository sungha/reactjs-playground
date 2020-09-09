package kr.sungha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

  @GetMapping(path = "/{path:^(?!(management|api|rest))}/**")
  public String forward(String path) {
    return "forward:/";
  }

}
