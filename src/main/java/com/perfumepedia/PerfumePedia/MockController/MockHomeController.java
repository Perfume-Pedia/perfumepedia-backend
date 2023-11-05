package com.perfumepedia.PerfumePedia.MockController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MockHomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
