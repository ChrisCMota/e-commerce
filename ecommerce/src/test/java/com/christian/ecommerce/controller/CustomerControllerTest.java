package com.christian.ecommerce.controller;

import com.christian.ecommerce.dao.CustomerDAO;
import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.mapper.CustomerMapper;
import com.christian.ecommerce.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = CustomerController.class)
@ComponentScan(basePackages = {"com.christian"})
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CustomerDAO repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerMapper mapper;

    List<Customer> list = new ArrayList<>();

    @BeforeEach
    void init(){
        var customer1 = new Customer(1,"Test1", "newcustomer1@email.com", "12345691", "street 1", "D01JH91", "Ireland1", "Dublin1");
        var customer2 = new Customer(2,"Test2", "newcustomer2@email.com", "12345692", "street 2", "D01JH92", "Ireland2", "Dublin2");
        var customer3 = new Customer(3,"Test3", "newcustomer3@email.com", "12345693", "street 3", "D01JH93", "Ireland3", "Dublin3");
        list.addAll(List.of(customer1,customer2,customer3));
    }

    @Test
    void shouldReturnCustomersList() throws Exception {

        List<CustomerDTO> customerDTOS = mapper.listCustomerToCustomerDto(list);
        BDDMockito.given(repository.findAll()).willReturn(list);

        mvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTOS)));
    }

    @Test
    void shouldReturnCustomerById() throws Exception {
        Integer id = 1;
        CustomerDTO customerDTO = mapper.customerToCustomerDto(list.get(0));

        BDDMockito.given(repository.findById(id)).willReturn(list.stream().filter(customer -> customer.getId().equals(id)).findFirst());

        mvc.perform(MockMvcRequestBuilders.get("/customers/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTO)));
    }

    @Test
    void shouldThrowsCustomerException() throws Exception {
        Integer id = 4;

        String responseBody = """
                {"status":400,"message":"[ERROR]: Could not retrieve customer by id"}
                """;

        BDDMockito.given(repository.findById(id)).willReturn(list.stream().filter(customer -> customer.getId().equals(id)).findFirst());

        mvc.perform(MockMvcRequestBuilders.get("/customers/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(responseBody));
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenJsonIsMissingValues() throws Exception {
        CustomerDTO wrongCustomer = new CustomerDTO("", "newcustomer1@email.com", "12345691", "street 1", "D01JH91", "Ireland1", "Dublin1");

        String response = """
                {"status":400,"message":"Invalid Field(s), some fields cannot be empty or blank"}
                """;

        mvc.perform(MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongCustomer)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    void shouldReturnCustomerByPhoneNumber() throws Exception {
        CustomerDTO customerByPhoneNumber = mapper.customerToCustomerDto(list.get(1));
        String phoneNumber = "12345692";

        BDDMockito.given(repository.findByPhoneNumber(phoneNumber)).willReturn(list.stream()
                .filter(customer -> customer.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .get());

        mvc.perform(MockMvcRequestBuilders.get("/customers/search")
                        .param("phoneNumber", phoneNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerByPhoneNumber)));

    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        Customer customer = list.get(0);
        CustomerDTO customerDTO = mapper.customerToCustomerDto(customer);
        customerDTO.setName("Test");
        String customerJson = objectMapper.writeValueAsString(customerDTO);

        BDDMockito.given(repository.findByEmail(customer.getEmail())).willReturn(customer);
        BDDMockito.given(repository.save(any(Customer.class))).willReturn(mapper.customerDtotoCustomer(customerDTO));

        mvc.perform(MockMvcRequestBuilders.put("/customers")
                        .content(customerJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(customerJson));

    }

    @Test
    void shouldInsertNewCustomer() throws Exception {
        CustomerDTO newCustomer = new CustomerDTO("Test3", "newcustomer3@email.com", "12345693", "street 3", "D01JH93", "Ireland3", "Dublin3");
        Customer customer = mapper.customerDtotoCustomer(newCustomer);

        BDDMockito.given(repository.save(mapper.customerDtotoCustomer(newCustomer))).willReturn(customer);

        mvc.perform(MockMvcRequestBuilders.post("/customers")
                .content(objectMapper.writeValueAsString(newCustomer))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(newCustomer)));
    }

    @Test
    void shouldReturnNotFoundWhenGetCustomerById() throws Exception{
        BDDMockito.given(repository.findById(1)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/customers/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json("{\"status\":400,\"message\":\"[ERROR]: Could not retrieve customer by id\"}"));
    }


}