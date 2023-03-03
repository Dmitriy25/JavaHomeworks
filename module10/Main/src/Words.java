import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Words {
    public void wordsFrequency (String path) {
        Map<String, Integer> wordFreq = new HashMap<>();
        InputStream fis;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNext()) {
            String[] tmp = scanner.nextLine().split(" ");
            for (String s : tmp) {
                if (wordFreq.containsKey(s)) {
                    wordFreq.put(s, wordFreq.get(s) + 1);
                } else {
                    wordFreq.put(s, 1);
                }
            }
        }
        Comparator<String> comparator = new Comparator<>() {
            public int compare(String k1, String k2) {
                return wordFreq.get(k1).compareTo(wordFreq.get(k2)) > 0 ? -1 : 1;
            }
        };
        Map<String, Integer> sortedWordFreq = new TreeMap<>(comparator);
        sortedWordFreq.putAll(wordFreq);

        for (Map.Entry<String, Integer> entry : sortedWordFreq.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
