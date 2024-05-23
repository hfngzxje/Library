package com.example.Library.service;

import com.example.Library.dtos.response.BookResponse;
import com.example.Library.entities.Books;
import com.example.Library.entities.Customer;
import com.example.Library.mapper.CustomerMapper;
import com.example.Library.repository.specifications.BookSpec;
import com.example.Library.repository.specifications.CustomerSpec;
import com.example.Library.service.serviceIService.ICustomerService;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.CustomerResponse;
import com.example.Library.enums.ErrorCode;
import com.example.Library.exception.AppException;
import com.example.Library.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService implements ICustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        if(customerRequest.getAge() < 0 || customerRequest.getAge()>120){
            throw new AppException(ErrorCode.INVALID_AGE);
        }
        Customer customer = customerMapper.toCustomers(customerRequest);
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()
                -> new AppException(ErrorCode.CUSTOMER_DONOT_EXISTED));
        if(customerRequest.getAge() <= 0 || customerRequest.getAge()>120){
            throw new AppException(ErrorCode.INVALID_AGE);
        }
        customerMapper.updateCustomer(customer,customerRequest);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new AppException(ErrorCode.CUSTOMER_DONOT_EXISTED);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_DONOT_EXISTED));
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomer(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        return customerPage.getContent().stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> searchByName(int page, int pageSize, String name) {
        Specification<Customer> spec = CustomerSpec.searchByName(name);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = customerRepository.findAll(spec,pageable);
        return customerPage.getContent().stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }
}
