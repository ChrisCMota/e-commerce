package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CustomerDAO;
import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.exceptions.CustomersException;
import com.christian.ecommerce.mapper.CustomerMapper;
import com.christian.ecommerce.model.Customer;
import com.christian.ecommerce.validators.CustomerDTOValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements ICustomerService{

    private CustomerDAO customerDAO;

    private CustomerMapper mapper;

    public CustomerServiceImp(CustomerDAO customerDAO, CustomerMapper mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }


    @Override
    public CustomerDTO createNewCustomer(CustomerDTO newCustomer) {
        CustomerDTOValidator.validator(newCustomer);
        Customer customer = mapper.customerDtotoCustomer(newCustomer);

        CustomerDTO savedCustomerDTO = mapper.customerToCustomerDto(customerDAO.save(customer));

        if (savedCustomerDTO != null){
            return savedCustomerDTO;
        }
        throw new CustomersException("[ERROR]: Could not create customer");
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

        Customer customer = mapper.customerDtotoCustomer(customerDTO);

        CustomerDTO saved = mapper.customerToCustomerDto(customerDAO.save(customer));

        if (saved != null && customerDTO != null){
            return saved;
        }

        throw new CustomersException("[ERROR]: Could not update customer");
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {

        Customer customer = customerDAO.findById(id).orElse(null);

        if (customer != null){
            return mapper.customerToCustomerDto(customer);
        }

        throw new CustomersException("[ERROR]: Could not retrieve customer by id");
    }

    @Override
    public CustomerDTO getCustomerByPhoneNumber(String phoneNumber) {
        Customer customerByPhoneNumber = customerDAO.findByPhoneNumber(phoneNumber);

        if (customerByPhoneNumber != null){
            return mapper.customerToCustomerDto(customerByPhoneNumber);
        }

        throw new CustomersException("[ERROR]: Could not retrieve customer by phone number");
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = (List<Customer>) customerDAO.findAll();

        if (customers != null){
            return mapper.listCustomerToCustomerDto(customers);
        }

        throw new CustomersException("[ERROR]: Could not retrieve customers");
    }
}
