package org.aop.aopconcepts.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/secondhome")
    public String secondhome() {
        String result = String.valueOf(10/0);
        return result;
    }
    
}
