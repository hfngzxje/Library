package com.example.Library.service;

import com.example.Library.entities.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    public ApiResponses getAllCountries() {
        String url = "https://api.first.org/data/v1/countries";
        return restTemplate.getForObject(url, ApiResponses.class);
    }
}
