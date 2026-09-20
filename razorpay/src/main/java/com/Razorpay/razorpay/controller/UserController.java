package com.Razorpay.razorpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Razorpay.razorpay.Entity.User;
import com.Razorpay.razorpay.service.UserService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Object pay(@RequestBody User user) throws RazorpayException {

        System.out.println("Controller Called");
        System.out.println("Amount : " + user.getAmount());

        return userService.payment(user.getAmount());

    }

}