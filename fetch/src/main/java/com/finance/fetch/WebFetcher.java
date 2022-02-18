package com.finance.fetch;

import com.finance.fetch.models.Account;
import com.finance.fetch.models.Accounts;
import com.finance.fetch.models.RequestInput;
import com.finance.fetch.models.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

public class WebFetcher extends Fetcher {

    private static final String WEB_URL = "https://candidates-test.herokuapp.com/fakebank";
    public static final String BODY = "body";
    public static final String H_2_CONTAINS_ACCOUNT_NAME = "h2:contains(Account name)";
    public static final String DIV_CONTAINS_BLANCE = "div:contains(Blance)";
    public static final String SPACE = " ";
    public static final String DIV_CONTAINS_ACCCOUNT_NUMBER_IDS = "div:contains(Acccount Number (Ids))";
    public static final String DIV_CONTAINS_TRANSACTIONS = "div:contains(Transactions)";
    public static final String DIV = "div";
    public static final String NO_AVAILABLE_DATA = "N/A";

    public static Accounts fetch(RestTemplate restTemplate, RequestInput requestInput) {
        Document document = new Document(WEB_URL);
        try {
            document = Jsoup.connect(document.location()).get();
            Elements divs = document.select(BODY);
            String s = divs.text();
            divs.forEach(div -> {
                div.text();
            });
        } catch (
                IOException e) {
            e.getMessage();
        }

        Elements accName = document.select(H_2_CONTAINS_ACCOUNT_NAME).get(0).children();
        Elements accBalance = document.select(DIV_CONTAINS_BLANCE).get(2).children();
        String[] balanceArray = accBalance.get(1).text().split(SPACE);

        Elements accNumber = document.select(DIV_CONTAINS_ACCCOUNT_NUMBER_IDS).get(2).children();
        Elements accTransactions = document.select(DIV_CONTAINS_TRANSACTIONS).get(1).children();
        ArrayList<Transaction> transactionArrayList = getTransactions(accTransactions);

        Accounts fetchedAccounts = getAccountsFromFetchedData(new Account(accNumber.get(1).text(), NO_AVAILABLE_DATA, transactionArrayList,
                Double.parseDouble(balanceArray[0])));
        return fetchedAccounts;
    }

    private static ArrayList<Transaction> getTransactions(Elements accTransactions) {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();

        for (Element accTransaction : accTransactions) {
            if (!accTransaction.select(DIV).isEmpty()) {
                Elements suspectedTransactions = accTransaction.select(DIV).get(0).children();
                if (suspectedTransactions.text().contains("Id") && suspectedTransactions.size() == 4) {
                    String id = suspectedTransactions.get(0).children().get(1).text();
                    String date = suspectedTransactions.get(1).children().get(1).text(); // save for future use if needed
                    String amountWithCurrency = suspectedTransactions.get(2).children().get(1).text();
                    String[] amount = amountWithCurrency.split(SPACE);
                    String description = suspectedTransactions.get(3).children().get(1).text(); // save for future use if needed
                    Transaction transaction = new Transaction(Long.parseLong(id), Double.parseDouble(amount[0]), amount[1]);
                    transactionArrayList.add(transaction);
                }
            }
        }
        return transactionArrayList;
    }

    private static Accounts getAccountsFromFetchedData(Account fetchedAccount1) {
        Account fetchedAccount = fetchedAccount1;
        ArrayList<Account> accountArrayList = new ArrayList<>();
        accountArrayList.add(fetchedAccount);
        Accounts fetchedAccounts = new Accounts();
        fetchedAccounts.setAccounts(accountArrayList);
        return fetchedAccounts;
    }
}
