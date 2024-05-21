package com.example.Library.Mapper;

import com.example.Library.Entities.Books;
import com.example.Library.Entities.Customer;
import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.BookResponse;
import com.example.Library.dtos.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toCustomerResponse(Customer customer);
    Customer toCustomers(CustomerRequest customerRequest);
    void UpdateCustomer(@MappingTarget Customer customer, CustomerRequest request);
}
