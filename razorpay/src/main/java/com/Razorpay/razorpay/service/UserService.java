package com.Razorpay.razorpay.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class UserService {

    @Value("${razorpay.key.id}")
    private String id;

    @Value("${razorpay.key.secret}")
    private String secret;

    public String payment(int amount) throws RazorpayException {

        RazorpayClient client = new RazorpayClient(id, secret);

        JSONObject object = new JSONObject();

        object.put("amount", amount * 100);
        object.put("currency", "INR");
        object.put("receipt", "receipt_123");

        Order order = client.orders.create(object);

        System.out.println(order);

        return order.toString();

    }

}