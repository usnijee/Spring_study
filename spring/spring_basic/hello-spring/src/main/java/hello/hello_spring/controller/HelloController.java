package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "jinsu!");
        return "hello"; // 컨트롤러에서 리턴 값으로 문자를 반환 -> viewResolver가 화면을 찾아 처리
        // 화면을 찾아 처리한다는 것
        // - 스프링 부트 템플릿 엔진 기본 viewName 매핑
        // - resources:templates/ +{ViewName}+ .html
    }
}
