package com.example.Library.controller;

import com.example.Library.client.CountryApiClient;
import com.example.Library.entities.ApiResponses;
import com.example.Library.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CountryController {

    @Autowired
    private CountryApiClient countryApiClient;
    @Autowired
    private CountryService countryService;

    @GetMapping("/FeignClient/countries")
    public ApiResponses getCountries() {
        return countryApiClient.getAllCountries();
    }

    @GetMapping("/RestTemplate/countries")
    public ApiResponses restTemplateGetCountries() {
        return countryService.getAllCountries();
    }
}
