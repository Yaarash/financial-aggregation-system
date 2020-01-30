package com.finance.fetch;

import com.finance.fetch.models.Account;
import com.finance.fetch.models.Accounts;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class RedisHandler {

    //This shall be the representation of our redis keys userId_channel
    private static final String KEY_TEMPLATE ="%s_%s";
    static Jedis jedis = null;

    public static void saveToDb(Accounts accounts, ChannelEnum channel){
        //Connecting to Redis on localhost
        jedis = new Jedis("localhost");

        ArrayList<Account> accountArrayList = accounts.getAccounts();
        for (Account account: accountArrayList ) {
            String key = String.format(KEY_TEMPLATE, account.getAccountId(), channel);
            jedis.set(key, account.toString());
        }
    }
}
