package com.example.Library.Service.ServiceIService;

import com.example.Library.dtos.Request.BookRequest;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.BookResponse;
import com.example.Library.dtos.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest);
    void deleteCustomer(Long customerId);
    CustomerResponse getCustomerById(Long customerId);
    List<CustomerResponse> getAllCustomer(int page, int pageSize);
}
