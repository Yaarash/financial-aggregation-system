package com.finance.fetch.controllers;

import com.finance.fetch.ApiFetcher;
import com.finance.fetch.ChannelEnum;
import com.finance.fetch.WebFetcher;
import com.finance.fetch.models.RequestInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@SpringBootApplication
public class FetchApplication {

    public static final String NOT_SUPPORTED_YET = "Not supported yet";
    private static final int N_THREADS = 30;
    private static final int KEEP_ALIVE_TIME = 1;
    private static RestTemplate restTemplate;
    ThreadPoolExecutor executor;

    public FetchApplication() {
        restTemplate = new RestTemplate();
        executor = new ThreadPoolExecutor(N_THREADS, N_THREADS, KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @RequestMapping("/fetch")
    public Object fetchingController(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "channel") ChannelEnum channel) {
        RequestInput requestInput = new RequestInput(userId, userName, channel);
        executor.execute(requestInput);
        return "200 OK";
    }


    public static Object getFetcherResponse(RequestInput requestInput) {
        ChannelEnum channel = requestInput.getChannel();
        switch (channel) {
            case API:
                return ApiFetcher.fetch(restTemplate, requestInput);
            case WEB:
                return WebFetcher.fetch(restTemplate, requestInput);
            case STATEMENT:
                return NOT_SUPPORTED_YET;
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(FetchApplication.class, args);
    }
}
