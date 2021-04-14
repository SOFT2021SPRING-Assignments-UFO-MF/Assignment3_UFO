package cphbusiness.ufo.letterfrequencies;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        for (int j=0; j<n; j++) {
            Timer t = new Timer();
            for (int i=0; i<count; i++) {
                String fileName = "./src/main/resources/FoundationSeries.txt";
//                String content = getFileContentIntoString(fileName);
//                char[] arr = stringSortedToCharArray(content);
//                HashMap<Character, Integer> map = countFrequency(arr);
//                printMap(map);
                //------------------
//                try (FileInputStream is = new FileInputStream(fileName);
//                     BufferedInputStream bis = new BufferedInputStream(is)) {
//                    int b;
//                    while ((b = bis.read()) != -1) {
//                        //if(Pattern.compile("[a-zA-Z]").matcher(Character.toString((char) b)).matches()){
//                            //System.out.println("Char: " + (char) b);
//                        //}
//                    }
//                }
                //------------------
                //Reader reader = new FileReader(fileName);
                Map<Character, Long> freq = new HashMap<>();
                tallyChars(fileName, freq);
                //tallyChars(reader, freq);
                //print_tally(freq);
            }
            double time = t.check() * 1e9 / count;
            timer[j] = String.format("%6.1f ns",time);
        }
        System.out.println("***********************");
        for (int i = 0; i < timer.length; i++) {
            System.out.println(timer[i]);
        }
        System.out.println("***********************");
    }

    private static void tallyChars(String fileName, Map<Character, Long> freq) throws IOException {
        try (FileInputStream is = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(is)) {
            int b;
            while ((b = bis.read()) != -1) {
                char c = (char) b;
                    try {
                        freq.put(c, freq.get(c) + 1);
                    } catch (NullPointerException np) {
                        freq.put(c, 1L);
                    }
            }
        }
//        try (FileReader reader = new FileReader(fileName);
//             BufferedReader bufferedReader = new BufferedReader((reader))) {
//            int c;
//            while ((c = bufferedReader.read()) != -1) {
//                //System.out.println("Char: " + (char) c);
//            }
//        }
//        try (FileInputStream is = new FileInputStream(fileName);
//             BufferedInputStream bis = new BufferedInputStream(is)) {
//            int b;
//            while ((b = bis.read()) != -1) {
//                char c = (char) b;
//                if(freq.containsKey(c))
//                {
//                    freq.put((char) b, freq.get((char) b) + 1);
//                } else
//                {
//                    freq.put((char) b, 1L);
//                }
////                try {
////                    freq.put((char) b, freq.get((char) b) + 1);
////                } catch (NullPointerException np) {
////                    freq.put((char) b, 1L);
////                }
//                //if(Pattern.compile("[a-zA-Z]").matcher(Character.toString((char) b)).matches()){
//                //System.out.println("Char: " + (char) b);
//                //}
//            }
//        }
//        int b;
////        while ((b = reader.read()) != -1) {
//            try {
//                freq.put(b, freq.get(b) + 1);
//            } catch (NullPointerException np) {
//                freq.put(b, 1L);
//            }
////        }
    }
//    private static void tallyChars(Reader reader, Map<Character, Long> freq) throws IOException {
//        try (FileInputStream is = new FileInputStream(fileName);
//             BufferedInputStream bis = new BufferedInputStream(is)) {
//            int b;
//            while ((b = bis.read()) != -1) {
//                //if(Pattern.compile("[a-zA-Z]").matcher(Character.toString((char) b)).matches()){
//                //System.out.println("Char: " + (char) b);
//                //}
//            }
//        }
////        int b;
////        while ((b = reader.read()) != -1) {
////            try {
////                freq.put(b, freq.get(b) + 1);
////            } catch (NullPointerException np) {
////                freq.put(b, 1L);
////            }
////        }
//    }
//
//    private static void print_tally(Map<Integer, Long> freq) {
//        int dist = 'a' - 'A';
//        Map<Character, Long> upperAndlower = new LinkedHashMap();
//        for (Character c = 'A'; c <= 'Z'; c++) {
//            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
//        }
//        Map<Character, Long> sorted = upperAndlower
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));
//        for (Character c : sorted.keySet()) {
//            System.out.println("" + c + ": " + sorted.get(c));;
//        }
//    }

