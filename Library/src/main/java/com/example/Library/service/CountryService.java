package com.example.Library.service;

import com.example.Library.client.CountriesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountriesClient countriesClient;

    public Map<String, Object> getCountries() {
        return countriesClient.getCountries();
    }
}
