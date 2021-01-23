package com.teestore.backend.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeAPI {

    @GetMapping("/")
    public String statusCheck() {
        return "TeeStore Backend is up and running";
    }
}
