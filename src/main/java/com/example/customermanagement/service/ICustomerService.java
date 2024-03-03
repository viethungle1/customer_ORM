package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> fillALl();
    public Customer findById(int id);
    public void save(Customer customer);
    public void delete(int id);
}
