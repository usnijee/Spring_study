package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * @GetMapping : HTTP GET 요청을 특정 url에 매핑, 요청경로와 helloMvc메서드를 연결하여 요청에 대한 처리를 메서드에서 진행
     * @RequestParam("name") : HTTP 요청의 쿼리파라미터에서 값을 추출하여 메서드 매개변수에 바인딩 http://localhost:8080/hello-mvc?name=John -> 이런
     * 요청에 대해 name이 쿼리파라미터이고 이를 메서드의 매개변수에 전달
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
        /**
         * @ResponseBody에의해 viewResolver를 거치지 않고 바로 HttpMessageConverter에 의해
         * String 그대로 응답됨
         */
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        /**
         *  반환값이 `객체`인 경우 spring은 @Responsebody에의해 HttpMessageConverter에 의해
         *  json body로 요청에 대한 응답을 보냄
         */
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

