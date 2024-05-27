package com.example.Library.controller;

import com.example.Library.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/api/countries")
    public Map<String, Object> getCountries() {
        return countryService.getCountries();
    }
}
