package com.finance.requestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.finance.requestservice.RedisConnection.readFromDb;

@RestController
@SpringBootApplication
public class RequestServiceApplication {

    private static final String FETCH_SERVICE_URL_TEMPLATE = "http://localhost:8081/fetch?userName=%s&userId=%s&channel=%s";
    private static final long FOUR_HOUR_THRESHOLD = 14400000;
    private static final Logger LOG = LoggerFactory.getLogger(RequestServiceApplication.class);

    RestTemplate restTemplate;

    public RequestServiceApplication() {
        restTemplate = new RestTemplate();
    }

    /*
     * lsad = shorten last successful aggregation date
     * */
    @RequestMapping("/ondemand")
    public String onDemandController(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "channel") String channel,
                                     @RequestParam(value = "lsad") long lsad) {
        //Time passed since last aggregation is less than 4 hours
        if (System.currentTimeMillis() - lsad < FOUR_HOUR_THRESHOLD) {
            return readFromDb(userId, channel);
        } else return callFetchService(userName, userId, channel).toString();
    }

    @RequestMapping("/data")
    public String getDataController(@RequestParam(value = "userName") String userName,
                                    @RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "channel") String channel) {

        return readFromDb(userId, channel);
    }


    private String callFetchService(String userName, String userId, String channel) {
        String url = String.format(FETCH_SERVICE_URL_TEMPLATE, userName, userId, channel);
        String response = restTemplate.getForObject(url, String.class);
        LOG.info(response);
        return readFromDb(userId, channel);
    }

    public static void main(String[] args) {
        SpringApplication.run(RequestServiceApplication.class, args);
    }
}
