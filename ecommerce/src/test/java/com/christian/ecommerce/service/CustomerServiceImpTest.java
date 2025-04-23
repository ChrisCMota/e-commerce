package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CustomerDAO;
import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.exceptions.CustomersException;
import com.christian.ecommerce.mapper.CustomerMapper;
import com.christian.ecommerce.mapper.CustomerMapperImpl;
import com.christian.ecommerce.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImpTest {

    @Mock
    private CustomerDAO customerDAO;

    private CustomerMapper mapper = new CustomerMapperImpl();

    @InjectMocks
    private CustomerServiceImp service = new CustomerServiceImp(customerDAO, mapper);


    private List<Customer> list = new ArrayList<>();


    @BeforeEach
    void init(){
        var customer1 = new Customer(1,"Test1", "newcustomer1@email.com", "12345691", "street 1", "D01JH91", "Ireland1", "Dublin1");
        var customer3 = new Customer(2,"Test3", "newcustomer3@email.com", "12345693", "street 3", "D01JH93", "Ireland3", "Dublin3");
        var customer2 = new Customer(3,"Test2", "newcustomer2@email.com", "12345692", "street 2", "D01JH92", "Ireland2", "Dublin2");
        list.addAll(List.of(customer1,customer2,customer3));
    }

    @Test
    void shouldCreateNewCustomer(){

        Customer newCustomer = new Customer(4, "Test4", "newcustomer4@email.com", "12345694", "street 4", "D01JH94", "Ireland4", "Dublin4");
        CustomerDTO newCustomerDto = mapper.customerToCustomerDto(newCustomer);

        BDDMockito.given(customerDAO.save(any(Customer.class))).willReturn(newCustomer);

        CustomerDTO createdCustomer = service.createNewCustomer(newCustomerDto);

        Assertions.assertThat(createdCustomer.getName()).isEqualTo(newCustomer.getName());
        Assertions.assertThat(createdCustomer.getEmail()).isEqualTo(newCustomer.getEmail());

        BDDMockito.then(customerDAO).should().save(any(Customer.class));
    }

    @Test
    void shouldThrowsCustomerExceptionOnCreateNewCustomer(){

        CustomerDTO newCustomer = new CustomerDTO ("", "newcustomer4@email.com", "12345694", "street 4", "D01JH94", "Ireland4", "Dublin4");

        Assertions.assertThatThrownBy(() -> service.createNewCustomer(newCustomer))
                .isInstanceOf(CustomersException.class)
                .hasMessage("Invalid CustomerDTO");

    }

    @Test
    void shouldUpdateCustomer(){
        Customer updatedCustomer = new Customer(4,"Test4", "newcustomer4@email.com", "12345694", "street 4", "D01JH94", "Ireland4", "Dublin4");
        CustomerDTO customerDTO = mapper.customerToCustomerDto(updatedCustomer);

        BDDMockito.given(customerDAO.save(any(Customer.class))).willReturn(updatedCustomer);

        CustomerDTO updtCustomer = service.updateCustomer(customerDTO);

        Assertions.assertThat(updtCustomer.getName()).isEqualTo(updatedCustomer.getName());
        Assertions.assertThat(updtCustomer.getEmail()).isEqualTo(updatedCustomer.getEmail());

        BDDMockito.then(customerDAO).should().save(any(Customer.class));
    }

    @Test
    void shouldReturnAllCustomers(){
        BDDMockito.given(customerDAO.findAll()).willReturn(list);

        var customers = service.findAll();

        Assertions.assertThat(customers).containsAll(mapper.listCustomerToCustomerDto(list));

        BDDMockito.then(customerDAO).should().findAll();
    }

    @Test
    void shouldReturnCustomerByPhoneNumber(){
        var customer = list.get(0);

        BDDMockito.given(customerDAO.findByPhoneNumber(customer.getPhoneNumber())).willReturn(customer);

        var result = service.getCustomerByPhoneNumber(customer.getPhoneNumber());

        Assertions.assertThat(result.getName()).isEqualTo(customer.getName());
        Assertions.assertThat(result.getEmail()).isEqualTo(customer.getEmail());

        BDDMockito.then(customerDAO).should().findByPhoneNumber(customer.getPhoneNumber());

    }


}