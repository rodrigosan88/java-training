package br.rodrigo.training.challenges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result3 {

    /*
     * Complete the 'kthPerson' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY p
     *  3. INTEGER_ARRAY q
     */

    public static List<Integer> kthPerson2(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        List<Integer> kthPerson = new ArrayList<>();

        for (Integer time : q) {
            int filledSpots = 0;
            int lastSeated = 0;
            for (int j = 0; j < p.size(); j++) {
                if(time <= p.get(j)){
                    lastSeated = j + 1;
                    filledSpots++;
                } else {
                    continue;
                }

                if(filledSpots == k) {
                    break;
                }
            }
            if(filledSpots < k) {
                lastSeated = 0;
            }
            kthPerson.add(lastSeated);
        }

        return kthPerson;
    }

    public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        List<Integer> kthPerson = new ArrayList<>();

        for (Integer time : q) {
            List<Integer> personMap = IntStream.range(0, p.size()).filter(i -> p.get(i) >= time).mapToObj(i -> i).collect(Collectors.toList());
            if(!personMap.isEmpty() && personMap.size() > 1){
                kthPerson.add(personMap.get(k-1) + 1);
            } else {
                kthPerson.add(0);
            }
        }
        return kthPerson;
    }

    public static List<Integer> kthPerson_(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        List<Integer> kthPerson = new ArrayList<>();

        for (Integer time : q) {
            int filledSpots = 0;
            int lastSeated = 0;

            lastSeated = getOnTheBus(p, 0, time, k, 0);

            kthPerson.add(lastSeated);
        }

        return kthPerson;
    }

    public static int getOnTheBus(List<Integer> p, int index, int time, int k, int seatsTaken){

        if(k > seatsTaken && index < p.size()){
            if (time <= p.get(index)){
                index = getOnTheBus(p, ++index, time, k, ++seatsTaken);
            } else {
                index = getOnTheBus(p, ++index, time, k, seatsTaken);
            }
        } else if( k > seatsTaken){
            index = 0;
        }

        return index;
    }

}

public class ChallengeBusLine {
    public static void main(String[] args) throws IOException {
        System.out.println("DESAFIO 3");

        Integer k = 10;

        List<Integer> p = new ArrayList<>();
        List<Integer> q = new ArrayList<>();
        p.add(1);
        p.add(4);
        p.add(4);
        p.add(3);
        p.add(1);
        p.add(2);
        p.add(6);

        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);

        List<Integer> result  = Result3.kthPerson(k, p, q);

        for (int i : result){
            System.out.println(i);
        }
    }
}
