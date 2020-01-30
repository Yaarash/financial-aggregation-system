package com.finance.fetch;

public enum ChannelEnum {

    API("api"),
    WEB("web"),
    STATEMENT("statement");

    private String channel;

    ChannelEnum(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
