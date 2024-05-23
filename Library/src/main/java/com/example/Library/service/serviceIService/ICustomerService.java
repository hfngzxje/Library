package com.example.Library.service.serviceIService;

import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest);
    void deleteCustomer(Long customerId);
    CustomerResponse getCustomerById(Long customerId);
    List<CustomerResponse> getAllCustomer(int page, int pageSize);
    List<CustomerResponse> searchByName(int page, int pageSize, String name);
}
