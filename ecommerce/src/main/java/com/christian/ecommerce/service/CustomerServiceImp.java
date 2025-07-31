package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CustomerDAO;
import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.exceptions.CustomersException;
import com.christian.ecommerce.mapper.CustomerMapper;
import com.christian.ecommerce.model.Customer;
import com.christian.ecommerce.security.ECToken;
import com.christian.ecommerce.security.ECTokenUtil;
import com.christian.ecommerce.validators.CustomerDTOValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements ICustomerService{

    private CustomerDAO customerDAO;

    private CustomerMapper mapper;

    private BCryptPasswordEncoder encoderPassword = new BCryptPasswordEncoder();

    public CustomerServiceImp(CustomerDAO customerDAO, CustomerMapper mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }


    @Override
    public CustomerDTO createNewCustomer(CustomerDTO newCustomer) {
        CustomerDTOValidator.validator(newCustomer);

        String passwordEncoded = encoderPassword.encode(newCustomer.getPassword());

        Customer customer = mapper.customerDtotoCustomer(newCustomer);
        customer.setPassword(passwordEncoded);

        CustomerDTO savedCustomerDTO = mapper.customerToCustomerDto(customerDAO.save(customer));

        if (savedCustomerDTO != null){
            return savedCustomerDTO;
        }
        throw new CustomersException("[ERROR]: Could not create customer");
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer byEmail = customerDAO.findByEmail(customerDTO.getEmail());


        Customer customer = mapper.customerDtotoCustomer(customerDTO);
        customer.setId(byEmail.getId());

        if (customerDTO.getPassword() != null || !customerDTO.getPassword().isBlank()){

            String passwordEncoded = encoderPassword.encode(customerDTO.getPassword());
            customer.setPassword(passwordEncoded);
        }

        if (byEmail != null){
            Customer saved = customerDAO.save(customer);
            return mapper.customerToCustomerDto(saved);
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

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
       return mapper.customerToCustomerDto(customerDAO.findByEmail(email));
    }

    @Override
    public ECToken doLogin(String email, String password) {
        Customer customer = customerDAO.findByEmail(email);

        if (customer != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(password, customer.getPassword())){
                return ECTokenUtil.generateToken(customer);
            }
        }

        return null;
    }
}
