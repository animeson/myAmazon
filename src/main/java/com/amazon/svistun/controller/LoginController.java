package com.amazon.svistun.controller;

import com.amazon.svistun.entity.sales.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    @PostMapping("/login")
    public Customer doLogin (Customer customer) {
        return null;
    }

    @PostMapping("/registration")
    public Customer doRegistration(Customer customer) {
        return null;
    }
}
