package com.mundial.mundialbets.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping
    public String testText() {
        return "test Oskar";
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return token;
    }
}
