package br.rodrigo.training.challenges;

import java.io.IOException;
import java.util.*;

class Result4 {

    /*
     * Complete the 'predictAnswer' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stockData
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> predictAnswer_(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> results = new ArrayList<>();

        int arr[][] = new int[stockData.size()][2];

        for (int i = 0; i < stockData.size(); i++) {
            arr[i][0] = i + 1; // day
            arr[i][1] = stockData.get(i); // price
        }

        Arrays.sort(arr, Comparator.comparing((price) -> price[1]));

        for (int query : queries) {
            int stockPrice = stockData.get(query - 1);
            int dayBefore = -1;
            int dayAfter = -1;
            for (int i = 0; i < stockData.size(); i++) {
                if(arr[i][0] < query && dayBefore < arr[i][0] && arr[i][1] < stockPrice){
                    dayBefore = arr[i][0];
                }
            }
            results.add(dayBefore);
        }

        return results;

    }

    public static List<Integer> predictAnswer2(List<Integer> stockData, List<Integer> queries) {
        List<Integer> results = new ArrayList<>();

        Integer[] stockDataArr = new Integer[stockData.size()];
        stockDataArr = stockData.toArray(stockDataArr);

        for (int j = 0; j < queries.size(); j++) {
            int dayToken = queries.get(j) - 1;
            int dayBefore = -1;
            int iterations = stockData.size();
            int tokenBefore = dayToken - 1;
            int tokenAfter = dayToken + 1;
            while (iterations > 0){
                if(tokenBefore >= 0 && stockDataArr[tokenBefore] < stockDataArr[dayToken]){
                    dayBefore = tokenBefore + 1;
                    break;
                } else if(tokenAfter < stockData.size() && stockDataArr[tokenAfter] < stockDataArr[dayToken]){
                    dayBefore = tokenAfter + 1;
                    break;
                }
                tokenAfter++;
                tokenBefore--;
                iterations--;
            }
            results.add(dayBefore);
        }

        return results;
    }

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        List<Integer> results = new ArrayList<>();

        Integer[] stockDataArr = new Integer[stockData.size()];
        stockDataArr = stockData.toArray(stockDataArr);

        for (int j = 0; j < queries.size(); j++) {
            int query = queries.get(j);
            int dayBefore = -1;
            int dayAfter = -1;
            for (int i = query - 2; i >= 0; i--) {
                if(stockDataArr[i] < stockDataArr[query - 1]){
                    dayBefore = i + 1;
                    break;
                }
            }

            for (int i = query; i < stockData.size(); i++) {
                if(stockDataArr[i] < stockDataArr[query - 1]){
                    dayAfter = i + 1;
                    break;
                }
            }

            if(dayBefore == -1) {
                results.add(dayAfter);
            } else if (dayAfter == -1) {
                results.add(dayBefore);
            } else if (dayAfter - query < query -dayBefore){
                results.add(dayAfter);
            } else {
                results.add(dayBefore);
            }

        }

        return results;
    }

}

public class ChallengeStocks {

    public static void main(String[] args) throws IOException {
        List<Integer> stockData = new ArrayList<>();
        List<Integer> queries = new ArrayList<>();

//        stockData.add(5);
//        stockData.add(6);
//        stockData.add(8);
//        stockData.add(4);
//        stockData.add(9);
//        stockData.add(10);
//        stockData.add(8);
//        stockData.add(3);
//        stockData.add(6);
//        stockData.add(4);
//
//        queries.add(3);
//        queries.add(1);
//        queries.add(8);

        stockData.add(89214);
        stockData.add(26671);
        stockData.add(75144);
        stockData.add(32445);
        stockData.add(13656);
        stockData.add(66289);
        stockData.add(21951);
        stockData.add(10265);
        stockData.add(59857);
        stockData.add(59133);
        stockData.add(63227);
        stockData.add(86121);
        stockData.add(37411);
        stockData.add(54628);
        stockData.add(25859);
        stockData.add(43510);
        stockData.add(63756);
        stockData.add(54763);
        stockData.add(30852);
        stockData.add(53243);
        stockData.add(76238);
        stockData.add(96885);
        stockData.add(33074);
        stockData.add(17745);
        stockData.add(81814);
        stockData.add(43436);
        stockData.add(79172);
        stockData.add(92819);
        stockData.add(30001);
        stockData.add(68442);
        stockData.add(54021);
        stockData.add(35566);
        stockData.add(95113);
        stockData.add(29164);
        stockData.add(84362);
        stockData.add(25120);
        stockData.add(11804);
        stockData.add(6313);
        stockData.add(51736);
        stockData.add(71661);
        stockData.add(81797);
        stockData.add(14962);
        stockData.add(57781);
        stockData.add(35560);
        stockData.add(85941);
        stockData.add(99991);
        stockData.add(95421);
        stockData.add(66048);
        stockData.add(54754);
        stockData.add(26272);
        stockData.add(35642);
        stockData.add(47343);
        stockData.add(39508);
        stockData.add(85068);
        stockData.add(65087);
        stockData.add(21321);
        stockData.add(28503);
        stockData.add(60611);
        stockData.add(30491);
        stockData.add(58503);
        stockData.add(29052);
        stockData.add(84512);
        stockData.add(94069);
        stockData.add(40516);
        stockData.add(13675);
        stockData.add(78430);
        stockData.add(65635);
        stockData.add(25479);
        stockData.add(1094);
        stockData.add(17370);
        stockData.add(13491);
        stockData.add(99243);
        stockData.add(48683);
        stockData.add(71271);
        stockData.add(34802);
        stockData.add(34624);
        stockData.add(87613);
        stockData.add(46574);
        stockData.add(671);
        stockData.add(42366);
        stockData.add(89197);
        stockData.add(36313);
        stockData.add(89708);
        stockData.add(28704);
        stockData.add(21380);
        stockData.add(54795);
        stockData.add(66376);
        stockData.add(49882);
        stockData.add(15405);
        stockData.add(96867);
        stockData.add(24737);
        stockData.add(60808);
        stockData.add(81378);
        stockData.add(35157);
        stockData.add(1324);
        stockData.add(11404);
        stockData.add(29938);
        stockData.add(66958);
        stockData.add(53234);

        queries.add(80);
        queries.add(24);
        queries.add(26);
        queries.add(62);
        queries.add(46);
        queries.add(79);
        queries.add(85);
        queries.add(59);
        queries.add(52);
        queries.add(8);
        queries.add(76);
        queries.add(48);
        queries.add(72);
        queries.add(84);
        queries.add(3);
        queries.add(3);
        queries.add(30);
        queries.add(30);
        queries.add(36);
        queries.add(86);
        queries.add(96);
        queries.add(72);
        queries.add(93);
        queries.add(25);
        queries.add(28);
        queries.add(68);
        queries.add(81);
        queries.add(18);
        queries.add(78);
        queries.add(14);
        queries.add(1);
        queries.add(57);
        queries.add(90);
        queries.add(26);
        queries.add(18);
        queries.add(87);
        queries.add(56);
        queries.add(55);
        queries.add(97);
        queries.add(59);
        queries.add(62);
        queries.add(73);
        queries.add(58);
        queries.add(85);
        queries.add(8);
        queries.add(60);
        queries.add(87);
        queries.add(89);
        queries.add(89);
        queries.add(22);

        List<Integer> list = Result4.predictAnswer2(stockData, queries);

        for (int i : list){
            System.out.println(i);
        }

    }
}
