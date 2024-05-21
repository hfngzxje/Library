package com.example.Library.Mapper;

import com.example.Library.Entities.Customer;
import com.example.Library.dtos.Request.CustomerRequest;
import com.example.Library.dtos.response.CustomerResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-21T16:17:34+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponse toCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        customerResponse.customerName( customer.getCustomerName() );
        customerResponse.age( customer.getAge() );

        return customerResponse.build();
    }

    @Override
    public Customer toCustomers(CustomerRequest customerRequest) {
        if ( customerRequest == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerName( customerRequest.getCustomerName() );
        customer.setAge( customerRequest.getAge() );

        return customer;
    }

    @Override
    public void UpdateCustomer(Customer customer, CustomerRequest request) {
        if ( request == null ) {
            return;
        }

        customer.setCustomerName( request.getCustomerName() );
        customer.setAge( request.getAge() );
    }
}
