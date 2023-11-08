package com.perfumepedia.PerfumePedia.MockController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MockPageController {

    @GetMapping("/")
    public String homeView(){
        return "home";
    }

    @GetMapping("/notes")
    public String notesView(){
        return "notes";
    }

    @GetMapping("/brands")
    public String brandsView(){
        return "brands";
    }
}
