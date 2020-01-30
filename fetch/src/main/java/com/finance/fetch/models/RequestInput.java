package com.finance.fetch.models;

import com.finance.fetch.ChannelEnum;
import com.finance.fetch.RedisHandler;
import com.finance.fetch.controllers.FetchApplication;

import static com.finance.fetch.controllers.FetchApplication.NOT_SUPPORTED_YET;

public class RequestInput implements Runnable {

    private String userId;
    private String userName;
    private ChannelEnum channel;

    public RequestInput(String userId, String userName, ChannelEnum channel) {
        this.userId = userId;
        this.userName = userName;
        this.channel = channel;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public ChannelEnum getChannel() {
        return channel;
    }


    @Override
    public void run() {
        Object accounts = FetchApplication.getFetcherResponse(this);
        if (!accounts.equals(NOT_SUPPORTED_YET))
            RedisHandler.saveToDb((Accounts) accounts, this.getChannel());
    }
}
