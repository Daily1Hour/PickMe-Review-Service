package com.pickme.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final WebClient.Builder webClientBuilder;

    public List<Map<String, String>> getExternalData() {
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:3030").build();
        return webClient.get()
                .uri("/dummy2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, String>>>() {})
                .block();
    }
}