//    public static String getFileContentIntoString(String fileName) throws IOException {
//        //Source link: https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
//        Path file = Path.of(fileName);
//        String content = Files.readString(file);
//        return stringToUppercase(stringTrim(content));
//    }
//
//    public static String stringTrim(String input)
//    {
//        return input.trim();
//    }
//
//    public static String stringToUppercase(String input)
//    {
//        return input.toUpperCase();
//    }
//
//    public static char[] stringSortedToCharArray(String input)
//    {
//        char[] charArr = input.toCharArray();
//        return charArraySorted(charArr);
//    }
//
//    public static char[] charArraySorted(char[] inputArr)
//    {
//        Arrays.sort(inputArr);
//        return inputArr;
//    }
//
//    public static HashMap<Character, Integer> countFrequency(char[] inputArr)
//    {
//        int count = 0;
//        char currentChar = inputArr[0];
//        HashMap<Character,Integer> map = new HashMap<>();
//        for (int i = 0; i < inputArr.length; i++) {
//            if(inputArr[i] == currentChar)
//            {
//                currentChar = inputArr[i];
//                count++;
//            } else
//            {
//                if(!map.containsKey(currentChar))
//                {
//                    map.put(currentChar, count);
//                }
//                count = 0;
//            }
//        }
//        return map;
//    }
//
//    public static void printMap(Map<Character, Integer> inputMap)
//    {
//        for (Character c : inputMap.keySet()) {
//            System.out.println(c + ": " + inputMap.get(c));
//        }
//    }
}

//package cphbusiness.ufo.letterfrequencies;
//
//        import java.io.FileNotFoundException;
//        import java.io.FileReader;
//        import java.io.IOException;
//        import java.io.Reader;
//        import java.util.Collections;
//        import java.util.HashMap;
//        import java.util.LinkedHashMap;
//        import java.util.Map;
//        import static java.util.stream.Collectors.toMap;
//
///**
// * Frequency analysis Inspired by
// * https://en.wikipedia.org/wiki/Frequency_analysis
// *
// * @author kasper
// */
//public class Main {
//
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        String[] timer = new String[10];
//        int n = 10;
//        int count = 10;
//        //double dummy = 0.0;
//        for (int j=0; j<n; j++) {
//            Timer t = new Timer();
//            for (int i=0; i<count; i++) {
//                //dummy += multiply(i);
//                String fileName = "./src/main/resources/FoundationSeries.txt";
//                Reader reader = new FileReader(fileName);
//                Map<Integer, Long> freq = new HashMap<>();
//                tallyChars(reader, freq);
//                print_tally(freq);
//            }
//            double time = t.check() * 1e9 / count;
//            timer[j] = String.format("%6.1f ns",time);
//            for (int i = 0; i < timer.length; i++) {
//                System.out.println(timer[i]);
//            }
//            //System.out.printf("%6.1f ns%n", time);
//        }
//        //return dummy;
//
////        String fileName = "./src/main/resources/FoundationSeries.txt";
////        Reader reader = new FileReader(fileName);
////        Map<Integer, Long> freq = new HashMap<>();
////        tallyChars(reader, freq);
////        print_tally(freq);
//    }
//
//    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
//        int b;
//        while ((b = reader.read()) != -1) {
//            try {
//                freq.put(b, freq.get(b) + 1);
//            } catch (NullPointerException np) {
//                freq.put(b, 1L);
//            };
//        }
//    }
//
//    private static void print_tally(Map<Integer, Long> freq) {
//        int dist = 'a' - 'A';
//        Map<Character, Long> upperAndlower = new LinkedHashMap();
//        for (Character c = 'A'; c <= 'Z'; c++) {
//            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
//        }
//        Map<Character, Long> sorted = upperAndlower
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));
//        for (Character c : sorted.keySet()) {
//            System.out.println("" + c + ": " + sorted.get(c));;
//        }
//    }
//}
//
