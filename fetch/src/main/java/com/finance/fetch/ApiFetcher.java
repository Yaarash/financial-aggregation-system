package com.finance.fetch;

import com.finance.fetch.models.Accounts;
import com.finance.fetch.models.RequestInput;
import org.springframework.web.client.RestTemplate;

public class ApiFetcher extends Fetcher {

    private static final String API_URL = "https://candidates-test.herokuapp.com/transactions";

    public static Accounts fetch(RestTemplate restTemplate, RequestInput requestInput) {
        Accounts accounts = restTemplate.getForObject(
                API_URL, Accounts.class);
        log.info(accounts.toString());
        return accounts;
    }
}
