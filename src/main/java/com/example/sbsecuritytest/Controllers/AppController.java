package com.example.sbsecuritytest.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AppController {
    @GetMapping("/")    //No authorization required
    public String welcome() {
        System.out.println("Application: Home page requested");
        return "index";
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/users")   //need to be authorized
    public String userPage() {
        System.out.println("Application: User page requested");
        return "userpage";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")   //need to be authorized
    @ResponseBody
    public String adminPage() {
        System.out.println("Application: User page requested");
        return "<h2>hello admin<h2> ";
    }


}
