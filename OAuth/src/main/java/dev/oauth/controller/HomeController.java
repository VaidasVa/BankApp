package dev.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello Home";
    }

    @GetMapping("/user")
    public String secured() {
        return "Hello user";
    }
}
