package com.christian.ecommerce.controller;

import com.christian.ecommerce.model.Customer;
import com.christian.ecommerce.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAll(){
        log.info("INFO: getAll() Requested");

        List<Customer> customers = customerService.findAll();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        log.info("INFO: getCustomerById() Requested by id '{}'", id);

        Customer customerById = customerService.getCustomerById(id);

        if (customerById != null) {
            return ResponseEntity.ok(customerById);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("customers")
    public ResponseEntity<Customer> insertNewCustomer(@RequestBody @Valid Customer newCostumer){
        log.info("INFO: insertNewCustomer() Requested by '{}'", newCostumer.getName());

        try {
            Customer customer = customerService.createNewCustomer(newCostumer);
            if (customer != null){
                return ResponseEntity.status(201).body(customer);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("customers/search")
    public ResponseEntity<Customer> searchByPhone(@RequestParam String phoneNumber){
        log.info("INFO: searchByPhone() Requested by phoneNumber: '{}'", phoneNumber);

        try {
            Customer customerByPhoneNumber = customerService.getCustomerByPhoneNumber(phoneNumber);
            if (customerByPhoneNumber != null) {
                return ResponseEntity.ok(customerByPhoneNumber);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer, @PathVariable Integer id){
        log.info("INFO: updateCustomer() Requested by id: '{}'", id);

        try{
            Customer customerById = customerService.getCustomerById(id);
            if (customerById != null){
                customer.setId(id);
                return ResponseEntity.ok(customerService.updateCustomer(customer));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }
}
