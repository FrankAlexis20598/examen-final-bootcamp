package com.microservicio.atmdeposit.configurations;

import com.microservicio.atmdeposit.apiclients.apis.*;
import com.microservicio.atmdeposit.apiclients.ApiNetwork;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.microservicio.atmdeposit.util.Constants.*;

@Configuration
public class ApisConfiguration {

    @Bean
    public IApiAccounts apiAccounts() {
        return ApiNetwork.createService(IApiAccounts.class, BASE_URL_API_ACCOUNTS);
    }

    @Bean
    public IApiCards apiCards() {
        return ApiNetwork.createService(IApiCards.class, BASE_URL_API_CARDS);
    }

    @Bean
    public IApiFingerprints apiFingerprints() {
        return ApiNetwork.createService(IApiFingerprints.class, BASE_URL_API_FINGERPRINTS);
    }

    @Bean
    public IApiPersons apiPersons() {
        return ApiNetwork.createService(IApiPersons.class, BASE_URL_API_PERSONS);
    }

    @Bean
    public IApiReniec apiReniec() {
        return ApiNetwork.createService(IApiReniec.class, BASE_URL_API_RENIEC);
    }
}
