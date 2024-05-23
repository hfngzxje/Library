package com.example.Library.controller;

import com.example.Library.dtos.response.BookResponse;
import com.example.Library.service.CustomerService;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.ApiResponse;
import com.example.Library.dtos.response.CustomerResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @PostMapping()
    ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .massage("Add customer successfully!!")
                .result(customerService.addCustomer(request))
                .build();
    }

    @GetMapping("{customerId}")
    ApiResponse<CustomerResponse> findByCustomerId(@PathVariable Long customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ApiResponse.<CustomerResponse>builder()
                .result(customerResponse)
                .build();
    }

    @GetMapping()
    ApiResponse<List<CustomerResponse>> getAllCustomer(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int pageSize) {
        List<CustomerResponse> list = customerService.getAllCustomer(page,pageSize);
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(list)
                .build();
    }

    @DeleteMapping("{customerId}")
    ApiResponse<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ApiResponse.<Void>builder()
                .massage("Delete successfully!!")
                .build();
    }

    @PutMapping("{customerId}")
    ApiResponse<CustomerResponse> updateBook(@PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .massage("Update successfully!!")
                .result(customerService.updateCustomer(customerId,customerRequest))
                .build();
    }

    @GetMapping("searchByName")
    ApiResponse<List<CustomerResponse>> searchByName(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int pageSize,
                                                  @RequestParam(required = false) String name) {
        List<CustomerResponse> list = customerService.searchByName(page,pageSize,name);
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(list)
                .massage("Search successfully!!")
                .build();
    }
}
