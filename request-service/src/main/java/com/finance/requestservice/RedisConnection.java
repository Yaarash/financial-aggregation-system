package com.finance.requestservice;

import redis.clients.jedis.Jedis;

public class RedisConnection {

    //This shall be the representation of our redis keys userId_channel
    private static final String KEY_TEMPLATE ="%s_%s";
    static Jedis jedis = null;

    public static String readFromDb(String userId, String channel) {
        //Connecting to Redis on localhost
        jedis = new Jedis("localhost");

        String key = String.format(KEY_TEMPLATE, userId, channel);
        //Check if the requested key exist in db
        boolean doesKeyExist = jedis.strlen(key) > 0;

        if (doesKeyExist)
            return jedis.get(key);
        else
            return "Key " + key + " does not exist in our db";
    }
}
