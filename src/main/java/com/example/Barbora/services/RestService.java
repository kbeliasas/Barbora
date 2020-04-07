package com.example.Barbora.services;

import com.example.Barbora.models.BarboraApiModels.BarboraApiModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    @Value("${barbora.header.authorization}")
    private String barboraAuthorization;
    @Value("${barbora.header.cookie}")
    private String barboraCookie;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BarboraApiModel getBarboraTimes() {

        String url = "https://www.barbora.lt/api/eshop/v1/cart/deliveries";

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", barboraAuthorization);
        headers.set("Cookie", barboraCookie);

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<BarboraApiModel> response = this.restTemplate.exchange(url, HttpMethod.GET, request, BarboraApiModel.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.out.println("Request failed.");
            System.out.println(response.getHeaders());
            System.out.println("Status = " + response.getStatusCode());
            return null;
        }

    }
}
