package com.example.Library.entities;

import com.example.Library.entities.CountryData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponses {
     @JsonProperty("status-code")
     int statusCode;
     String status;
     String version;
     String access;
     @JsonProperty("data")
     Map<String, CountryData> data;
}
