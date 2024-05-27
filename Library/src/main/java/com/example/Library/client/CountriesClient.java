package com.example.Library.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "countriesClient", url = "https://api.first.org/data/v1")
public interface CountriesClient {

    @GetMapping("/countries")
    Map<String, Object> getCountries();
}
