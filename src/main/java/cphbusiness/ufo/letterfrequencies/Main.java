package cphbusiness.ufo.letterfrequencies;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] timer = new String[10];
        int n = 10;
        int count = 10;
        for (int j = 0; j < n; j++) {
            Timer t = new Timer();
            for (int i = 0; i < count; i++) {
                String fileName = "./src/main/resources/FoundationSeries.txt";
                Map<Character, Long> freq = new HashMap<>();
                tallyChars(fileName, freq);
                print_tally(sortMapByValues(freq));
            }
            double time = t.check() * 1e9 / count;
            timer[j] = String.format("%6.1f ns", time);
        }
        System.out.println("***********************");
        for (int i = 0; i < timer.length; i++) {
            System.out.println(timer[i]);
        }
        System.out.println("***********************");
    }

    private static void tallyChars(String fileName, Map<Character, Long> freq) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            int b;
            while ((b = bufferedInputStream.read()) != -1) {
                if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122)) {
                    char temp = (char) b;
                    char c = Character.toUpperCase(temp);
                    try {
                        freq.put(c, freq.get(c) + 1);
                    } catch (NullPointerException np) {
                        freq.put(c, 1L);
                    }
                }
            }
        }
    }

    private static Map<Character, Long> sortMapByValues(Map<Character, Long> freq) {
        return freq
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private static void print_tally(Map<Character, Long> freq) {
        for (Character c : freq.keySet()) {
            System.out.println("" + c + ": " + freq.get(c));
            ;
        }
    }
}