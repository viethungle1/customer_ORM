package com.example.customermanagementjpa.controller;
import com.example.customermanagementjpa.model.Customer;
import com.example.customermanagementjpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String index (Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customer",customerList);
        return "index";
    }



}
