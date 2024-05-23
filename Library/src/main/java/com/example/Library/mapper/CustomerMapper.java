package com.example.Library.mapper;

import com.example.Library.entities.Customer;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);
    Customer toCustomers(CustomerRequest customerRequest);
    void updateCustomer(@MappingTarget Customer customer, CustomerRequest request);
}
