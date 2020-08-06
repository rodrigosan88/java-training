package br.rodrigo.training.challenges;

import java.io.IOException;
import java.util.*;

class Result2 {

    /*
     * Complete the 'countMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY firstDay
     *  2. INTEGER_ARRAY lastDay
     */

    public static int countMeetings2(List<Integer> firstDay, List<Integer> lastDay) {
        Integer meetings = 0;

        Set<Integer> meetingsSet = new HashSet<>();

        System.out.println(getSmallInterval(firstDay, lastDay));
        System.out.println(getBigInterval(firstDay, lastDay));

        for (int i = 0; i < firstDay.size(); i++) {
            int interval = lastDay.get(i) - firstDay.get(i);

            for (int j = firstDay.get(i); j <= lastDay.get(i); j++) {
                if(meetingsSet.contains(j)){
                    continue;
                } else {
                    meetingsSet.add(j);
                    break;
                }
            }
        }

        return meetingsSet.size();
    }

    public static int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
        int arr[][] = new int[lastDay.size()][2];
        Set<Integer> meetingsSet = new HashSet<>();

        for (int i = 0; i < firstDay.size(); i++) {
            arr[i][0] = firstDay.get(i);
            arr[i][1] = lastDay.get(i);
        }

        Arrays.sort(arr, Comparator.comparing((ed) -> ed[1]));

        for (int i = 0; i < firstDay.size(); i++) {
            int interval = arr[i][0] - arr[i][1];

            for (int j = arr[i][0]; j <= arr[i][1]; j++) {
                if(meetingsSet.contains(j)){
                    continue;
                } else {
                    meetingsSet.add(j);
                    break;
                }
            }
        }

        return meetingsSet.size();
    }

    private static int getSmallInterval(List<Integer> firstDay, List<Integer> lastDay){

        Integer interval = 100000;

        for (int i = 0; i < firstDay.size(); i++) {
            int aux = (lastDay.get(i) - firstDay.get(i)) + 1;
            if(aux <= interval) {
                interval = aux;
            }
        }

        return interval;
    }

    private static int getBigInterval(List<Integer> firstDay, List<Integer> lastDay){

        Integer interval = 1;

        for (int i = 0; i < firstDay.size(); i++) {
            int aux = (lastDay.get(i) - firstDay.get(i)) + 1;
            if(aux > interval) {
                interval = aux;
            }
        }

        return interval;
    }
}

public class ChallengeMeetings {
    public static void main(String[] args) throws IOException {

        List<Integer> firstDay = new ArrayList<>();
        List<Integer> lastDay = new ArrayList<>();

        firstDay.add(1);
        firstDay.add(2);
        firstDay.add(3);
        firstDay.add(3);
        firstDay.add(3);
        lastDay.add(2);
        lastDay.add(2);
        lastDay.add(4);
        lastDay.add(3);
        lastDay.add(3);

        int result = Result2.countMeetings(firstDay, lastDay);

        System.out.println(result);
    }
}
