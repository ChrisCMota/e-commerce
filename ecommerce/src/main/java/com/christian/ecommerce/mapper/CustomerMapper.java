package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Mapping(source = "eircode", target = "eircode")
    Customer customerDtotoCustomer(CustomerDTO customerDTO);

    @Mapping(source = "eircode", target = "eircode")
    CustomerDTO customerToCustomerDto(Customer customer);

    List<CustomerDTO> listCustomerToCustomerDto(List<Customer> customers);
}
