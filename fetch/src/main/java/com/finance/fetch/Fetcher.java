package com.finance.fetch;

import com.finance.fetch.models.Accounts;
import com.finance.fetch.models.RequestInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public abstract class Fetcher {

    static final Logger log = LoggerFactory.getLogger(ApiFetcher.class);
    public static Accounts fetch(RestTemplate restTemplate, RequestInput requestInput){return null;};
}
