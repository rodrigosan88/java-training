package br.rodrigo.training.challenges;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Response{
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;

    public Response() {
        super();
    }

    public Response(Integer page, Integer perPage, Integer total, Integer totalPages) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {

        return "Page: " + this.page + "\n"
                + "Per page: " + this.perPage + "\n"
                + "Total: " + this.total + "\n"
                + "Total pages: " + this.totalPages + "\n";
    }
}

class Transaction {
    private Integer userId;
    private Integer locationId;
    private String ip;
    private Double amount;
    private Boolean credit;

    public Transaction (){
        super();
    }

    public Transaction(Integer userId, Integer locationId, String ip, Double amount, Boolean credit) {
        this.userId = userId;
        this.locationId = locationId;
        this.ip = ip;
        this.amount = amount;
        this.credit = credit;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public String getIp() {
        return ip;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getCredit() {
        return credit;
    }

    @Override
    public String toString() {

        return "User id: " + this.userId + "\n"
                + "Location id: " + this.locationId + "\n"
                + "Net start: " + this.ip + "\n"
                + "Amount: " + this.amount + "\n"
                + "Credit: " + this.credit + "\n";
    }
}

class Result {

    public static final String JSON_PATTERN = "(\"key\":)((\"([A-Za-z0-9,.$\\s]*\")|(\\d+)|(\\{[^\\}]*\\})|(\\[\\{.*\\}\\])))";

    /*
     * Complete the 'getTransactions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER userId
     *  2. INTEGER locationId
     *  3. INTEGER netStart
     *  4. INTEGER netEnd
     *
     *  https://jsonmock.hackerrank.com/api/transactions/search?userId=
     */

    public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {

        String jsonString = doHttpGet(userId, 1);
        Double total = 0D;

        if(jsonString != null){
            try{
                Response response = new Response(
                        Integer.parseInt(getJsonNodeValueBy("page", jsonString).replace("\"", "")),
                        Integer.parseInt(getJsonNodeValueBy("per_page", jsonString)),
                        Integer.parseInt(getJsonNodeValueBy("total", jsonString)),
                        Integer.parseInt(getJsonNodeValueBy("total_pages", jsonString)));

                for (int i = 2; i <= response.getTotalPages() + 1; i++) {
                    String[] transactions = getJsonNodeArrayBy("data",jsonString);

                    for (int j = 0; j < transactions.length; j++) {
                        String trx = transactions[j];
                        Transaction transaction = new Transaction();
                        transaction.setLocationId(Integer.parseInt(getJsonNodeValueBy("id", getJsonNodeValueBy("location", trx))));
                        transaction.setUserId(Integer.parseInt(getJsonNodeValueBy("userId", trx)));
                        transaction.setIp(getJsonNodeValueBy("ip", trx).replace("\"",""));
                        transaction.setCredit(getJsonNodeValueBy("txnType", trx).contains("credit"));
                        System.out.println("Summing transaction " + Integer.parseInt(getJsonNodeValueBy("id", trx)));
                        String amountStr = getJsonNodeValueBy("amount", trx);
                        if(!amountStr.isEmpty()) {
                            amountStr = amountStr.replace("\"", "");
                            amountStr = amountStr.replace("$", "");
                            amountStr = amountStr.replace(",", "");
                            transaction.setAmount(Double.parseDouble(amountStr));
                        }

                        if(transaction.getUserId() == userId && transaction.getLocationId() == locationId){
                            Integer ipFirstByte = Integer.parseInt(transaction.getIp().split("\\.")[0]);
                            if(ipFirstByte >= netStart && ipFirstByte <= netEnd){
                                total = total + transaction.getAmount();
                            }
                        }
                    }

                    jsonString = doHttpGet(userId, i);
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        return new Long(Math.round(total)).intValue();
    }

    private static String doHttpGet(Integer userId, Integer page){
        String jsonString = "";
        try {
            URL url = new URL(getTransactionsEndpoint(userId, page));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonString = br.readLine();
        } catch (IOException exception) {

        }

        return jsonString;
    }

    private static String[] getJsonNodeArrayBy(String key, String jsonString) {
        String jsonNodeValue = getJsonNodeValueBy(key, jsonString);
        String[] jsonNodeArray = {};

        if(jsonNodeValue != null && !jsonNodeValue.isEmpty()) {
            jsonNodeArray = jsonNodeValue.split("},\\{");
        }

        return jsonNodeArray;
    }


    private static String getJsonNodeValueBy(String key, String jsonString) {
        String jsonNodeValue = null;
        Pattern pattern = Pattern.compile(JSON_PATTERN.replace("key", key));
        Matcher matcher = pattern.matcher(jsonString);
        if(matcher.find()) {
            String[] jsonNodeArray = matcher.group(0).split(":",2);
            jsonNodeValue = jsonNodeArray[1];
        }

        return jsonNodeValue == null ? "" : jsonNodeValue;
    }

    private static String getTransactionsEndpoint(Integer userId, Integer page) {
        String baseUrl = "https://jsonmock.hackerrank.com/api/transactions/search?userId=" + userId;
        if(page != null) {
            baseUrl = baseUrl + "&page=" + page;
        }

        return baseUrl;
    }
}
public class ChallengeJsonResponse {
    public static void main(String[] args) throws IOException {

        int result = Result.getTransactions(1, 9, 100, 220);

        System.out.println(result);
    }
}
