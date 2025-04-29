package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.CustomerDTO;
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
    public ResponseEntity<List<CustomerDTO>> getAll(){
        log.info("INFO: getAll() Requested");

        List<CustomerDTO> customers = customerService.findAll();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id){
        log.info("INFO: getCustomerById() Requested by id '{}'", id);

        CustomerDTO customerById = customerService.getCustomerById(id);

        if (customerById != null) {
            return ResponseEntity.ok(customerById);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("customers")
    public ResponseEntity<CustomerDTO> insertNewCustomer(@RequestBody @Valid CustomerDTO newCostumer){
        log.info("INFO: insertNewCustomer() Requested by '{}'", newCostumer.getName());

            CustomerDTO customer = customerService.createNewCustomer(newCostumer);

            return ResponseEntity.status(201).body(customer);
    }

    @GetMapping("customers/search")
    public ResponseEntity<CustomerDTO> searchByPhone(@RequestParam String phoneNumber){
        log.info("INFO: searchByPhone() Requested by phoneNumber: '{}'", phoneNumber);

            CustomerDTO customerByPhoneNumber = customerService.getCustomerByPhoneNumber(phoneNumber);

            return ResponseEntity.ok(customerByPhoneNumber);
    }

    @PutMapping("customers")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody @Valid CustomerDTO customer){
        log.info("INFO: updateCustomer() Requested by: '{}'", customer.getEmail());

            CustomerDTO customerByEmail = customerService.getCustomerByEmail(customer.getEmail());

            return ResponseEntity.ok(customerService.updateCustomer(customer));
    }
}
