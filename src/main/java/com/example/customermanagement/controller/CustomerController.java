package com.example.customermanagement.controller;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.HibernateCustomerService;
import com.example.customermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService hibernateCustomerService;

    @GetMapping()
    public String showData(Model model) {
        List<Customer> customers = hibernateCustomerService.fillALl();
        model.addAttribute("customer",customers);
        return "index";

    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customer",new Customer());
        return "create";
    }
    @PostMapping("/save")
    public String saveCustomer(Customer customer) {
        hibernateCustomerService.save(customer);
        return "redirect:/customer";
    }
//    @GetMapping("/{id}/delete")
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        hibernateCustomerService.delete(id);
        System.out.println(id);
        return "redirect:/customer";
    }

}
