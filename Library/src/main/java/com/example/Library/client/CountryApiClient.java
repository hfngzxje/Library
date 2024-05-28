package com.example.Library.client;

import com.example.Library.entities.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "CountryApi", url = "https://api.first.org/data/v1/countries")
public interface CountryApiClient {
    @GetMapping
    ApiResponses getAllCountries();
}
