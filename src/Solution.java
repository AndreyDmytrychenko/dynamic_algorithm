import java.util.*;
import java.util.stream.Collectors;

//dynamic algorithm for issuing the amount of available banknotes

public class Solution {

    private static Map<Integer,Integer> data = new TreeMap<>();
    // test data
    static {
        data.put(3,10);
        data.put(4,10);
        data.put(5,10);
    }
    // test data
    private static int sum = 29;

    public static void main(String[] args) throws Exception {

        Map<Integer, Integer> map = dynamicAlgorithm(mapToSortList(data), sum);

        map.forEach((k, v) -> System.out.println(k + " " + v));

        try {
            printResOfDynamicAlgorithm(map,sum);
        } catch (Exception e) {
            System.out.println("it is not possible to issue the specified amount with available banknotes");
        }
    }

    public static Map<Integer, Integer> dynamicAlgorithm(List<Integer> list, int sum) {


        Map<Integer, Integer> result = new TreeMap();
        result.put(0, 0);

        Map<Integer, Integer> update;

        for (int i = 0; i < list.size(); i++) {
            if (result.containsKey(sum)) {
                break;
            }
            update = new HashMap<>();

            for (int t : result.keySet()) {
                int k = t + list.get(i);
                int v = list.get(i);
                if (k > sum) continue;

                update.put(k, v);
            }
            result.putAll(update);
        }
        return result;
    }

    public static void printResOfDynamicAlgorithm(Map<Integer, Integer> map, int sum) throws Exception {
        if (!map.containsKey(sum)) {
            throw new Exception();
        }
        Map<Integer, Integer> res = new TreeMap<>();

        int v = 0;
        int k = 0;
        List<Integer> list = map.keySet().stream().sorted((v1, v2) -> Integer.compare(v2, v1)).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {

            int kay = list.get(i);
            int val = map.get(kay);

            if (kay == sum) {
                if (val != k) v = 0;
                k = val;
                v++;
                if (isMoreThenElse(data, k, v)) continue;
                res.put(k, v);
                if (kay == val) break;
                sum -= k;
            }
        }
        res.forEach( (k1, v1) -> System.out.println(k1 + " - " + v1));
    }

    private static List<Integer> mapToSortList(Map<Integer, Integer> map) {

        List<Integer> list = new LinkedList<>();

        map.forEach( (k, v) -> {
            for (int i = 0; i < v; i++) {
                list.add(k);
            }
        });
        list.sort( (v1,v2) -> Integer.compare(v2,v1));
        return list;
    }

    private static boolean isMoreThenElse(Map<Integer,Integer> map, int kay, int val) {
        return val > map.get(kay);
    }

}

